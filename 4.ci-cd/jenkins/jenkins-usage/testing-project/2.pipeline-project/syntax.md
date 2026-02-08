```bash
node {
    stage('Build') {
        echo 'Building the project'
    }

    stage('Test') {
        echo 'Running tests'
    }

    stage('Deploy') {
        echo 'Deploying application'
    }
}

```