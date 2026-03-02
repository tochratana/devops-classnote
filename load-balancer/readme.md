## Working with load balancer



### 1. Key Terms

| Term                        | Meaning                                           | When to Use                                              |
| --------------------------- | ------------------------------------------------- | -------------------------------------------------------- |
| **Bare Metal**              | Physical server (real machine)                    | Own servers, labs, on-prem                               |
| **Minikube / k3s local**    | Local single-node cluster                         | Learning/testing                                         |
| **On-Prem**                 | Your company/server infrastructure                | Enterprise labs, private cloud                           |
| **GCP / Cloud provider LB** | Cloud-managed LoadBalancer                        | GKE (managed Kubernetes)                                 |
| **MetalLB**                 | LoadBalancer for bare metal/self-managed clusters | Bare metal, on-prem, k3s, kubeadm, Kubespray on cloud VM |


### 2. How LoadBalancer Works

Cloud (GKE / AWS / Azure)
```
Service (LoadBalancer)
       ↓
Cloud Provider LB
       ↓
Public IP → Internet
```
- Automatic, production-ready

Bare Metal / Local / Self-Managed
```
Service (LoadBalancer)
       ↓
MetalLB
       ↓
Assigned IP → Network
```

- Simulates cloud LB
- Works for learning/testing

### 3. Choosing What to Use
| Environment                                  | Use LB  | Notes                               |
| -------------------------------------------- | ------- | ----------------------------------- |
| GKE (cloud managed)                          | GCP LB  | Don’t use MetalLB in production     |
| Self-managed on GCP VM (Kubespray / kubeadm) | MetalLB | Cloud LB not automatic              |
| Local k3s / Minikube                         | MetalLB | For practice only                   |
| Bare Metal / On-Prem                         | MetalLB | Learning or production if own infra |

### 4. Commands for MetalLB Installation
Step 1: Add Helm Repo
```bash
helm repo add metallb https://metallb.github.io/metallb
helm repo update
```
Step 2: Install MetalLB in metallb-system namespace
```bash
helm install metallb metallb/metallb --namespace metallb-system --create-namespace
```
Step 3: Check pods
```bash
kubectl get pods -n metallb-system
```

### 5. MetalLB Configuration Example

Layer2 Mode with IP Pool

Create a file metallb-config.yaml:
```yaml
apiVersion: metallb.io/v1beta1
kind: IPAddressPool
metadata:
  name: my-ip-pool
  namespace: metallb-system
spec:
  addresses:
  - 192.168.1.240-192.168.1.250
---
apiVersion: metallb.io/v1beta1
kind: L2Advertisement
metadata:
  name: my-l2-ad
  namespace: metallb-system
spec:
  ipAddressPools:
  - my-ip-pool
```
and then apply
```bash
kubectl apply -f metallb-config.yaml
```
then create load balancer 
```yaml
apiVersion: v1
kind: Service
metadata:
  name: my-app
spec:
  selector:
    app: my-app
  type: LoadBalancer
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
```
### 6. Notes for GCP

- If using GKE → use cloud LoadBalancer, no MetalLB needed.
- If using VM + Kubespray → MetalLB needed, behaves like Bare Metal.
  - For external access in GCP VM
  - Allow firewall rules for the IP range in MetalLB pool
  - Ensure IPs are within your VPC subnet

### 7. Learning Tips

1. Practice both setups:
- Local k3s + MetalLB → learning
- GCP Cloud LB → production behavior
2. Compare EXTERNAL-IP:
- `<pending>` → MetalLB not configured
- IP assigned → MetalLB or cloud LB working
3. Remember TL;DR:
- Cloud managed = automatic LB
- Self-managed / local = MetalLB
4. Extra practice: Try NodePort, LoadBalancer, and Ingress, and see the difference.