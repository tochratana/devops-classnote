pipeline {
       agent any
       
       stages {
           stage('Checkout') {
               steps {
                   echo 'Checking out code...'
                   git 'https://github.com/jenkins-docs/simple-java-maven-app.git'
               }
           }
           
           stage('Build') {
               steps {
                   echo 'Building application...'
                   sh 'ls -la'
               }
           }
           
           stage('Test') {
               steps {
                   echo 'Running tests...'
                   sh 'echo "Test passed!"'
               }
           }
           
           stage('Deploy') {
               steps {
                   echo 'Deploying application...'
                   sh 'echo "Deployed to staging"'
               }
           }
       }
       
       post {
           success {
               echo 'Pipeline completed successfully!'
           }
           failure {
               echo 'Pipeline failed!'
           }
       }
   }