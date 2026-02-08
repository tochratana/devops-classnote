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


> Jenkins Server
1. Jenkins Web UI http://localhost:8080
From the UI you:
- Create jobs
- Click “Build Now”
- See logs
- Install plugins
2. Jenkins Home Directory (/var/jenkins_home)
3. Jenkins Scheduler
   - WHEN a job runs
   - WHAT job runs
   - WHERE it runs (master or agent)
4. Executors (worker): Threads that run jobs
   Jenkins server is difference jenkins job : 
| Jenkins Server     | Jenkins Job              |
| ------------------ | ------------------------ |
| Always running     | Runs only when triggered |
| Manages everything | Just runs commands       |
| Has UI & config    | Has steps                |



## Jenkins Usage
1. Introduction to Jenkins
2. Installation and Setup
3. Jenkins Architecture
4. Core Concepts
5. Creating Jobs : 
- undstand about Jenkins Jobs: Jenkins have different type of jobs, that we need.
* Working with projects
  - enkins uses projects (also known as "jobs") to perform its work. Projects are defined and run by Jenkins users. Jenkins offers severa












- workspace : the place that agents(master) work.
```bash


```
























