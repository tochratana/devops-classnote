### 1. Stateless Application

Stateless មានន័យថា៖
Application មិនរក្សាទុក state (data/session) នៅក្នុង pod ឬ server របស់វាទេ។
> រាល់ request គឺ independent ពី request មុន។
> Pod មួយណាក៏អាច handle request បានដូចគ្នា។

Example
```bash
Frontend Website
REST API
Authentication gateway
Load balancer
```
ឧទាហរណ៍៖

User request:
```bash
User -> Pod A -> Response
User -> Pod B -> Response
```
Pod A ឬ Pod B មិនចាំបាច់ដឹង request មុនទេ។

Example Real World

Frontend React website:
```bash
User -> Kubernetes Service -> React Pod
```
Pod ត្រូវ restart ក៏មិនប៉ះពាល់អ្វីទេ។

Kubernetes Resource ប្រើ ជាទូទៅប្រើ`Deployment`
Example:
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: web-app
spec:
  replicas: 3
```
> Kubernetes អាច
```bash
scale pods
restart pods
replace pods
```
ដោយ មិនបាត់ data។

### 2. Stateful Application

Stateful មានន័យថា៖
Application ត្រូវរក្សាទុក state ឬ data។
> Pod នីមួយៗមាន identity និង storage ផ្ទាល់ខ្លួន។

Example
```bash
Database
Kafka
Redis
Elasticsearch
```
ឧទាហរណ៍៖
```bash
Database Pod 0 -> data1
Database Pod 1 -> data2
Database Pod 2 -> data3
```
Pod ទាំងនេះ មិនអាចប្តូរគ្នាបាន។
Example Real World

Database cluster:
```bash
mysql-0
mysql-1
mysql-2
``
Pod mysql-0 មាន disk:
```bash
/data/mysql
```
បើ pod restart
> Kubernetes នឹង attach disk ចាស់វិញ។

Kubernetes Resource ប្រើ
StatefulSet

Example:
```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mysql
spec:
  serviceName: mysql
  replicas: 3
```
Kubernetes នឹង create:
```bash
mysql-0
mysql-1
mysql-2
```
### 3. Difference Summary
| Feature      | Stateless     | Stateful |
| ------------ | ------------- | -------- |
| Data storage | No             | Yes    |
| Pod identity | Random         | Fixed  |
| Pod name     | Random        | Ordered  |
| Scaling      | Easy          | Harder   |
| Use case     | API, frontend | Database |
