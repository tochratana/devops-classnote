## HPA 
**Horizontal Pod Autoscaller** 
used to automatically scale up or scale down the pod based on memory and cpu comsumptions 

> Tool needed 
- Apache Benchmark used for load testing 

```bash
kubectl apply -f .
kubectl get hpa
kubectl delete hpa <name-hpa>
ab -n 10000 -c 1000 http://10.233.10.138/
```


In Hpa we can use by resources like 
- CPU
- RAM
