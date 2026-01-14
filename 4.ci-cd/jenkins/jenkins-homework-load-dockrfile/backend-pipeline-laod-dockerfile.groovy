@Library("telegrame_notification_share_library@main") _

pipeline {
    agent any

    tools {
        jdk 'jdk-21'
    }

    environment {
        CHAT_ID    = "1177908131"
        CHAT_TOKEN = "7873147150:AAGVJ-bpejW4O0XS9FhLQmwEr5Wk-VK89-Y"

        IMAGE_NAME = "nexina/prodstack-backend"
        CONTAINER_NAME = "backend"
        BACKEND_PORT = "8082"
    }

    stages {

        stage('Clone Backend') {
            steps {
                git url: 'https://github.com/tochratana/prodstack.git', branch: 'main'
            }
        }

        stage('Build Backend') {
            steps {
                sh '''
                    chmod +x gradlew
                    ./gradlew clean build -x test --no-daemon
                '''
            }
        }

        stage('SonarQube Scan') {
            steps {
                script {
                    checkCodeQualitySonarqubeJava(
                        'ProdStack Backend',
                        'prodstack-backend',
                        "${env.BUILD_NUMBER}"
                    )
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 2, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            echo "❌ Quality Gate Status: ${qg.status}"
                            echo "⚠️ Quality Gate failed but continuing deployment..."
                            // Don't fail the build, just warn
                            unstable(message: "Quality Gate failed: ${qg.status}")
                        } else {
                            echo "✅ Quality Gate Status: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "🔨 Building Docker Image..."
                    writeFile file: 'Dockerfile', text: libraryResource('spring/dev.Dockerfile')
                    sh 'docker build -t ${IMAGE_NAME}:latest .'
                    echo "✅ Docker Image Built Successfully"
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    echo "📤 Pushing to Docker Hub..."
                    withCredentials([usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )]) {
                        sh '''
                            echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                            docker push ${IMAGE_NAME}:latest
                        '''
                    }
                    echo "✅ Image Pushed Successfully"
                }
            }
        }

        stage('Run Backend Container') {
            steps {
                script {
                    echo "🚀 Starting Backend Container..."
                    sh '''
                        # Create network if it doesn't exist
                        docker network create prod-net || true
                        
                        # Stop and remove existing container
                        docker stop ${CONTAINER_NAME} || true
                        docker rm -f ${CONTAINER_NAME} || true
                        
                        # Run new container
                        docker run -d --name ${CONTAINER_NAME} \
                          --network prod-net \
                          -p ${BACKEND_PORT}:8080 \
                          ${IMAGE_NAME}:latest
                        
                        # Wait a moment for container to start
                        sleep 3
                        
                        # Check if container is running
                        if docker ps | grep -q ${CONTAINER_NAME}; then
                            echo "✅ Container ${CONTAINER_NAME} is running"
                            docker ps | grep ${CONTAINER_NAME}
                        else
                            echo "❌ Container failed to start"
                            docker logs ${CONTAINER_NAME}
                            exit 1
                        fi
                    '''
                    echo "✅ Backend Container Started Successfully"
                }
            }
        }
    }

    post {
        success {
            script {
                sendTelegrameMessage(
                    "✅ <b>Backend Deployed Successfully</b>\n\n" +
                    "🔹 Build: #${env.BUILD_NUMBER}\n" +
                    "🔹 Container: ${CONTAINER_NAME}\n" +
                    "🔹 Port: ${BACKEND_PORT}",
                    CHAT_TOKEN,
                    CHAT_ID
                )
            }
        }
        failure {
            script {
                sendTelegrameMessage(
                    "❌ <b>Backend Pipeline Failed</b>\n\n" +
                    "🔹 Build: #${env.BUILD_NUMBER}\n" +
                    "🔹 Stage: ${env.STAGE_NAME}",
                    CHAT_TOKEN,
                    CHAT_ID
                )
            }
        }
        always {
            script {
                echo "🧹 Pipeline completed"
            }
        }
    }
}