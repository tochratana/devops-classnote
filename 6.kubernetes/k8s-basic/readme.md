`Ingress` for put domain name
- kubernetes node pod `30000-32767` ![alt text](image.png)

```bash

# For run apply pod
kubectl apply -f nginx-pod.yaml
```


---

- `service` and `pod` connection deplen on label : If the Service selector does not match the pod labels, traffic goes nowhere.



---
- HPA (Horizontal Pod Autoscaler) → scales number of pods
- VPA (Vertical Pod Autoscaler) → scales resources of a pod (CPU / memory)

---

clusterIP only access in cluster, if we want to access outside we can use NodePort, use clusterIP only for testing, 

DNS -> FQDN (Fully Qualified Domain Name)

---

What is Height Level of kubernets object ? -> ទំនាក់ទំនងនីមួយៗ ដែលយើងធ្វើការនៅក្នុង kubernetes របស់យើង


> In Kubernetes, you use different Service types to expose application ports, depending on whether the exposure is needed only within the cluster or to external traffic. 
The primary service types for exposing ports are:
1. ClusterIP (Default): Exposes the service on an internal IP address only reachable from within the cluster. This is the default and is ideal for communication between different microservices or components inside your cluster.
2. NodePort: Exposes the service on a static port (default range 30000–32767) on every Node's IP address. This makes the service accessible from outside the cluster using <NodeIP>:<NodePort>. It is often used in development/testing environments or on-premises setups without a cloud load balancer.
3. LoadBalancer: This is the standard way to expose public-facing applications in a cloud environment. It automatically provisions an external load balancer from your cloud provider (like AWS, Azure, GCP), which in turn routes traffic to the service on a stable external IP or DNS name. It acts as a superset of the NodePort service type.
4. Ingress: While technically a different resource type, Ingress is commonly used with a ClusterIP service to provide more advanced HTTP/HTTPS routing, load balancing, and SSL termination based on hostnames or URL paths, often using a single external IP address or load balancer.
5. ExternalName: A special type that maps a service to an external DNS name by returning a CNAME record. No proxying is involved; it's useful for accessing external services as if they were internal to the cluster. 
