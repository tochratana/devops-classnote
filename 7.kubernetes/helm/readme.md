## Note for learn helm


 Helm -> package manager for kubernetes
 fish -> sudo apt update, sudo apt install fish -y



 tolerations : when you tain  the node, 

 use for : 
 - Deploy service
 - for more complicated (Microservices)
 - database cluster, monitoring tools
 - multiple cluster
 - 
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



```bash
helm template nginx-chart
helm install nginx-release nginx-chart

helm upgrade nginx-release nginx-chart

# if it doesn't exist, it will install the new release
# if it's already exists, it will upgrade instead
helm install nginx-release nginx-chart - - upgrade
helm history nginx-release # to show the timestamp for the update and revision id

helm rollback nginx-release
helm rollback nginx-release 3

# delete install 
helm uninstall nginx-release

# to package your chart
helm package nginx-chart
# we will get the zipped .tgz which later on can be pushed to your registery
```