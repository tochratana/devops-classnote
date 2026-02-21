## Note for learn helm

```bash
# Download helm script
curl -fsSL -o get_helm.sh https://raw.githubusercontent.com
# add permission
chmod 700 get_helm.sh
# create file
./get_helm.sh
# check version
helm version

# Create Chart:
helm create myapp

# Install Chart:
helm install myapp ./myapp

# Upgrade:
helm upgrade myapp ./myapp

# Uninstall:
helm uninstall myapp

# List releases:
helm list
```



- [working with helm repo](https://www.devopsschool.com/blog/openshift-how-to-add-helm-repository-in-openshift/)
```bash
# Add bitnmi repo from helm to kubernetes
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update # update repo

# install
helm install <name-folder>
helm install my-nginx bitnami/nginx
```