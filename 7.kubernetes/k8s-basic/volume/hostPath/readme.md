## Note for Kubnertes Volume (Focus on HostPath)

When you define a `hostPath`, you are telling Kubernetes: "Go to the hard drive of the specific server where this container is running, find the folder at /data/logs, and map it into the container at /app/logs."



```yaml
apiVersion: v1
kind: Pod
metadata:
  name: hostpath-example
spec:
  containers:
    - name: nginx
      image: nginx
      volumeMounts:
        - name: my-volume
          mountPath: /usr/share/nginx/html
  volumes:
    - name: my-volume
      hostPath:
        path: /data/html
        type: Directory
```