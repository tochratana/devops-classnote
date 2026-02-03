#### NOTE related to deployment strategies

```bash
kubectl apply -f . 
kubectl edit svc demo-svc

kubectl scale deploy demo-green-app --replicas=3
```