# Deploying Microservices in Kubernetes

---

## Core Resources Per Service

Each microservice needs **3 Kubernetes resources**:

```
1. Deployment   →  runs your container (Pods)
2. Service      →  stable DNS / network endpoint
3. Ingress      →  expose to internet (frontend/API only)
```

---

## Recommended Folder Structure

```
k8s/
├── frontend/
│   ├── deployment.yaml
│   └── service.yaml
├── backend-api/
│   ├── deployment.yaml
│   └── service.yaml
├── product-service/
│   ├── deployment.yaml
│   └── service.yaml
└── ingress.yaml
```

---

## Step 1 — Deployment

```yaml
# k8s/backend-api/deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: backend-api
  namespace: default
spec:
  replicas: 2
  selector:
    matchLabels:
      app: backend-api
  template:
    metadata:
      labels:
        app: backend-api
    spec:
      containers:
        - name: backend-api
          image: your-registry/backend-api:v1.0
          ports:
            - containerPort: 8080
          env:
            - name: PRODUCT_SERVICE_URL
              value: "http://product-service:3000"  # talk to another service via DNS
```

---

## Step 2 — Service (ClusterIP)

```yaml
# k8s/backend-api/service.yaml
apiVersion: v1
kind: Service
metadata:
  name: backend-api       # this becomes the DNS name
  namespace: default
spec:
  selector:
    app: backend-api
  ports:
    - port: 8080
      targetPort: 8080
  type: ClusterIP          # reachable only inside the cluster
```

---

## Step 3 — Ingress (Expose to Internet)

```yaml
# k8s/ingress.yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: app-ingress
spec:
  rules:
    - host: myapp.com
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: frontend
                port:
                  number: 80
          - path: /api
            pathType: Prefix
            backend:
              service:
                name: backend-api
                port:
                  number: 8080
```

---

## Step 4 — Apply Everything

```bash
# Apply all manifests at once
kubectl apply -f k8s/

# Verify
kubectl get deployments
kubectl get services
kubectl get pods
kubectl get ingress
```

---

## Traffic Flow

```
Internet
    │
    ▼
[ Ingress ]
    │
    ├──► [ frontend Service ]        ──► [ frontend Pods ]
    │
    └──► [ backend-api Service ]     ──► [ backend-api Pods ]
                │
                │  http://product-service:3000
                ▼
         [ product-service Service ] ──► [ product-service Pods ]
```

---

## Service Communication (DNS Names)

| From | To | URL to use |
|------|----|-----------|
| backend-api | product-service (same ns) | `http://product-service:3000` |
| backend-api | auth-service (different ns) | `http://auth-service.auth-ns:8081` |

---

## Deployment Checklist

| Step | Action |
|------|--------|
| 1 | Build & push Docker image to registry |
| 2 | Write `Deployment` for each microservice |
| 3 | Write `Service` (ClusterIP) for each microservice |
| 4 | Use **service DNS names** in env vars to connect services |
| 5 | Add `Ingress` to expose frontend/API to internet |
| 6 | `kubectl apply -f k8s/` |
| 7 | `kubectl get pods` to verify all pods are Running |
