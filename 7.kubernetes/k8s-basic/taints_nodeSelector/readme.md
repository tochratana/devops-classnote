## Taint Node

Mark node to receive or not reveive tasks assignment (Pod will need node to run, but not on the nodes that we tainted)

When we create Pod, so kubernetes will decide which Node will run that Pod.

There are seral ways to control it : 
- Taints & Tolerations
- Node Selector
- Affinity / Anti-Affinity


```bash
kubectl get node
kubectl get node -o wide

kubectl get node --show-labels

# kubernetes.io/hostname=node4

# to see comand that taint and untaint use 
kubectl describe nodes
# Grep tain form describe
kubectl describe nodes | grep Taints

kubectl get nodes -o custom-columns=NAME:.metadata.name,TAINTS:.spec.taints

```
- Typically no taints for default worker, because worker need to run pod (worker ger job from kube scheduler for run pod)
- Typically there is a **taint for master** for prevent it running a normal job or work load to avoid overload or overwhelm which might increase the chance of server being donw.

---

Mark node to recieive or not recieve tasks assignment ( Pod will need node to run , but not on the nodes that we tainted )
```bash
# Use to taint any master
kubectl taint node node1 node-role.kubernetes.io/control-plane=:NoSchedule # មិនទទួលនៅការមកពី kube scheduler នោះទេ
# Use to untaint any master
kubectl taint node node1 node-role.kubernetes.io/control-plane- # ទទួលបានការងារពី kube scheduler

# For tainting the worker node 
kubectl taint node node5 service=disabled:NoSchedule
# untaint the worker 
kubectl taint node node5 service-
```

### 1. Taints (Block Pods From Node)
-> Taints = Mark a Node t oreject Pods 

If a node is tainted → Pods CANNOT run there (unless Pod has `toleration`).

> If Node taints, so Pod Cannot schedult
> Node tainted  ❌ → Pod cannot schedule

> Taint មិនមានការងារដែលត្រូវទទួលបានពី kube scheduler
> Untaint មានការងារដែលត្រូវទទួលបានពី kube scheduler


HA, Dabase cluster, Load balancer, 