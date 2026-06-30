## Node

### 1. Taints (Block Pods From Node)
-> Taints = Mark a Node to reject Pods

If a node is tainted → Pods CANNOT run there (unless Pod has `toleration`).

> If Node taints, so Pod Cannot schedult
> Node tainted  ❌ → Pod cannot schedule

**HA, Dabase cluster, Load balancer,**

### Different between Node Selector and Node Affinity 


Node Affinity is better because it is more flexible, supports soft and hard rules, and is suitable for production environments. NodeSelector is simpler but limited and mainly used for basic scenarios.
![node](https://i.ytimg.com/vi/rX4v_L0k4Hc/maxresdefault.jpg)

