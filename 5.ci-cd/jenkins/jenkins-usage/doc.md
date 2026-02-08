# Complete Jenkins Guide

## Table of Contents
1. Introduction to Jenkins
2. Installation and Setup
3. Jenkins Architecture
4. Core Concepts
5. Creating Jobs
6. Jenkins Pipelines
7. Integrations
8. Security
9. Best Practices
10. Troubleshooting

---

## 1. Introduction to Jenkins

Jenkins is an open-source automation server that enables developers to build, test, and deploy their software reliably. It's the most widely used tool for continuous integration and continuous delivery (CI/CD).

**Key Features:**
- Extensible with 1800+ plugins
- Distributed builds across multiple machines
- Easy installation and configuration
- Support for various version control systems
- Pipeline as Code capabilities

---

## 2. Installation and Setup

### Installation Methods

**On Linux (Ubuntu/Debian):**
```bash
# Add Jenkins repository
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'

# Install Jenkins
sudo apt update
sudo apt install jenkins

# Start Jenkins
sudo systemctl start jenkins
sudo systemctl enable jenkins
```

**Using Docker:**
```bash
docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
```

**On Windows:**
Download the Windows installer from jenkins.io and follow the installation wizard.

### Initial Setup

1. Access Jenkins at `http://localhost:8080`
2. Retrieve the initial admin password from `/var/lib/jenkins/secrets/initialAdminPassword`
3. Install suggested plugins or select specific plugins
4. Create your first admin user
5. Configure Jenkins URL

---

## 3. Jenkins Architecture

### Master-Agent Architecture

**Master (Controller):**
- Schedules build jobs
- Dispatches builds to agents
- Monitors agents
- Records and presents build results
- Serves the Jenkins UI

**Agents (Nodes):**
- Execute builds dispatched by the master
- Can run on different operating systems
- Configured with labels for targeted job execution

### Components

- **Jenkins Home Directory**: Stores all configuration, build logs, and workspace files
- **Workspace**: Temporary directory where builds are executed
- **Build Queue**: Holds jobs waiting to be executed
- **Executor**: A slot for executing jobs on a node

---

## 4. Core Concepts

### Jobs/Projects

Jenkins jobs are runnable tasks that Jenkins can execute. Types include:
- **Freestyle Project**: Basic job type with simple configuration
- **Pipeline**: Code-based job definition
- **Multi-configuration Project**: For testing across multiple environments
- **Folder**: Organizational container for jobs
- **Multibranch Pipeline**: Automatically creates pipelines for branches

### Builds

A build is a single execution of a job. Each build has:
- Build number (sequential)
- Build status (success, failure, unstable, aborted)
- Console output
- Build artifacts
- Test results

### Plugins

Plugins extend Jenkins functionality. Popular categories:
- Source Control (Git, SVN, Mercurial)
- Build Tools (Maven, Gradle, Ant)
- Testing (JUnit, TestNG, Selenium)
- Deployment (Docker, Kubernetes, AWS)
- Notifications (Email, Slack, Microsoft Teams)

---

## 5. Creating Jobs


- Freestyle Project
- Pipeline
- Folder


### Jenkins Pipeline
Code-based job definition using Groovy DSL. Supports both Declarative and Scripted syntax.
```bash
pipeline {
    agent any // Specifies where the entire pipeline will run
    stages {
        stage('Build') {
            steps {
                sh 'make' // Executes a shell command
            }
        }
        stage('Test') {
            steps {
                sh 'make check'
                junit 'reports/**/*.xml' // Publishes JUnit test results
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...' // Prints a message to the console
            }
        }
    }
}
```
```bash
pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.1'
        jdk 'JDK-11'
    }
    
    parameters {
        choice(
            name: 'ENVIRONMENT', 
            choices: ['dev', 'staging', 'production'], 
            description: 'Deployment environment'
        )
        booleanParam(
            name: 'RUN_TESTS', 
            defaultValue: true, 
            description: 'Execute test suite'
        )
        string(
            name: 'BRANCH_NAME', 
            defaultValue: 'main', 
            description: 'Branch to build'
        )
    }
    
    environment {
        DOCKER_REGISTRY = 'registry.example.com'
        APP_NAME = 'my-microservice'
        VERSION = "${BUILD_NUMBER}"
        SONAR_TOKEN = credentials('sonarqube-token')
    }
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 1, unit: 'HOURS')
        timestamps()
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo "Checking out branch: ${params.BRANCH_NAME}"
                git branch: "${params.BRANCH_NAME}", 
                    url: 'https://github.com/user/microservice.git',
                    credentialsId: 'github-credentials'
            }
        }
        
        stage('Build') {
            steps {
                echo 'Compiling the application...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Unit Tests') {
            when {
                expression { params.RUN_TESTS == true }
            }
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    jacoco(
                        execPattern: 'target/jacoco.exec',
                        classPattern: 'target/classes',
                        sourcePattern: 'src/main/java'
                    )
                }
            }
        }
        
        stage('Code Quality Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=${APP_NAME} \
                        -Dsonar.host.url=https://sonarqube.example.com \
                        -Dsonar.login=${SONAR_TOKEN}
                    '''
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        
        stage('Package') {
            steps {
                echo 'Creating JAR file...'
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building Docker image..."
                    sh """
                        docker build -t ${DOCKER_REGISTRY}/${APP_NAME}:${VERSION} .
                        docker tag ${DOCKER_REGISTRY}/${APP_NAME}:${VERSION} ${DOCKER_REGISTRY}/${APP_NAME}:latest
                    """
                }
            }
        }
        
        stage('Security Scan') {
            steps {
                echo 'Scanning Docker image for vulnerabilities...'
                sh "trivy image ${DOCKER_REGISTRY}/${APP_NAME}:${VERSION}"
            }
        }
        
        stage('Push to Registry') {
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: 'docker-registry-creds',
                        usernameVariable: 'REGISTRY_USER',
                        passwordVariable: 'REGISTRY_PASS'
                    )]) {
                        sh """
                            echo ${REGISTRY_PASS} | docker login ${DOCKER_REGISTRY} -u ${REGISTRY_USER} --password-stdin
                            docker push ${DOCKER_REGISTRY}/${APP_NAME}:${VERSION}
                            docker push ${DOCKER_REGISTRY}/${APP_NAME}:latest
                        """
                    }
                }
            }
        }
        
        stage('Deploy to Environment') {
            steps {
                script {
                    echo "Deploying to ${params.ENVIRONMENT}..."
                    
                    if (params.ENVIRONMENT == 'production') {
                        input message: 'Deploy to Production?', ok: 'Deploy', submitter: 'admin,release-manager'
                    }
                    
                    sh """
                        kubectl set image deployment/${APP_NAME} \
                            ${APP_NAME}=${DOCKER_REGISTRY}/${APP_NAME}:${VERSION} \
                            -n ${params.ENVIRONMENT}
                        
                        kubectl rollout status deployment/${APP_NAME} -n ${params.ENVIRONMENT}
                    """
                }
            }
        }
        
        stage('Integration Tests') {
            when {
                expression { params.ENVIRONMENT != 'production' }
            }
            steps {
                echo 'Running integration tests...'
                sh 'mvn verify -DskipUnitTests'
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline completed successfully!'
            slackSend(
                channel: '#deployments',
                color: 'good',
                message: "✅ SUCCESS: ${APP_NAME} v${VERSION} deployed to ${params.ENVIRONMENT}"
            )
        }
        failure {
            echo 'Pipeline failed!'
            slackSend(
                channel: '#deployments',
                color: 'danger',
                message: "❌ FAILED: ${APP_NAME} build ${BUILD_NUMBER} failed"
            )
        }
        always {
            cleanWs()
        }
    }
}
```



### Freestyle Job Example

1. Click "New Item"
2. Enter job name and select "Freestyle project"
3. Configure Source Code Management (e.g., Git repository)
4. Add Build Triggers (e.g., Poll SCM, GitHub webhook)
5. Add Build Steps (e.g., Execute shell script)
6. Add Post-build Actions (e.g., Archive artifacts, send notifications)

**Example Build Step (Shell):**
```bash
#!/bin/bash
echo "Installing dependencies..."
npm install

echo "Running tests..."
npm test

echo "Building application..."
npm run build

echo "Creating deployment package..."
tar -czf app-${BUILD_NUMBER}.tar.gz dist/ package.json

echo "Build completed successfully!"
```
```bash
#!/bin/bash
echo "Building the project..."
mvn clean install
echo "Build completed!"
```

---

## 6. Jenkins Pipelines

### Declarative Pipeline

```groovy
pipeline {
    agent any
    
    environment {
        APP_NAME = 'my-application'
        VERSION = '1.0.0'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/user/repo.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Code Analysis') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
        
        stage('Deploy to Staging') {
            steps {
                sh './deploy-staging.sh'
            }
        }
        
        stage('Deploy to Production') {
            when {
                branch 'main'
            }
            steps {
                input message: 'Deploy to production?', ok: 'Deploy'
                sh './deploy-production.sh'
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline succeeded!'
            emailext subject: 'Build Success', body: 'The build was successful', to: 'team@example.com'
        }
        failure {
            echo 'Pipeline failed!'
            emailext subject: 'Build Failed', body: 'The build failed', to: 'team@example.com'
        }
    }
}
```

### Scripted Pipeline

```groovy
node {
    try {
        stage('Checkout') {
            checkout scm
        }
        
        stage('Build') {
            sh 'npm install'
            sh 'npm run build'
        }
        
        stage('Test') {
            sh 'npm test'
        }
        
        stage('Deploy') {
            if (env.BRANCH_NAME == 'main') {
                sh 'kubectl apply -f deployment.yaml'
            }
        }
        
        currentBuild.result = 'SUCCESS'
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        throw e
    } finally {
        // Cleanup or notifications
        echo "Build result: ${currentBuild.result}"
    }
}
```

### Pipeline Features

**Parallel Execution:**
```groovy
stage('Parallel Testing') {
    parallel {
        stage('Unit Tests') {
            steps {
                sh 'npm run test:unit'
            }
        }
        stage('Integration Tests') {
            steps {
                sh 'npm run test:integration'
            }
        }
        stage('E2E Tests') {
            steps {
                sh 'npm run test:e2e'
            }
        }
    }
}
```

**Using Credentials:**
```groovy
stage('Deploy') {
    steps {
        withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            sh 'docker login -u $USER -p $PASS'
            sh 'docker push myimage:latest'
        }
    }
}
```

---

## 7. Integrations

### Git Integration

```groovy
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'develop',
                    credentialsId: 'git-credentials',
                    url: 'https://github.com/user/repo.git'
            }
        }
    }
}
```

### Docker Integration

```groovy
pipeline {
    agent {
        docker {
            image 'maven:3.8.1-jdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
```

### Kubernetes Integration

```groovy
pipeline {
    agent {
        kubernetes {
            yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: maven
    image: maven:3.8.1-jdk-11
    command: ['cat']
    tty: true
            '''
        }
    }
    stages {
        stage('Build') {
            steps {
                container('maven') {
                    sh 'mvn clean package'
                }
            }
        }
    }
}
```

### Webhooks

Configure GitHub/GitLab webhooks to trigger builds automatically:
1. In Jenkins job, enable "GitHub hook trigger for GITScm polling"
2. In GitHub repo settings, add webhook pointing to `http://jenkins-url/github-webhook/`
3. Select events that should trigger builds

---

## 8. Security

### Access Control

**Matrix-based Security:**
- Navigate to Manage Jenkins > Configure Global Security
- Enable "Matrix-based security"
- Assign permissions to users/groups

**Role-based Access:**
- Install "Role-based Authorization Strategy" plugin
- Define roles (admin, developer, viewer)
- Assign users to roles

### Credentials Management

Store sensitive information securely:
- Username/password
- SSH keys
- Secret files
- Secret text (API tokens)
- Certificates

**Using Credentials in Pipeline:**
```groovy
withCredentials([string(credentialsId: 'api-token', variable: 'TOKEN')]) {
    sh "curl -H 'Authorization: Bearer $TOKEN' https://api.example.com"
}
```

### Best Security Practices

- Keep Jenkins and plugins updated
- Use HTTPS for Jenkins UI
- Enable CSRF protection
- Restrict access to Jenkins master
- Use dedicated service accounts for integrations
- Audit security logs regularly
- Implement backup strategy

---

## 9. Best Practices

### Pipeline Best Practices

1. **Use Declarative Pipeline**: More readable and maintainable
2. **Store Jenkinsfile in SCM**: Version control your pipelines
3. **Use Shared Libraries**: Reuse common pipeline code
4. **Implement Proper Error Handling**: Use try-catch blocks
5. **Keep Pipelines DRY**: Don't repeat yourself
6. **Use Stages Effectively**: Break down into logical units
7. **Leverage Parallel Execution**: Speed up builds
8. **Clean Workspace**: Remove old artifacts

### Job Configuration

- Use descriptive names for jobs
- Organize jobs in folders
- Set appropriate build retention policies
- Use parameters for flexible builds
- Tag builds appropriately
- Document jobs with descriptions

### Performance Optimization

- Distribute builds across agents
- Use pipeline caching
- Optimize checkout steps (shallow clones)
- Archive only necessary artifacts
- Clean old workspaces regularly
- Monitor system resources

### Shared Library Example

**vars/buildAndTest.groovy:**
```groovy
def call(Map config) {
    pipeline {
        agent any
        stages {
            stage('Build') {
                steps {
                    sh "${config.buildCommand}"
                }
            }
            stage('Test') {
                steps {
                    sh "${config.testCommand}"
                }
            }
        }
    }
}
```

**Usage in Jenkinsfile:**
```groovy
@Library('my-shared-library') _
buildAndTest(
    buildCommand: 'npm run build',
    testCommand: 'npm test'
)
```

---

## 10. Troubleshooting

### Common Issues

**Build Stuck in Queue:**
- Check if all agents are online
- Verify agent labels match job requirements
- Increase number of executors

**Out of Memory Errors:**
- Increase heap size: `JAVA_OPTS="-Xmx2048m"`
- Check for memory leaks in plugins
- Clean old build data

**Plugin Conflicts:**
- Update all plugins to latest versions
- Check plugin compatibility matrix
- Disable conflicting plugins

**Slow Builds:**
- Use distributed builds
- Implement caching strategies
- Optimize SCM checkout
- Profile build steps

### Useful Commands

**Restart Jenkins:**
```bash
sudo systemctl restart jenkins
```

**Check Jenkins Logs:**
```bash
tail -f /var/log/jenkins/jenkins.log
```

**Jenkins Script Console:**
Access via Manage Jenkins > Script Console for Groovy scripts to manage Jenkins programmatically.

### Backup and Restore

**Backup Jenkins:**
```bash
# Backup Jenkins home directory
tar -czf jenkins-backup.tar.gz /var/lib/jenkins/

# Or use ThinBackup plugin for automated backups
```

**Restore Jenkins:**
```bash
# Stop Jenkins
sudo systemctl stop jenkins

# Restore backup
tar -xzf jenkins-backup.tar.gz -C /

# Start Jenkins
sudo systemctl start jenkins
```

---

## Additional Resources

- **Official Documentation**: https://www.jenkins.io/doc/
- **Plugin Index**: https://plugins.jenkins.io/
- **Community Forums**: https://community.jenkins.io/
- **GitHub**: https://github.com/jenkinsci/jenkins
- **Training**: CloudBees University, Linux Academy, Udemy

---

## Conclusion

Jenkins is a powerful and flexible automation server that can significantly improve your development workflow. Start with simple freestyle jobs, graduate to pipelines, and leverage plugins to customize Jenkins for your specific needs. Remember to follow security best practices and optimize for performance as your usage grows.