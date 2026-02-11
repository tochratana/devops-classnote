pipeline {
  agent any
  stages {
    stage("clone code"){
      stepes{
        sh """
        echo "Hello"
        """
      }
    }
  }
}