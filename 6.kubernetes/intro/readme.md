- open-source
- use for automate, scaling, management contaienized

---

Machines Spacification : 
---
Key Feature
1. Contaienr Orchestration
2. HPA - VPA (Auto scale up or scale down base on real consumption)
   - Kube-scheduler : which pod that should run
   - Kube-proxy
   - Pod -> Container -> Image

Tainted Node
NodeSelector
Affinfity


**Tool use in cluster key-value store date or contaiain config**



---

osi model
application model layer
cluster
longhon



---

Manage kubernetes object : 
1. Imperative -> like adhoc
2. Declarative -> playbook


in pod have container, normaly one container have one pod.
```bash
kubectl apply -f file.yaml
kubectl delete -f file.yaml
```



