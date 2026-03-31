## ArgoCD

### 1. Argo CD

Argo CD គឺជា tool សម្រាប់ deploy application ទៅ Kubernetes ដោយប្រើ Git ជា source of truth។

> Idea សំខាន់:

`Git Repository  ->  ArgoCD  ->  Kubernetes Cluster`

- YAML manifests (Deployment, Service...) នៅក្នុង Git
- ArgoCD sync វាទៅ cluster
- ប្រសិនបើ cluster ខុសពី Git → ArgoCD fix វាវិញ
- នេះហៅថា GitOps

### 2. Sync Window

Sync Window គឺជា time schedule អនុញ្ញាត ឬ មិនអនុញ្ញាតឱ្យ sync។

Example:
```
Time	Action
9AM - 5PM	allow deploy
5PM - 9AM	block deploy
```
ហេតុអ្វីប្រើ?

- កុំឱ្យ deploy ពេល production peak
- deploy តែពេល maintenance window

Example:
```
allow: 02:00 - 04:00
deny: all other time
```
### 3. GitOps & Source Of Truth
GitOps = deploy infrastructure/application using Git

Flow:
```
Developer push code
        ↓
Git repo change
        ↓
ArgoCD detect change
        ↓
Sync to Kubernetes
```
Source of Truth = Git Repository

Example:

- Git repo
- deployment.yaml (replicas: 3)

បើ admin នៅ cluster ប្តូរ manually:
```bash
kubectl scale deployment nginx --replicas=1
```
ArgoCD នឹង detect:
```
Git = 3
Cluster = 1
```
ArgoCD នឹង restore back to 3 `នេះហៅថា Git is Source of Truth`

### 4. Self-Healing
Self-Healing = Auto fix drift

Example:
```
Git repo:
replicas: 3
```
បើមាន admin scale:
```
replicas: 1
```
ArgoCD នឹង:
`auto sync -> restore replicas:3`
> cluster state ត្រូវតែ match Git

### 5. Self-Prune

Self-Prune = delete resources ដែល Git មិនមាន

Example Git repo
```
deployment
service
```
Cluster:
```
deployment
service
configmap
```
> ArgoCD នឹង delete:
- configmap
- ព្រោះ Git មិនមាន

### 6. Foreground vs Background

នេះគឺ delete strategy

Foreground
```
Delete parent → wait children delete
```
Example:
```
Deployment
   ↓
ReplicaSet
   ↓
Pods
```
Flow:
```
delete deployment
→ delete pod first
→ delete replicaset
→ delete deployment
```
> safe ប៉ុន្តែ slow

Background
```
Delete parent immediately
```
Flow:
```
delete deployment immediately
children deleted later
```
> fast ប៉ុន្តែ sometimes orphan resources

### 7. Summary (សំខាន់សម្រាប់ interview)

- Concept	Meaning
- Sync Window	schedule deploy time
- GitOps	deploy using Git
- Source Of Truth	Git is main state
- Self-Healing	auto fix drift
- Self-Prune	delete unused resources
- Foreground	delete children first
- Background	delete parent first

> Tip សម្រាប់ DevOps interview

សំណួរដែលគេសួរញឹកញាប់:

- What is ArgoCD?
- What is GitOps?
- What is source of truth?
- What is drift?
- What is self healing?
- What is prune?