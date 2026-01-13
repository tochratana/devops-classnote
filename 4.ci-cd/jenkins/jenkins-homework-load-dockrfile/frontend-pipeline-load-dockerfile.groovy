@Library("telegrame_notification_share_library@main") _
pipeline {
    agent any

    tools {
        nodejs 'Node-20'
    }

    environment {
        CHAT_ID    = "1177908131"
        CHAT_TOKEN = "7873147150:AAGVJ-bpejW4O0XS9FhLQmwEr5Wk-VK89-Y"

        SONAR_SCANNER = tool 'sonarqube-scanner'

        IMAGE_NAME = "nexina/prodstack-frontend"
        CONTAINER_NAME = "frontend"
        FRONTEND_PORT = "3001"
    }

    stages {

        stage('Clone Frontend') {
            steps {
                git url: 'https://github.com/tochratana/prodstack-ui.git', branch: 'main'
            }
        }

        stage('Build Frontend') {
            steps {
                sh '''
                    npm install
                    npm run build
                '''
            }
        }

        stage('SonarQube Scan') {
            steps {
                withSonarQubeEnv(
                    credentialsId: 'SONARQUBE-TOKEN',
                    installationName: 'sonarqube-scanner'
                ) {
                    sh """
                        ${SONAR_SCANNER}/bin/sonar-scanner \
                        -Dsonar.projectKey=prodstack-frontend \
                        -Dsonar.sources=src \
                        -Dsonar.exclusions=node_modules/**,build/**
                    """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                writeFile file: 'Dockerfile', text: libraryResource('reactjs/dev.Dockerfile')
                sh 'docker build -t ${IMAGE_NAME}:latest .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
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
            }
        }

        stage('Run Frontend Container') {
            steps {
                sh '''
                    docker rm -f frontend || true

                    docker run -d --name frontend \
                      --network prod-net \
                      -p ${FRONTEND_PORT}:3000 \
                      ${IMAGE_NAME}:latest
                '''
            }
        }
    }

    post {
        success {
            script {
                sendTelegrameMessage(
                    "✅ <b>Frontend Deployed Successfully</b>",
                    CHAT_TOKEN,
                    CHAT_ID
                )
            }
        }
        failure {
            script {
                sendTelegrameMessage(
                    "❌ <b>Frontend Pipeline Failed</b>",
                    CHAT_TOKEN,
                    CHAT_ID
                )
            }
        }
    }
}
