#!groovy
@Library('jenkins-pipeline-shared@master') _

// slack channel where you want to send the job notification
def slack_channel = 'slack_channel'

// Bucket name where you want to send the artifact
def bucketName = "bucket_name"

//Downstream Job Deploy
def deployJob = "Deploy_job"

pipeline {
    agent {
        kubernetes {
            label "node-build"
            defaultContainer 'jnlp'
            yaml """
                apiVersion: v1
                kind: Pod
                metadata:
                    labels:
                        docker: true
                spec:
                    containers:
                    - name: git
                      image: alpine/git
                      command:
                      - cat
                      tty: true
                    - name: node
                      image: node:12.19-alpine
                      command:
                      - cat
                      tty: true
            """
        }
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        skipDefaultCheckout true
        timestamps()
    }

    stages {
        stage ('Checkout') {
            steps {
                container('git') {
                    script {
                        ## SCM checkout
                        )
                    }
                }
            }
        }

        stage('NPM install') {
            steps {
                container('node') {
                        script {
                            sh """
                            ## Build Commands
                            """
	                }
                  }
             }
        }

        stage('run tests') {
            steps {
                ## Junit commands
            }
        }

        stage('Upload Artifacts') {
            steps {
                container('node') {
                    script {
                        ## Plugin to upload artifact to S3
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            sendNotifications currentBuild.result, "Job: '${env.JOB_NAME}', VERSION: 'commit-${commit}-build-${env.BUILD_NUMBER}'", slack_channel
        }

        success {
            bitbucketStatusNotify (
               ## Build Status Notify
            )
            script {
                if ( branch == 'develop') {
                    build(job: "${deployJob}" + "/" + "${branch}".replaceAll('/', '%2F'))
                }
                if (branch == 'release/1.0.0') {
                    build(job: "${deployJob}" + "/" + "${branch}".replaceAll('/', '%2F'))
                }
                if (branch == 'master') {
                    build(job: "${deployJob}" + "/" + "${branch}".replaceAll('/', '%2F'))
                }
            }
        }

        unsuccessful {
            bitbucketStatusNotify (
                buildKey: env.JOB_NAME,
                buildName: env.JOB_NAME,
                buildState: 'FAILED',
                repoSlug: 'repo_name',
                commitId: commit
            )
        }
    }

