### Note for deployment : 
```yml
# Clusterissuser
---
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

# deployemnt 
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

# service 
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

# ingress
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