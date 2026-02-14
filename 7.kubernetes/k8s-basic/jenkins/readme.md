## Kubernetes + Jenkins

Install and Setup
1. Create namespaces
```bash
kubectl create namespace jenkins
kubectl create namespace jenkins-demo
```
2. Create Helm values file
```yaml
cat > values-demo.yaml <<'EOF'
controller:
  adminUser: admin
  adminPassword: admin123      # demo only — change in real use
  serviceType: LoadBalancer
  persistence:
    enabled: true
    storageClass: standard
    size: 8Gi
agent:
  enabled: true
EOF
```
3. Add Helm repo and install Jenkins
```bash
helm repo add jenkinsci https://charts.jenkins.io
helm repo update
helm install jenkins jenkinsci/jenkins -n jenkins -f values-demo.yaml
# wait until controller is ready:
kubectl -n jenkins rollout status deployment/jenkins
```
4. Get Jenkins URL & admin password (kubectl)
```bash
# Get service URL
kubectl service jenkins -n jenkins --url

# Get admin password
kubectl get secret jenkins -n jenkins -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode; echo
```