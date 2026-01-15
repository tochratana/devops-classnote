@Library("telegrame_notification_share_library@main") _
pipeline {
    agent any

    tools {
        jdk 'jdk-21'                 
        nodejs 'Node-20'        
    }

    environment {
        // Telegram
        CHAT_ID    = "1177908131"
        CHAT_TOKEN = "7873147150:AAGVJ-bpejW4O0XS9FhLQmwEr5Wk-VK89-Y"

        // SonarQube
        SONAR_SCANNER = tool 'sonarqube-scanner'
        
        // Database Configuration
        DB_NAME     = "prodstack_db"
        DB_USER     = "postgres"
        DB_PASSWORD = "postgres"
        DB_PORT     = "5433"  // Changed from 5432 to avoid conflicts
        
        // Application Ports
        BACKEND_PORT  = "8081"  // Changed from 8080
        FRONTEND_PORT = "3001"  // Changed from 3000
    }

    stages {

        stage('Clone Repositories') {
            steps {
                dir('prodstack') {
                    git url: 'https://github.com/tochratana/prodstack.git', branch: 'main'
                }
                dir('prodstack-ui') {
                    git url: 'https://github.com/tochratana/prodstack-ui.git', branch: 'main'
                }
            }
        }

        stage('Build Backend (Gradle)') {
            steps {
                dir('prodstack') {
                    sh '''
                        chmod +x gradlew
                        ./gradlew clean build -x test --no-daemon
                    '''
                }
            }
        }

        stage('SonarQube Scan - Backend') {
            steps {
                dir('prodstack') {
                    withSonarQubeEnv(
                        credentialsId: 'SONARQUBE-TOKEN',
                        installationName: 'sonarqube-scanner'
                    ) {
                        sh """
                            ${SONAR_SCANNER}/bin/sonar-scanner \
                            -Dsonar.projectKey=prodstack-backend \
                            -Dsonar.projectName=prodstack-backend \
                            -Dsonar.sources=src/main/java \
                            -Dsonar.java.binaries=build/classes/java/main \
                            -Dsonar.exclusions=**/test/**,**/build/**
                        """
                    }
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('prodstack-ui') {
                    sh '''
                        npm install
                        npm run build
                    '''
                }
            }
        }

        stage('SonarQube Scan - Frontend') {
            steps {
                dir('prodstack-ui') {
                    withSonarQubeEnv(
                        credentialsId: 'SONARQUBE-TOKEN',
                        installationName: 'sonarqube-scanner'
                    ) {
                        sh """
                            ${SONAR_SCANNER}/bin/sonar-scanner \
                            -Dsonar.projectKey=prodstack-frontend \
                            -Dsonar.projectName=prodstack-frontend \
                            -Dsonar.sources=src \
                            -Dsonar.exclusions=node_modules/**,dist/**,build/**
                        """
                    }
                }
            }
        }

        stage('Quality Gate - Backend') {
            steps {
                timeout(time: 3, unit: 'MINUTES') {
                    script {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            sendTelegrameMessage(
                                "❌ <b>Backend Quality Gate Failed:</b> ${qg.status}",
                                CHAT_TOKEN,
                                CHAT_ID
                            )
                            error "Quality Gate failed: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Load Dockerfiles from Shared Library') {
            steps {
                writeFile file: 'Dockerfile.backend', text: libraryResource('spring/dev.Dockerfile')
                writeFile file: 'Dockerfile.frontend', text: libraryResource('reactjs/dev.Dockerfile')
            }
        }

        stage('Build Docker Images') {
            steps {
                sh '''
                    docker build -t backend-app:latest   -f Dockerfile.backend ./prodstack
                    docker build -t frontend-app:latest  -f Dockerfile.frontend ./prodstack-ui
                '''
            }
        }

        stage('Setup Database Container') {
            steps {
                sh '''
                    # Create network if it doesn't exist
                    docker network create prod-net || true

                    # Stop and remove existing postgres container
                    docker rm -f postgres || true

                    # Run PostgreSQL container
                    docker run -d --name postgres \
                      --network prod-net \
                      -e POSTGRES_DB=${DB_NAME} \
                      -e POSTGRES_USER=${DB_USER} \
                      -e POSTGRES_PASSWORD=${DB_PASSWORD} \
                      -p ${DB_PORT}:5432 \
                      -v postgres-data:/var/lib/postgresql/data \
                      postgres:15-alpine

                    # Wait for PostgreSQL to be ready
                    echo "Waiting for PostgreSQL to be ready..."
                    sleep 10
                    
                    # Health check
                    docker exec postgres pg_isready -U ${DB_USER} || (echo "PostgreSQL failed to start" && exit 1)
                    
                    echo "PostgreSQL is ready!"
                '''
            }
        }

        stage('Cleanup Old Containers') {
            steps {
                sh '''
                    echo "Stopping and removing old containers..."
                    
                    # Stop and remove containers by name
                    docker stop backend frontend 2>/dev/null || true
                    docker rm backend frontend 2>/dev/null || true
                    
                    # Also stop any containers using the configured ports
                    docker ps -q --filter "publish=${BACKEND_PORT}" | xargs -r docker stop 2>/dev/null || true
                    docker ps -q --filter "publish=${FRONTEND_PORT}" | xargs -r docker stop 2>/dev/null || true
                    
                    # Remove any stopped containers using these ports
                    docker ps -aq --filter "publish=${BACKEND_PORT}" | xargs -r docker rm 2>/dev/null || true
                    docker ps -aq --filter "publish=${FRONTEND_PORT}" | xargs -r docker rm 2>/dev/null || true
                    
                    echo "Cleanup completed!"
                '''
            }
        }

        stage('Run Application Containers') {
            steps {
                sh '''
                    # Run backend container
                    docker run -d --name backend \
                      --network prod-net \
                      -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/${DB_NAME} \
                      -e SPRING_DATASOURCE_USERNAME=${DB_USER} \
                      -e SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD} \
                      -p ${BACKEND_PORT}:8080 \
                      backend-app:latest

                    # Wait for backend to start
                    sleep 5

                    # Run frontend container
                    docker run -d --name frontend \
                      --network prod-net \
                      -e REACT_APP_API_URL=http://localhost:${BACKEND_PORT} \
                      -p ${FRONTEND_PORT}:3000 \
                      frontend-app:latest

                    # Show running containers
                    docker ps
                '''
            }
        }

        stage('Health Check') {
            steps {
                sh '''
                    echo "Checking container health..."
                    
                    # Check if containers are running
                    docker ps | grep postgres || (echo "PostgreSQL container not running" && exit 1)
                    docker ps | grep backend || (echo "Backend container not running" && exit 1)
                    docker ps | grep frontend || (echo "Frontend container not running" && exit 1)
                    
                    echo "All containers are running successfully!"
                '''
            }
        }

        stage('Send Telegram Success') {
            steps {
                script {
                    sendTelegrameMessage(
                        """<b>✅ Deployment Successful</b>

<b>Frontend:</b> http://yourhost:${FRONTEND_PORT}
<b>Backend:</b> http://yourhost:${BACKEND_PORT}
<b>Database:</b> PostgreSQL Container - Port ${DB_PORT}
<b>Quality Gate:</b> PASSED""",
                        CHAT_TOKEN,
                        CHAT_ID
                    )
                }
            }
        }
    }

    post {
        failure {
            script {
                sendTelegrameMessage(
                    "❌ <b>Deployment failed.</b> Check Jenkins logs.",
                    CHAT_TOKEN,
                    CHAT_ID
                )
            }
        }
    }
}