#### Some of command use when start kubernetes successfull

```bash
kubectl get node
kubectl get node -o wide
kubectl get pod
kubectl get pod -A

# Show all the service that run inside cluster
kubectl get all -A

# To access dashbord, we use nodeport
kubectl get svc -n kube-system

# To see all resource in kube-system
kubectl get all -n kube-system

# edit dashboard change type: ClusterIp -> NodePort
kubectl edit service/kubernetes-dashboard -n kube-system

```
after run this we will get dashbaord but it require login
#### after get dashboard so we can't login, so start from generate token for loginls
```bash
# create namespace for kubernates
# we create namespace for manage any resource
kubectl create ns kubernetes-dashboard

# we create file k8s-svcacc-clusterrolebinding.yaml for apply configuration
kubectl apply -f k8s-svcacc-clusterrolebinding.yaml

# to create the token from the user 


# For access dashbaord : 
https://34.87.70.98:30767  # with https 


kubectl get clusterrole

kubectl get clusterrolebinding
```

#### Put domain to kubernetes dashbaord 
```bash

# check all service in namespace kube-system
kubectl get svc -n kube-system

# make sure kubernetes-dashboard using ClusterIP not NodePort
kubectl edit svc kubernetes-dashboard -n kube-system

# For search in vim we can use /type
/type
# test DNS -> IP 
nslookup kubernetes-dashboard.tochratana.com

```

### IMPORTANT
* If you removed the taint (untaint) for all the masters you can use master IP for the dns record
* If not, use IP of workers instead
