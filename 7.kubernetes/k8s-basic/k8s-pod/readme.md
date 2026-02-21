Multiple container in one pod


```bash
kubectl logs -f pod/pod-name

# Imparetive 

```

- Replicaset : control how many idtical pod as renning

```bash

# see detail about pod
kubectl describe pod multi-container-pod

# Get container that run inside pod by filter via json
kubectl get pod multi-container-pod -o jsonpath='{.spec.containers[*].name}'

# Logs from busybox container
kubectl logs multi-container-pod -c busybox-container

# Logs from nginx container
kubectl logs multi-container-pod -c nginx-container


# Exec into nginx container
kubectl exec -it multi-container-pod -c nginx-container -- /bin/bash

# Exec into busybox container
kubectl exec -it multi-container-pod -c busybox-container -- sh

```


1. Basic Pod Commands
```bash
# List all pods in current namespace
kubectl get pods

# List pods with more details (node, IP, etc.)
kubectl get pods -o wide

# List pods in all namespaces
kubectl get pods -A
```

2. Create Pod
```bash
# Create pod from YAML file
kubectl apply -f nginx-pod.yaml

# Create a pod directly (no controller)
kubectl run my-nginx --image=nginx:1.22.1 --restart=Never
# --restart=Never → makes sure it creates a Pod, not a Deployment.

# replaces the entire resource with what’s in the YAML file.
kubectl replace -f nginx-pod.yaml
```

3. Pod Details & Debugging
```bash
# Describe a pod (very important for debugging)
kubectl describe pod my-nginx

# Get pod logs
kubectl logs my-nginx

# Follow pod logs (like tail -f)
kubectl logs -f my-nginx

# Execute command inside a pod
kubectl exec -it my-nginx -- /bin/bash
```

4. Delete Pod
```bash
# Delete a pod
kubectl delete pod my-nginx

# Force delete pod (if stuck in Terminating)
kubectl delete pod my-nginx --grace-period=0 --force
```

5. Pod with Namespace
```bash
# List pods in a specific namespace
kubectl get pods -n kube-system # kube-system is a name of namespace

# Delete pod in a namespace
kubectl delete pod my-nginx -n dev


# create name space 
kubectl create ns namespace-demo
```


6. Pod Status / Filtering
```bash
# Watch pod status in real-time
kubectl get pods -w

# Get only running pods
kubectl get pods --field-selector=status.phase=Running
```

7. YAML & Output Formats
```bash
# Show pod YAML
kubectl get pod my-nginx -o yaml

# Show pod JSON
kubectl get pod my-nginx -o json
```

8. Quick Tip (Exam / Real-world)
```bash
# Get pod name quickly
kubectl get pods -o name

# Delete all pods in a namespace
kubectl delete pods --all -n dev
```