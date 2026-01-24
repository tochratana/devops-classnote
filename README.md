# DevOps Class Notes

This repository contains my **DevOps class notes**, including concepts, commands, configuration files, and hands-on practice. I use it as a **personal knowledge base** for learning, revision, and quick reference.

## 1. Introduction
**Core DevOps concepts** and overview.

**Topics**

* What is DevOps
* DevOps lifecycle
* CI vs CD
* Infrastructure as Code (IaC)
* Automation & monitoring basics

## 2. Web Server

Notes related to **web servers and deployment**.

**Topics**

* What is a web server
* Nginx / Apache basics
* Reverse proxy
* Ports & firewall basics

## 3. Nexus Repository

Nexus is used as an **artifact repository** for binaries, Docker images, and build outputs.

### Installation

* Docker Compose file: `./3.nexusossrepository/nexus/docker-compose.yaml`

```bash
docker compose up -d
```

**Notes**

* Default port: `8081`
* Used in CI/CD pipelines
* Supports Maven, Docker, npm, etc.


## 4. CI/CD Pipeline

**Continuous Integration & Continuous Deployment** concepts.


### Jenkins


* Jenkins installation
* Freestyle vs Pipeline jobs
* Jenkinsfile basics
* GitHub integration


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

* [Setup]()

## 7. Cheat Sheets

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

---

**Author**: Toch Ratana
ISTAD Student | DevOps & Cloud Learner
