## Node Selector

Running deployment on the node that we have select.

### 1. Node selector vai using nodeSelector/kubernetes.io/hostname: node5

We can customize like this for spacific which node that we want to use for run pod.
```yaml
apiVersion: apps/v1
kind: Deployment
metadata: 
  name: nginx-nodeselector-dpl
spec: 
  replicas: 4
  selector: 
    matchLabels: 
      app: nginx-nodeselector-app
  template: 
    metadata: 
      labels: 
        app: nginx-nodeselector-app
    spec: 
      nodeSelector: 
        kubernetes.io/hostname: node5 # use it here for selecto node vai node name
      containers:
      - name: nginx-nodeselector-app
        image: nginx:latest
        ports: 
          - containerPort: 80
```

### 2. Node Selector vai disktype

if in our machiens we don't have distype we create labels for it and then call it vai labels for know which node that we want to run
```bash
kubectl label nodes node1 disktype=ssd
kubectl label nodes node5 disktype=ssd
kubectl label nodes node4 disktype=ssd
```

some of configuration also have disktype, example `disktype=hddkubclear` so we can selete node for run it vai this

```yaml
apiVersion: apps/v1
kind: Deployment
metadata: 
  name: nginx-nodeselector-dpl
spec: 
  replicas: 4
  selector: 
    matchLabels: 
      app: nginx-nodeselector-app
  template: 
    metadata: 
      labels: 
        app: nginx-nodeselector-app
    spec: 
      nodeSelector: 
        disktype: ssd # use it here
      containers:
      - name: nginx-nodeselector-app
        image: nginx:latest
        ports: 
          - containerPort: 80
```

