pipeline {
agent {
kubernetes {
label 'maven-agent'
defaultContainer 'maven'
yaml """
apiVersion: v1
kind: Pod
spec:
containers:
name: maven
image: maven:3.6.3-jdk-11
command:
cat
tty: true
name: kaniko
image: gcr.io/kaniko-project/executor:latest
command:
cat
tty: true
"""
}
}
stages {
stage('Checkout') {
steps {
container('maven') {
checkout scm
}
}
}

stage('Build') {
  steps {
    container('maven') {
      sh 'mvn -B -DskipTests package'
    }
  }
}

stage('Build & Push Image') {
  steps {
    container('kaniko') {
      sh """
      /kaniko/executor --context $WORKSPACE --dockerfile $WORKSPACE/Dockerfile \
        --destination my-registry/my-app:${env.BUILD_NUMBER} \
        --cache=true
      """
    }
  }
}

stage('Deploy') {
  steps {
    container('maven') {
      // or use kubectl image in a container
      sh """
      kubectl set image deployment/my-app my-app=my-registry/my-app:${env.BUILD_NUMBER} --namespace default
      kubectl rollout status deployment/my-app --namespace default
      """
    }
  }
}

}
}