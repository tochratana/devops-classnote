## HA (Hight Availability)
**Hight Availability** crucial(សំខាន) concept in DevOps.​ It design for operation performance, usally uptime, for a computer system, network, or application.
> It means your system keeps running even if something fails.

In DevOps, failure is normal:
- a server can crash
- a VM can go down
- a container can die
- a network can have problems

Simple example
- That server dies
- Website is down
HA
- You have 2 or more servers
- If one fails → another one takes over automatically
- Website stays online so that is Hight Availability
---
In real DevOps | Kubernetes
1. Load Balancer
  * Distributes traffic to many servers (បញ្ជូនរាល់ការ request ទៅកាន់ server ផ្សេងៗគ្នា)
  * If one server dies → traffic goes to healthy ones
  * Example : 
```bash
User → Load Balancer → Server A, Server B, Server C
```
1. Multiple replicas (Kubernetes) បើសិនជាមាន Pods ច្រើនកំពុងតែ run ហើយមានមួយមិនដំណើរការ នោះវានឹងធ្វើការ auto បង្កើតដោយខ្លួនឯងមួយទៀត
2. HA Control Plane (Kubernetes)
   In HA kubernetes Cluster
   - 3 Control plan work
   - if 1 fails -> cluster still work
> HA typology with stacked etcd

---
Imagine a website with only 1 server
Key Concepts of High Availability (HA) : 
1. Having multiple copies of components (servers, pods, databases). If one fails → another one is ready. `Example: 3 servers instead of 1`
2. Failover: Automatic switching to a healthy system when the main one fails. `Example: Server A down → traffic moves to Server B`
3. Load Balancing: Traffic is distributed across many servers to avoid overload and failure. `Example: NGINX / HAProxy / Cloud Load Balancer`
4. Health Monitoring: System constantly checks if services are healthy or not.`Example: Kubernetes liveness & readiness probes`
5. Automatic Recovery: System fixes itself automatically when something crashes. `Pod dies → Kubernetes recreates it`
6. No Single Point of Failure (SPOF): There is no single component whose failure can bring the whole system down. `Example: Multiple control planes, multiple databases`
7. Replication