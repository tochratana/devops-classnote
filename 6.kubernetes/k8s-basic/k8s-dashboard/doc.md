#### Some of command use when start kubernetes successfull

```bash
kubectl get node
kubectl get node - o wide
kubectl get pod
kubectl get pod -A

# Show all the service that run inside cluster
kubectl get all -A

# To access dashbord, we use nodeport
kubectl get svc -n kube-system

# edit dashboard change type: ClusterIp -> NodePort
kubectl edit service/kubernetes-dashboard -n kube-system

after run this we will get dashbaord but it require login
```

#### after get dashboard so we can't login, so start from generate token for login
```bash
# create namespace for kubernates
# we create namespace for manage any resource
kubectl create ns kubernetes-dashboard

# we create file k8s-svcacc-clusterrolebinding.yaml for apply configuration
kubectl apply -f k8s-svcacc-clusterrolebinding.yaml

# to create the token from the user 
kubectl -n kubernetes-dashboard create token admin-user --duration=24h
```