1. Understand the core concepts: Familiarize yourself with the essentials of DevOps practices, including continuous integration/continuous deployment (CI/CD), infrastructure as code (IaC), the software development lifecycle, and containerization. Understand how these concepts contribute to the overall development lifecycle.

SDLC -> CI (Developer frequently merge theire code into shared repository) -> CD (app ready for deploy but not release to prod wait for testing) -> IAC (instead of configuration menual we just write code for automate it) -> 



2. What is Docker and why we use it ?

Docker is an open-source platform that enable developers to create deploy, and run application within lightweight, portable containers.

3. What is different between IaC and CaC ?

```
                IaC
                 │
                 ▼
Terraform
    │
    ├── Create VPC
    ├── Create Firewall Rules
    ├── Create Static IP (optional)
    ├── Create 5 Compute Engine VMs
    └── Generate SSH inventory
                 │
                 ▼
          Infrastructure Ready
                 │
                 │
                CaC
                 ▼
Kubespray (Ansible)
    │
    ├── Install containerd
    ├── Install kubeadm
    ├── Install kubelet
    ├── Install kubectl
    ├── Configure networking
    ├── Initialize control plane
    ├── Join worker nodes
    ├── Install CNI (Calico/Cilium)
    └── Configure HA
                 │
                 ▼
      Kubernetes Cluster Ready
```