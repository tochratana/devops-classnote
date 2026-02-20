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