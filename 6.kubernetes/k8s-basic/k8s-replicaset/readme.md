```bash
# create replicaset 
kubectl apply -f replicaset-name-file.yaml

# run command get pod also see replicaset that create
kubectl get pod

# for list all replicaset 
kubectl get rs
kubectl get replicaset


# watch command for see realtime
watch kubectl get pod

# if we delete pod it will be auto create 

# delete replicaset for don't auto create 
kubectl delete -f nginx-replicaset.yaml
```