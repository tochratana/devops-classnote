#### Pipeline Project (Modern Approach) : Uses Jenkinsfile (code) to define your build pipeline. Infrastructure as Code approach.
---
1. Create Project
   1. Click `New Item`
   2. Enter name: `my-first-pipeline`
   3. Select `Pipeline`
   4. Click OK
2. Write Your Pipeline (Simple Example)
   1. Scroll to pipeline section
   2. select `Pipeline script` (Not from SCM for now)
   3. Paste this code `.groovy`
3. Save and Build:
   1. Click `save`
   2. click `Build Now`
   3. You'll see a visual pipeline view with each stage
---
Advanced Practice - Pipeline from SCM:
* Create a new Pipeline project: 
* pipeline-from-scm
* In Pipeline section, select "Pipeline script from SCM"
* SCM: Git
* Repository URL: https://github.com/jenkins-docs/simple-java-maven-app.git
* Script Path: Jenkinsfile (this repo has one)
* Save and build