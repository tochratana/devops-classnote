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



> **kubernetes Architecture**

<!-- Kubernetes Cluster មាន 2 ផ្នែកធំៗ -->

<!-- `Control Plane (Master Node)` -->
<!-- - គ្រប់គ្រង Cluster ទាំងមូល
- សម្រេចថា Pod រត់នៅណា និងរក្សា Desired State

Components សំខាន់ៗ
1. API Server
  - ច្រកចូលសម្រាប់ Kubernetes
  - ទទួល command ពី kubectl, CI/CD, UI
  - Validate & process request

👉 Brain Gateway

2. Scheduler
- ជ្រើស Worker Node សម្រាប់ Pod
- Based on:
  - CPU / Memory
  - Resource availability
  - Policy / Constraints

👉 Decide "Pod run where?"

3. Controller Manager
- ត្រួតពិនិត្យ Desired state vs Current state
- បើ pod បាត់ → recreate
- បើ replicas មិនគ្រប់ → create more

  Controllers:
  - Deployment Controller
  - ReplicaSet Controller
  - Node Controller
  - Job Controller

👉 Maintain system stability

4. etcd
- Key-value database
- រក្សាទុក cluster state ទាំងអស់
- Pods, nodes, configs, secrets

👉 Cluster database

`Worker Nodes`

រត់ Pods / Containers (Application)

Components
1. Kubelet
- Agent នៅលើ node
- ទទួល command ពី API Server
- Ensure container running

👉 Node manager

2. Container Runtime
- Run containers
- Example:
  - Docker
  - containerd
  - CRI-O

3. Kube Proxy
- Network & routing
- Enable Pod communication
- Load balancing

4. Pods
- Smallest unit in Kubernetes
- Contain one or more containers
- Share network + storage

👉 Where app runs

`How Kubernetes Works (Flow)`

- You run:
```bash
kubectl apply -f deployment.yaml
```

- API Server receive request
- etcd save desired state
- Scheduler choose best node
- Controller ensure pod created
- Kubelet run container
- Pod running -->

![images](https://www.simplyblock.io/wp-content/media/a7fbb2_0290c594b9244137a8858165fa1c22f0mv2.png?ver=5b20f5f757759cd9d7352268a9fdb966a45cab3f)


`Control Plane (ខួរក្បាល)`

Control Plane = កន្លែងគ្រប់គ្រង Kubernetes ទាំងមូល
វាជាអ្នក “បញ្ជា” និងកំណត់ថា Pod ត្រូវរត់នៅ Node ណា និងរក្សា state របស់ cluster។

Components សំខាន់ៗ

- Kube-apiserver -> ច្រកចូលសំខាន់ (API) ទទួល command ពី kubectl / CI/CD
- Kube-scheduler -> ជ្រើស Node សម្រាប់ Pod រត់
- Kube-controller-manager -> ត្រួតពិនិត្យ និងរក្សា desired state (បើ pod បាត់ → បង្កើតថ្មី)
- etcd -> Database រក្សាទុកទិន្នន័យ cluster ទាំងអស់
- Cloud-controller-manager -> ភ្ជាប់ Kubernetes ជាមួយ Cloud (AWS, Azure, GCP)

**សរុប៖ Control Plane = អ្នកបញ្ជា និងគ្រប់គ្រង cluster**

` Nodes (Worker Machines)`

Nodes = ម៉ាស៊ីនដែលធ្វើការងារពិត (run application)
ទទួល task ពី Control Plane ហើយរត់ Pods / Containers

Components នៅលើ Node
- Kubelet -> Agent ត្រួតពិនិត្យថា Containers ក្នុង Pod កំពុងរត់ត្រឹមត្រូវ
- Container Runtime -> Software សម្រាប់រត់ Container (Docker / containerd)Kube-proxy -> Network proxy អនុញ្ញាតឲ្យ Pods និយាយគ្នា
- Pods -> កន្លែងដែល Application រត់

`Pod (Unit តូចបំផុត)`
- Pod = ក្រុម container មួយ ឬច្រើន នៅលើ Node មួយ
- Containers ក្នុង Pod:
  - Share IP address
  - Share hostname
  - Share storage / resource

**ចង់ scale service → បង្កើនចំនួន Pods**

`Replication Controller`
- គ្រប់គ្រងចំនួន Pod ឲ្យត្រឹមត្រូវ
- បើ Pod បាត់ → បង្កើតថ្មី
- ធានាថាមាន replica ត្រឹមត្រូវជានិច្ច

`Service`
- Service = Proxy network ដែលភ្ជាប់ client ទៅ Pod ត្រឹមត្រូវ
- មិនចាំបាច់ដឹង IP Pod
- បើ Pod ផ្លាស់ទី / ត្រូវប្តូរ → Service នៅដដែល
- Load balance ទៅ Pods ច្រើន

`Kubectl`
- Command line tool សម្រាប់គ្រប់គ្រង Kubernetes
- ឧទាហរណ៍:
```bash
kubectl get pods
kubectl apply -f file.yaml
kubectl delete pod <name>
```
 
<!-- Features / Capabilities
1. Auto Scaling: Scale Pods ឡើង / ចុះ ដោយស្វ័យប្រវត្តិ (based on CPU, Memory)

2. Lifecycle Management
គ្រប់គ្រង deployment:
- Rollback version ចាស់
- Pause / Resume deployment
- Update មិន downtime

3. Declarative Model

អ្នកសរសេរ Desired State (YAML)
Kubernetes ធ្វើការនៅ background ដើម្បីរក្សា state នោះ

4. Resilience & Self-Healing 🛠️

Auto restart container

Auto recreate pod

Auto scaling

Auto placement

👉 App មិនងាយ down

5. Persistent Storage 💾

អាចភ្ជាប់ storage dynamically (PV / PVC)
Data មិនបាត់ពេល container restart

6. Load Balancing ⚖️

ចែក traffic ទៅ Pods ច្រើន
មាន internal និង external load balancing

7. DevSecOps Support 🔐

Kubernetes គាំទ្រ Security + Automation

Examples:

Secret Management → រក្សា password / token

RBAC (Role-Based Access Control) → កំណត់ permission

Network Policies → គ្រប់គ្រង network access

👉 ជួយ deploy software មានសុវត្ថិភាព និងលឿន -->


Deployment Strategis : 
- Recreate
- RollingUpdate

### 4. Ansible

