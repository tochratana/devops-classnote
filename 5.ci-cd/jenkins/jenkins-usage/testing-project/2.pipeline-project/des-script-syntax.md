```bash
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                // build commands here
            }
        }
        
        stage('Test') {
            steps {
                // test commands here
            }
        }
        
        stage('Deploy') {
            steps {
                // deploy commands here
            }
        }
    }
}
```



1. Use for Declarative : 
```bash
pipeline { # Declares the start of a Declarative pipeline.
    agent any # where the pipeline will execute, Any available Jenkins agent
    stages { # Container for all your pipeline stages. This is a wrapper that holds all the individual stage blocks. You must have at least one stages block in a Declarative pipeline.
        stage('Build') { # Defines a single stage named 'Build'. A stage represents a distinct phase of your pipeline. The name 'Build' is arbitrary - you can name it anything meaningful. This name appears in the Jenkins UI as a visual step.
            steps { # Container for the actual work to be done in this stage. Everything inside steps is the actual commands or actions that will execute. Each stage must have a steps block.

                sh 'make build'
            }
        }
    }
}
```