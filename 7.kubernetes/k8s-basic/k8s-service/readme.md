## Note

kind of service
- headless service = 
- NodePort service = expose service using pord of Node (30000-32676)
- Cluster service = Can't acces outside, and use ingress(Configuration ingress for put domain name) for expose 
- Load Balancer
- ExternalName

```bash
kubectl api-resources # To see all api resources
```

For put domain name
```bash
ingress -> service -> deployment
```
but it don't have https so we use 
```bash
ingress (clusterIssuer, ingressClassname:nginx) -> service -> deployment
```
for put domain name with https

What is `clusterIssuer` : it's a service that get from Cert Manager. `cert manager` is manage on certification that put https.
- Certbot
- Cert manager = auto renew
- Letsencrypt

