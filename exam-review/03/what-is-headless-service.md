`Headless Service` នៅក្នុង Kubernetes គឺជា Service មួយប្រភេទដែល មិនមាន Cluster IP ទេ។

វាអនុញ្ញាតឲ្យ client connect directly ទៅ Pod ម្នាក់ៗ តាមរយៈ DNS records។

ធម្មតា Service ទូទៅមាន IP មួយ (ClusterIP) ហើយវា load balance request ទៅ pods។

ប៉ុន្តែ Headless Service មិនមាន load balancing ទេ — វាផ្តល់ list នៃ Pod IPs ជំនួស។

1. Service ធម្មតា (ClusterIP)
Flow normally:
```bash
Client
│
▼
Service (ClusterIP)
│
├── Pod A
├── Pod B
└── Pod C
```


Client request → Service IP → Kubernetes load balance → Pod មួយ។

Example DNS:

my-service.default.svc.cluster.local → 10.96.10.20



2. Headless Service
Flow:
```bash
Client
│
▼
DNS
│
├── Pod A IP
├── Pod B IP
└── Pod C IP
```


DNS នឹង return multiple Pod IPs។

Example DNS result:

my-headless.default.svc.cluster.local


```bash
10.244.1.5
10.244.1.6
10.244.1.7
```


Client ជាអ្នក ជ្រើស Pod ដោយខ្លួនឯង។

3. YAML Example

```yaml
apiVersion: v1
kind: Service
metadata:
name: my-headless
spec:
clusterIP: None
selector:
app: myapp
ports:
- port: 80
```

> Key point:

clusterIP: None

នេះធ្វើឲ្យ Service ក្លាយជា Headless។

4. Use Case (ពេលណាប្រើ)
Headless Service ត្រូវប្រើសម្រាប់ stateful systems ដូចជា:

Databases
Message queues
Distributed systems
Example technologies:

Apache Kafka
MongoDB
Cassandra
Redis
ព្រោះ systems ទាំងនេះត្រូវការ connect ទៅ node ជាក់លាក់។

Example:
```bash
mongo-0.mongo-headless
mongo-1.mongo-headless
mongo-2.mongo-headless
```


នេះអាចប្រើជាមួយ Kubernetes StatefulSet។

5. Example DNS per Pod
podname.servicename.namespace.svc.cluster.local



Example:
```bash
mongo-0.mongo-headless.default.svc.cluster.local
mongo-1.mongo-headless.default.svc.cluster.local
mongo-2.mongo-headless.default.svc.cluster.local
```


6. Compare
FeatureClusterIP ServiceHeadless ServiceCluster IPYesNoLoad balancingKubernetesClientDNS resultSingle IPMultiple Pod IPsUse caseNormal microserviceStateful apps

💡 Short summary
Headless Service =
Service ដែល មិនមាន ClusterIP ហើយ DNS return Pod IPs directly ដើម្បីឲ្យ client connect ទៅ Pod ផ្ទាល់។

