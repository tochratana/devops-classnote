`Ingress` for put domain name
- kubernetes node pod `30000-32767` ![alt text](image.png)

```bash

# For run apply pod
kubectl apply -f nginx-pod.yaml
```


---

- `service` and `pod` connection deplen on label : If the Service selector does not match the pod labels, traffic goes nowhere.