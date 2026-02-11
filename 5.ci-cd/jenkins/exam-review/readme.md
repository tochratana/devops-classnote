## DevOps Exam Review Cheat Sheet

### 1. Nexus (Repository Manager)

What is Nexus?: Tool use to store artifacts, package, images.

Example:
- .jar
- .war
- .npm
- docker images
- Maven dependency

Why use Nexus?
- Central storage (ទទួលបាននៅ storage មួយដែលមាននៅ privacy, private ដែលមិនមានអ្នកណាអាចប្រើប្រាស់បាន)
- Version control for build
- CI/CD can push & pull artifact

```bash
Developer push code → Jenkins build → push artifact to Nexus → deploy from Nexus
```


### 2. Jenkins Pipeline (VERY IMPORTANT)


### 3. Kunernetes

What is Kubernetes ?

- `Kubernetes`, also known as K8s, is an open-source Container Orchestration Tool for automating deployment, scaling, and management of containerized applications.

  វាធ្វើការដូចជា អ្នកគ្រប់គ្រង (Manager) ដែលអាច៖
  - Deploy containers
  - Scale up / down
  - Self-healing (restart container ពេល crash)
  - Load balancing
  - Rolling update / rollback

- `Kubernetes clusters` Kubernetes Cluster = Environment ពេញលេញ ដែល Kubernetes ដំណើរការ ។ វាមាន machines (nodes) ច្រើន + Kubernetes components

**Cluster Structure**

1. Control Plane (Master Node)គ្រប់គ្រង cluster
- API Server → ទទួល command (kubectl)
- Scheduler → កំណត់ Pod ទៅ node
- Controller Manager → ត្រួតពិនិត្យ state
- etcd → Database រក្សាទុក cluster state

2. Worker Nodes
- រត់ Pods / Containers
- Kubelet → agent ទទួល command ពី master
- Container Runtime (Docker / containerd)
- Pods

- `Container Orchestration` provisioning, deployment, scaling, networking and load balancing. 
> Container Orchestration = ការគ្រប់គ្រង Containers ច្រើនដោយស្វ័យប្រវត្តិ

* ពេល app ធំ មាន container ច្រើន (10, 100, 1000) → មិនអាចគ្រប់គ្រងដៃបាន ❌
* ត្រូវការប្រព័ន្ធ Automation នេះហើយជា Orchestration