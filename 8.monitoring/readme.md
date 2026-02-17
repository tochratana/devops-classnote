Continuous Monitoring
- Continuous Monitoring
- What is a Monitoring tool?
- Working with kube-prometheus-stack
- Accessing the monitoring dashboard for better insight
- Adding alerts for the telegram alerts

![alt text](image.png)
![alt text](image-1.png)
---

**Continuous Monitoring (ការត្រួតពិនិត្យជាប់ៗគ្នា)**

Continuous Monitoring គឺជាការតាមដានប្រព័ន្ធ (System), Server, Application ឬ Infrastructure ជាបន្តបន្ទាប់ 24/7។

ដើម្បីដឹងថា៖
- ប្រព័ន្ធដំណើរការល្អ ឬអត់
- មាន Error ឬ Problem ឬអត់
- CPU / RAM / Disk / Network ប្រើប៉ុន្មាន
- Service ណាធ្លាក់ (down) ឬយឺត

គោលបំណង៖ រកបញ្ហាឲ្យឃើញលឿន ហើយជួសជុលមុនពេល User មានបញ្ហា

---

**What is a Monitoring Tool? (Monitoring Tool គឺអ្វី?)**

Monitoring Tool គឺជា Software សម្រាប់៖
- Collect Metrics (CPU, Memory, Network, Pods, Nodes...)
- Visualize ជា Graph / Dashboard
- Alert ពេលមានបញ្ហា (Server down, CPU 100%, Pod crash...)

ឧទាហរណ៍ Tools ពេញនិយម
- Prometheus → ប្រមូល Metrics
- Grafana → បង្ហាញ Dashboard
- Alertmanager → ផ្ញើ Alert (Telegram, Email, Slack)

---

**Working with kube-prometheus-stack**

kube-prometheus-stack គឺជា Package មួយក្នុង Kubernetes ដែលមានរួចជាស្រេច៖
- Prometheus
- Grafana
- Alertmanager
- Node Exporter
- Kubernetes Monitoring

វាជួយឲ្យយើង Install Monitoring លើ Kubernetes ងាយៗ ដោយប្រើ Helm តែម្តង

អ្វីដែលវាធ្វើ
- Monitor Nodes, Pods, Containers
- Collect Metrics ពី Kubernetes
- Create Dashboard អូតូម៉ាទិច
- Support Alert

---

**Accessing the Monitoring Dashboard (ចូលមើល Dashboard)**

បន្ទាប់ពី install kube-prometheus-stack អ្នកអាចចូលមើល Grafana Dashboard ដើម្បីឃើញ៖

- CPU / Memory Usage
- Pod Health
- Node Status
- Network Traffic
- Error Rate

Dashboard ជួយឲ្យយើងយល់ស្ថានភាព System ពិតៗ (Real-time insight)

---

**Adding Alerts for Telegram (ផ្ញើ Alert ទៅ Telegram)**

យើងអាចកំណត់ Alert ដើម្បីឲ្យប្រព័ន្ធផ្ញើសារ Telegram ពេលមានបញ្ហា ដូចជា៖

- Pod Crash
- CPU > 80%
- Node Down
- Service Not Responding

ដំណើរការសង្ខេប
- Create Telegram Bot (BotFather)
- ទទួលបាន BOT_TOKEN
- ទទួលបាន CHAT_ID
- Configure នៅក្នុង Alertmanager
- កំណត់ Alert Rules (CPU, Memory, Pod...)

ពេលមានបញ្ហា → Telegram នឹងផ្ញើសារ Auto