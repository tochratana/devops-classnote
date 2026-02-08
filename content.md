# DevOps Class Notes

This repository contains my **DevOps class notes**, including concepts, commands, configuration files, and hands-on practice. I use it as a **personal knowledge base** for learning, revision, and quick reference.

## 1. Introduction
**Core DevOps concepts** and overview.

* [Cut command](./1.introductory/basic-commands-learning/cut-commands/cut-doc.md)
* [user permission](1.introductory/basic-commands-learning/doc/permission-related.md)
* [SellScript](1.introductory/basic-commands-learning/doc/shellscript.md)
* [User relate in linux](./1.introductory/basic-commands-learning/doc/user-related.md)
* [Setup ZSH](./1.introductory/basic-commands-learning/doc/zshsetup.md)
* [User Management](./1.introductory/user-management/)
## 2. Web Server

Notes related to **web servers and deployment**.

* [Docker](2.workingwithwebservers/docker/)
* [Docker Compose](2.workingwithwebservers/docker-compose/)
* [Docker Volume](./2.workingwithwebservers/volume/)
* [Docker Network](./2.workingwithwebservers/docker-network/)

## 3. Nexus Repository

Nexus is used as an **artifact repository** for binaries, Docker images, and build outputs.

- [Installation](./3.nexusossrepository/nexus/docker-compose.yaml) Run command  `docker compose up -d`
- [document](./3.nexusossrepository/nexus/)

## 4. CI/CD Pipeline

**Continuous Integration & Continuous Deployment** concepts.
### Jenkins

* [Nginx Config](./4.ci-cd/jenkins/nginx-conf/)
* [Jenkins Pipeline](./4.ci-cd/jenkins/pipes/readme.md)
* Jenkins installation
* Freestyle vs Pipeline jobs
* Jenkinsfile basics
* GitHub integration
* [Jenkins Document](./4.ci-cd/jenkins/classnote.md)
* [Install Jenkins as Service](./4.ci-cd/jenkins/install-as-service/install.sh)
* [Jenkins Usage](4.ci-cd/jenkins/jenkins-usage/doc.md)
* [Install SOnarqube](./4.ci-cd/jenkins/)
* [Sonarqube](./4.ci-cd/jenkins/sonarqube/next-pipeline.groovy)


## 5. Ansible

Ansible is used for **configuration management** and **Infrastructure as Code**.

### Basics

* [Installation](./5.ansible/Installation.md)
* [Guidelines](./5.ansible/readme.md)
* [Inventory](./5.ansible/inventory.ini)
* [Playbook test](./5.ansible/playbook/first.yaml)

### Core Concepts

* Inventory
* Playbooks
* Modules
* Variables
* Roles
* Handlers

### Practice

* [NFS setup](./5.ansible/nfs-learn)

### Infrastructure as Code (IaC)

* [Root](./5.ansible/iac/)
* [Google Cloud](./5.ansible/iac/google-cloud/)
  * [GCP IaC overview](./5.ansible/iac/google-cloud/iaC/)
  * [SSH playbook](./5.ansible/iac/google-cloud/project-2-ssh/)
  * [Test playbook](./5.ansible/iac/google-cloud/project-1/)
  * [Dynamic inventory](./5.ansible/iac/google-cloud/project-3-template/)

## 6. Kubernetes

Kubernetes is used for **container orchestration**.

* [Setup](./6.kubernetes/k8s-setup/installation.md)
* [Sample Deployment](./6.kubernetes/k8s-basic/k8s-deployment-access-outside/simplestore-deployment.yaml)
* [Guideline](./6.kubernetes/k8s-setup/doc.md)
* [K8s-dashboard](6.kubernetes/k8s-basic/k8s-dashboard/)
* [K8s-basic](./6.kubernetes/k8s-basic/)
  * [Pod](./6.kubernetes/k8s-basic/k8s-pod/)
  * [Replicaset](6.kubernetes/k8s-basic/k8s-replicaset/)
  * [deployment](./6.kubernetes/k8s-basic/k8s-dashboard/)
  * [Rolling Update](6.kubernetes/k8s-basic/rollingUpdate/)
  * [Deployment Strategies](./6.kubernetes/k8s-basic/k8s-deployment/readme.md)
  * [HPA](6.kubernetes/k8s-basic/hpa/)

---

## 7. Configuration for testing

1. [vagrant]()
2. [k3s + vagrant]()
3. [Testing]()
<!-- ## 7. Cheat Sheets

### Docker

```bash
docker ps
docker images
docker build -t app .
docker run -d -p 80:80 app
```

### Jenkins

```bash
java -jar jenkins.war
```

### Ansible

```bash
ansible --version
ansible all -m ping -i inventory.ini
ansible-playbook site.yml -i inventory.ini
```

### Kubernetes

```bash
kubectl get nodes
kubectl get pods
kubectl apply -f deployment.yaml
kubectl describe pod <pod-name>
```

---

## 8. Suggested Folder Structure

```
.
├── 1.introduction
├── 2.web-server
├── 3.nexusossrepository
├── 4.cicd
│   └── jenkins
├── 5.ansible
│   ├── Installation.md
│   ├── inventory.ini
│   ├── playbook
│   └── iac
├── 6.kubernetes
└── README.md
```

---

## 9. Common Commands

```bash
# Docker
docker compose up -d

# Ansible
ansible-playbook main.yml

# Kubernetes
kubectl get all
```

---

## 10. Tools Used 🛠

* Docker & Docker Compose
* Jenkins
* Nexus OSS
* Ansible
* Kubernetes
* Google Cloud Platform (GCP)

---

## 11. Purpose

* Study notes for DevOps class
* Hands-on practice
* Quick revision before exams
* Portfolio for DevOps learning

--- -->
---
**Author**: Toch Ratana
ISTAD Student | DevOps & Cloud Learner
