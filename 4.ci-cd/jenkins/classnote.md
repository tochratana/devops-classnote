## Jenkins

we have 3 basic things for understand
1. Jenkins Server: 
   container that we already and run it in docker or jenkins that we have install it as server
   - Has UI
   - Store configuration
   - Decide when to run jobs
2. Job (Importance thing): 
   A list of command jenkins should run
   > Jenkins have 2 ways to defind a jobs
   - Freestyle Job: Jenkins web UI (beginner)
   - Pipeline Job(Jenkinsfile): In project(repo) (advance)
   > Let's understand about `Freestyle Job` 
   - What is freestyle jobs ?
   - **Freestyle Job** mean : 
     - We click jenkins ui
     - we type command in textbox
     - Jenkins run it.
   > Let's understand about `Jenkinsfile`
   - **WJenkinsfile** mean:
     - A file that contains job steps written as code
      ```bash
        pipeline {
          agent any
            stages {
              stage('Test') {
                steps {
                  sh 'echo Hello'
                }
              }
          }
        }
      ```
3. Workspace: When Jenkins runs a job, it needs a place to work (that place we call it workspace)
| Real life | Jenkins   |
| --------- | --------- |
| Kitchen   | Jenkins   |
| Chef      | Job       |
| Table     | Workspace |
| Recipe    | Commands  |


Jenkins Server
1. Jenkins Web UI http://localhost:8080
From the UI you:
- Create jobs
- Click “Build Now”
- See logs
- Install plugins
1. Jenkins Home Directory (/var/jenkins_home)
2. Jenkins Scheduler
   - WHEN a job runs
   - WHAT job runs
   - WHERE it runs (master or agent)
3. Executors