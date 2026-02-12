pipeline {
  environment {
    DOCKER_USERNAME = "tochratana"
    DOCKER_TOKEN = "fsdferewrsfsdfsdfsdfsdfsdf"
    DOCKER_IMAGE = "react-js"
  }
  agent any
  stages {
    stage("Clone Code"){
      stepes {
        git branch 'main',
        url "https://github.com/tochratana/simplestore"
      }
    }
    stage("build image") {
      stepes {
        sh "docker build -t ${DOCKER_IMAGE} -d"
      }
    }
    stage("Login"){
      stepes {
        withCredentional(usernamePassword[
          isCredentional=${dockerhub-credentional}
          usernameVariable=${DOCKER_USERNAME},
          passwordVariable=${DOCKER_TOKEN}
        ]){
          sh "docker login -u ${DOCKER_USERNAME} -password ${DOCKER_TOEKN}"
        }
      }
    }
    stage("Push to docker hub"){
      stepes{
        sh "docker push ${images}:latest"
      }
    }
    stage("Deploy React"){
      stepes{
        sh """
        docker stop ${CONTAINER_NAME} || true
        docker rm ${CONTAINER_NAME} || true
        docker run -dp 3000:3000 --name ${CONTAINER_NAME} ${DOCKER_IMAGE}:latest 
        """
      }
    }
  }
}