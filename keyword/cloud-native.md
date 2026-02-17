## តើពាក្យថា cloud Native មានន័យដូចម្តេច?

- [resource-pooling-architecture-in-cloud-computing](https://www.geeksforgeeks.org/devops/resource-pooling-architecture-in-cloud-computing/)
- [cloud-native-architecture](https://www.geeksforgeeks.org/cloud-computing/cloud-native-architecture/)
- [cloud native application](https://intellipaat.com/blog/cloud-native-applications/)

Cloud Native មានន័យថា  វិធីសាស្រ្តបង្កើត និងដំណើរការ Application ដែលរចនាឡើងសម្រាប់ Cloud ដោយផ្ទាល់ មិនមែនយក App ធម្មតាទៅដាក់លើ Cloud ប៉ុណ្ណោះទេ។

វាធ្វើឲ្យ App:
- Scale ងាយ (ឡើង/ចុះ user ច្រើនបាន)
- Stable និង Reliable
- Deploy លឿន
- Auto-healing (ខូច → restart auto)

គន្លឹះសំខាន់ៗរបស់ Cloud Native

1. Containers
  App ត្រូវដំណើរការក្នុង Container ដូចជា Docker
  → ងាយ Deploy និង Run គ្រប់ Environment

2. Microservices Architecture
  App មួយបំបែកជា Services តូចៗ
  → ងាយ Maintain, Scale, Update

3. Dynamic Orchestration
  ប្រើ Kubernetes ដើម្បីគ្រប់គ្រង Containers
  → Auto scale, auto restart, load balance

4. DevOps & CI/CD
  Build → Test → Deploy Auto
  → Release លឿន និង Safe

5. Observability (Monitoring + Logging)
  តាមដាន System, Logs, Metrics

Tools ពេញនិយមក្នុង Cloud Native
- Docker → បង្កើត Container
- Kubernetes → គ្រប់គ្រង Container
- Prometheus → Monitoring Metrics
- Grafana → Dashboard
- Cloud Native Computing Foundation → អង្គការគាំទ្រ Cloud Native Projects

![cloud-native-tools](https://intellipaat.com/blog/wp-content/uploads/2023/07/Cloud-Native-Tools.png)
1. Kubernetes- Automating the deployment, scaling, and maintenance of containerized applications is Kubernetes, an open-source platform for container orchestration.
2. Docker- Developing and running distributed programs in containers is made possible by the open-source platform Docker.
3. Prometheus- It is a free toolset for monitoring and alerting that facilitates the gathering and visualization of information from many systems.
4. Istio- It is a platform for managing and securing service mesh-based applications that are open-source.
5. Helm- Applications can be managed, installed, and upgraded with the help of Helm, a package manager for Kubernetes.
6. Envoy- It is a free and open-source edge and service proxy that facilitates the management and security of traffic between microservices.