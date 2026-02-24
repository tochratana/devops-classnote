## Helm Custom Chart

```bash
# Create Custom Chart
helm create <name-custom-chart>

# For apply template

# apply template custom-chart with prod
helm template custom-chart  --values custom-chart/values.yaml

# apply template with stagging
helm template custom-chart  --values custom-chart/stag-values.yaml

# after update or config chart/ for install dependency, we can use 
helm dependency update
```


1. `Chart.yaml` (Chart Metadata) : This is the main configuration file of your chart. like `package.json` in Node.js or `pom.xml` in Maven.
```yaml
apiVersion: v2
name: custom-chart
version: 0.1.0
appVersion: "1.0"
description: My custom Helm chart
```
2. `values.yaml` This file stores default values for your templates. like variable file
```yaml
replicaCount: 2

image:
  repository: nginx
  tag: latest
  pullPolicy: IfNotPresent

service:
  type: ClusterIP
  port: 80
```
3. `templates/` kubernetes resource templates
4. `charts/` older contains dependency charts.


- Helm can automatically install them.
- Dependencies are defined in Chart.yaml:
```yaml
# Example we want to install redis
dependencies:
  - name: redis
    version: 17.0.0
    repository: https://charts.bitnami.com/bitnami
```
5. `.helmignore` like `.gitignore`