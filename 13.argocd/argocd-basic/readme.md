## Note

```bash
kubectl get app #get all app in default namespace
kubectl get app -A #get all application for all namespace
```



## Working with Webhook
```bash
kubectl edit secret argocd-secret -n argocd
kubectl describe secret argocd-secret -n argocd

echo -n "<screet-webhook>" | base64 # generate base64 and then

data: 
    # For github Secret we use this
    webhook.github.secret: <your-base64-value>
```




teacher
```yaml
ingress:
  enabled: true
  className: "nginx"
  annotations:
    kubernetes.io/ingress.class: nginx
    cert-manager.io/cluster-issuer: "letsencrypt-prod"
  hosts:
    - host: planting-k8s.devnerd.store
      paths:
        - path: /
          pathType: Prefix
  tls:
    - secretName: planting-k8s-tls
      hosts:
        - planting-k8s.devnerd.store
```


me
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: nginx-demo
  annotations:
    cert-manager.io/cluster-issuer: letsencrypt-prod
spec:
  tls:
    - hosts:
        - nginx-test-lg.tochratana.com
      secretName: nginx-demo-tls
  rules:
    - host: nginx-test-lg.tochratana.com
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: nginx-demo
                port:
                  number: 80
```
```yaml
ingress:
  enabled: true
  className: nginx
  annotations:
    cert-manager.io/cluster-issuer: letsencrypt-prod

  hosts:
    - host: nginx-test-lg.tochratana.com
      paths:
        - path: /
          pathType: Prefix

  tls:
    - secretName: nginx-demo-tls
      hosts:
        - nginx-test-lg.tochratana.com

service:
  name: nginx-demo
  port: 80
```