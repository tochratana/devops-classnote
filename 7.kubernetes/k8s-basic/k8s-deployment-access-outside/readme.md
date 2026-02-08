## Noted

We use NodePort for only testing, but in production we use ClusterIP


`nginx-service.yaml` this is testing for deploy nginx
```yml
# clusterIP, nodePort, lb 
--- 
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-app
spec:
  selector:
    matchLabels:
      app: nginx-app
  template:
    metadata:
      labels:
        app: nginx-app
    spec:
      containers:
      - name: nginx-app-container
        image: nginx:latest
        ports:
        - containerPort: 80

# Used kubernetes service in order to expose or allow client to access your deployment 
---
apiVersion: v1
kind: Service # use service if we want another can access
metadata:
  name: mynginx-service
spec:
  type: NodePort # we use NodePort only for quick testing
  selector:
    app: nginx-app
  ports:
  - port: 80
    targetPort: 80
    nodePort: 30000 # expose port 30000 for access from browser 
```

---

We are testing for deploy simplestore app that pull image from docker hub and put domain name for it **simplestore.tochratana.com** and this is a following file and we use for deploy.
#### Structure file
```bash
k8s-deployment-access-outside/
├── deployment.yaml
├── service.yaml
├── ingress.yaml
├── clusterissuer.yaml
```



`deployment.yaml` 
```yaml
# Kubernetes Deployment
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: simplestore-app
spec:
  selector:
    matchLabels:
      app: simplestore-app
  template:
    metadata:
      labels:
        app: simplestore-app
    spec:
      containers:
        - name: simplestore-app-container
          image: tochratana/simplestore:v1.0.8
          ports:
            - containerPort: 3000
```

`service.yaml` In production, the service type is usually ClusterIP, and traffic is exposed via Ingress, not NodePort.
```yml
# Kubernetes Service is used to expose the Deployment
# and allow clients or other services to access the application
---
apiVersion: v1
kind: Service
metadata:
  name: simplestore-service
spec:
  type: ClusterIP
  selector:
    app: simplestore-app
  ports:
    - port: 3000
      # targetPort: 3000
```

`ingress.yaml`
```yml
---
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: simplestore-app
  annotations:
    cert-manager.io/cluster-issuer: "letsencrypt-prod"
    kubernetes.io/ingressClassName: "nginx"
spec:
  ingressClassName: nginx
  tls:
    - hosts:
        - simplestore.tochratana.com
      secretName: simplestore-secret-tls
  rules:
    - host: simplestore.tochratana.com
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: simplestore-service
                port:
                  number: 3000
```

`clusterissuer.yaml` is used to register and manage Let's Encrypt certificates using cert-manager.
```yml
apiVersion: cert-manager.io/v1
kind: ClusterIssuer
metadata:
  name: letsencrypt-prod
spec:
  acme:
    server: https://acme-v02.api.letsencrypt.org/directory
    email: ratanatoch58@gmail.com
    privateKeySecretRef:
      name: letsencrypt-prod
    solvers:
      - http01:
          ingress:
            class: nginx
```