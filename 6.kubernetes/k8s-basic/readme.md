`Ingress` for put domain name
- kubernetes node pod `30000-32767` ![alt text](image.png)

```bash

# For run apply pod
kubectl apply -f nginx-pod.yaml
```


---

- `service` and `pod` connection deplen on label : If the Service selector does not match the pod labels, traffic goes nowhere.



---
- hpa (deepdown on it)
- vpa

---

clusterIP only access in cluster, if we want to access outside we can use NodePort, use clusterIP only for testing, 


DNS -> FQDN (Fully Qualified Domain Name)