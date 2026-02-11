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

---

### 2. Jenkins Pipeline (VERY IMPORTANT)

---

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



> **Feature សំខាន់ៗ របស់ Kubernetes**

`Scale – Heal – Balance – Update – Discover – Secure – Schedule`
- Container Orchestration
  - Kubernetes គ្រប់គ្រង container ច្រើនដោយស្វ័យប្រវត្តិ
  - Deploy containers
  - Manage lifecycle
  - Run app across many nodes
- Auto Scaling
  - អាចបង្កើន / បន្ថយ Pods ដោយស្វ័យប្រវត្តិ
  - Types:
    - Manual scaling → kubectl scale
    - Auto scaling (HPA) → based on CPU / Memory
    - -> Traffic ច្រើន → Scale up
    - -> Traffic តិច → Scale down
- Self-Healing
  - Kubernetes ជួសជុលខ្លួនឯង
  - Container crash → Restart
  - Pod fail → Recreate
  - Node down → Move pod to another node
  - -> App មិនងាយ down
- Load Balancing
  - ចែក traffic ទៅ Pods ច្រើន
    - Prevent overload
    - Improve performance
    - High availability
  - ប្រើ Service (ClusterIP / NodePort / LoadBalancer)
- Rolling Updates & Recreate (`deployment strategies`)
  - Update app មិន downtime
- Service Discovery
  - Pods និយាយគ្នាតាម DNS name
    - No need IP hardcode
    - Use Service name
- Storage Orchestration (volume)
  - Manage storage automatically
    - Persistent Volume (PV)
    - Persistent Volume Claim (PVC)
    - Support NFS, Cloud storage, local disk
- Configuration & Secret Management
  - Store config separate from code
    - ConfigMap → normal config
    - Secret → password, token, API key
- Automatic Scheduling
  - Scheduler ជ្រើស node ដែលសមស្រប
  - Based on:
    - CPU / Memory
    - Resource availability
    - Constraints
- High Availability (HA)
  - App run បានទោះ node មួយ down
    - Multiple replicas
    - Failover support
    - No single point of failure