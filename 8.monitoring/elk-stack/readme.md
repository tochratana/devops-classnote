## Note

### 1. Install Elasticsearch

Store logs and allow fast searching and analytics.
Think of it like a database optimized for searching logs.

What it does
- Store logs from applications
- Index data for fast search
- Analyze large log datasets
- Provide APIs to query logs
```bash
helm repo add elastic https://helm.elastic.co
helm repo update
```

create namespace for logging
```bash
kubectl create namespace logging
kubectl get ns


helm install elasticsearch elastic/elasticsearch \
  --namespace logging \
  --set replicas=1
```

### 2. Install Kibana (Visualization UI)

Visualize and explore logs stored in Elasticsearch.Think of it like a dashboard for logs and metrics.
- What it does
- Search logs
- Create dashboards
- Monitor errors
- Create alerts
- Analyze system behavior

Developers and DevOps engineers use Kibana to debug systems.

Add repo for kibana
```bash
helm install kibana elastic/kibana \
  --namespace logging
```

Get Pod : 
```bash
kubectl get pods -n logging

# We can use port forwarding 
kubectl port-forward svc/kibana-kibana 5601:5601 -n logging
```

### 3. Logstash (Log Processing Pipeline)

`Purpose:` Collect, transform, and send logs to Elasticsearch. Think of it like a data pipeline / log processor.
- What it does
- Collect logs from many sources
- Parse logs
- Transform log formats
- Send logs to Elasticsearch