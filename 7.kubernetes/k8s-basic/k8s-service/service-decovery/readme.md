## Service and Service Communicate

How each service can communicate between service and service (pod-to-pod) in Kubernetes Cluster.

---

## 1. ClusterIP (The Default & Most Common)

Every Service gets a stable **ClusterIP** — a virtual IP address only reachable **inside** the cluster.

```
Frontend Pod  -->  ClusterIP (Service)  -->  Backend Pod(s)
```

- Kubernetes automatically load-balances requests across all healthy Pods behind the Service.
- The ClusterIP never changes, even if Pods are restarted or replaced.

---

## 2. DNS-Based Service Discovery (Recommended)

Kubernetes ships with **CoreDNS**, which automatically gives every Service a DNS name.

### DNS Format

```
<service-name>.<namespace>.svc.cluster.local
```

### Shortcut Rules

| Situation | How to call |
|-----------|-------------|
| Same namespace | `http://service-name` |
| Different namespace | `http://service-name.namespace` |
| Full FQDN | `http://service-name.namespace.svc.cluster.local` |

### Example

```
backend-api Service  (namespace: default)
  → DNS name: backend-api.default.svc.cluster.local
  → Short name: backend-api
```

Frontend calling backend:
```javascript
const res = await fetch("http://backend-api:8080/api/products");
```

---

## 3. Communication Flow

```
┌──────────────┐   DNS lookup    ┌──────────────┐
│ Frontend Pod │ ─────────────► │   CoreDNS    │
│              │ ◄───────────── │              │
│ http://backend              returns ClusterIP │
└──────┬───────┘                └──────────────┘
       │
       │  HTTP Request to ClusterIP
       ▼
┌──────────────────┐   routes to   ┌──────────────┐
│ Backend Service  │ ────────────► │ Backend Pod  │
│  (ClusterIP)     │               │              │
└──────────────────┘               └──────────────┘
```

---

## 4. Environment Variables (Alternative)

Kubernetes also injects env vars into every Pod for existing Services:

```bash
BACKEND_API_SERVICE_HOST=10.96.0.5
BACKEND_API_SERVICE_PORT=8080
```

> ⚠️ **Limitation**: Only Services that existed *before* the Pod started are injected.  
> **DNS is preferred** because it works dynamically without restarting Pods.

---

## 5. Service Types Summary

| Type | Reachable From | Use Case |
|------|---------------|----------|
| `ClusterIP` | Inside cluster only | Service-to-Service (default) |
| `NodePort` | Outside via Node IP | Dev / testing |
| `LoadBalancer` | External (cloud LB) | Expose to internet |
| `ExternalName` | Inside cluster | Proxy to external DNS |

---

## 6. Example Manifest

**backend-service.yaml**
```yaml
apiVersion: v1
kind: Service
metadata:
  name: backend-api        # DNS name = backend-api
  namespace: default
spec:
  selector:
    app: backend            # Targets pods with label app=backend
  ports:
    - port: 8080            # Port exposed by the Service
      targetPort: 8080      # Port on the Pod
  type: ClusterIP           # Only reachable inside the cluster
```

**frontend calling backend (same namespace):**
```bash
curl http://backend-api:8080/health
```

**frontend calling backend (different namespace):**
```bash
curl http://backend-api.default.svc.cluster.local:8080/health
```

---

## 7. Key Takeaways

1. Use **DNS names** (`http://service-name`) — simplest and most reliable.
2. **Same namespace** → `http://service-name`
3. **Different namespace** → `http://service-name.namespace`
4. The Service is a **stable endpoint** (load balancer) in front of your Pods.
5. Pods come and go, but the **Service IP/DNS stays constant**.
6. CoreDNS resolves all names inside the cluster automatically.