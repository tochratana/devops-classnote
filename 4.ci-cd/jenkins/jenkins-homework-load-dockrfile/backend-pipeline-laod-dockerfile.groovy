@Library("telegrame_notification_share_library@main") _
pipeline {
    agent any

    tools {
        jdk 'jdk-21'
    }

    environment {
        CHAT_ID    = "1177908131"
        CHAT_TOKEN = "7873147150:AAGVJ-bpejW4O0XS9FhLQmwEr5Wk-VK89-Y"

        SONAR_SCANNER = tool 'sonarqube-scanner'

        IMAGE_NAME = "nexina/prodstack-backend"
        CONTAINER_NAME = "backend"
        BACKEND_PORT = "8081"

        DB_NAME     = "prodstack_db"
        DB_USER     = "postgres"
        DB_PASSWORD = "postgres"
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
                withSonarQubeEnv(
                    credentialsId: 'SONARQUBE-TOKEN',
                    installationName: 'sonarqube-scanner'
                ) {
                    sh """
                        ${SONAR_SCANNER}/bin/sonar-scanner \
                        -Dsonar.projectKey=prodstack-backend \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.java.binaries=build/classes/java/main
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 3, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                writeFile file: 'Dockerfile', text: libraryResource('spring/dev.Dockerfile')
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

        stage('Run Backend Container') {
            steps {
                sh '''
                    docker network create prod-net || true
                    docker rm -f backend || true

                    docker run -d --name backend \
                      --network prod-net \
                      -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/${DB_NAME} \
                      -e SPRING_DATASOURCE_USERNAME=${DB_USER} \
                      -e SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD} \
                      -p ${BACKEND_PORT}:8080 \
                      ${IMAGE_NAME}:latest
                '''
            }
        }
    }

    post {
        success {
            script {
                sendTelegrameMessage(
                    "✅ <b>Backend Deployed Successfully</b>",
                    CHAT_TOKEN,
                    CHAT_ID
                )
            }
        }
        failure {
            script {
                sendTelegrameMessage(
                    "❌ <b>Backend Pipeline Failed</b>",
                    CHAT_TOKEN,
                    CHAT_ID
                )
            }
        }
    }
}
