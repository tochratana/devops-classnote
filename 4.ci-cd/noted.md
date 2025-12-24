## CI and CD tools :
1. Jenkins (popular open-source, we focus here)
2. GitLab CI/CD
3. GitHub Actions
4. CircleCI, TravisCI
5. TeamCity, Bamboo

## What is Jenkins?
	•	Jenkins is a CI/CD automation tool.
	•	It monitors your source code repository, triggers builds, runs tests, and optionally deploys code.
	•	Can be extended with plugins (e.g., Docker, GitHub, Slack notifications)

## Jenkins Architecture
> Jenkins have 3 main part : 
1. Master (Controller)
2. Agents
3. Job/Pipline
> How it work :
- Jenkins noted the push code
- if have push code Master job start run
- if we have agents, master assign to avaliable agent
- agent checkout the code to workspace
- Agent runs the build, tests, and deployment.
- Job result (success/fail) is sent back to master.
- Job result (success/fail) is sent back to master.
```bash
       [Developer Push Code]
               |
           [Git Repo]
               |
           [Jenkins Master]
               |
     -------------------------
    |           |            |
[Agent 1]   [Agent 2]   [Agent 3]
    |           |            |
Build/Test   Build/Test   Build/Test
Workspace    Workspace    Workspace
```
