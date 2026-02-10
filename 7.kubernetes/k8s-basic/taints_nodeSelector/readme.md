## Node

### 1. Taints (Block Pods From Node)
-> Taints = Mark a Node t oreject Pods 

If a node is tainted → Pods CANNOT run there (unless Pod has `toleration`).

> If Node taints, so Pod Cannot schedult
> Node tainted  ❌ → Pod cannot schedule




**HA, Dabase cluster, Load balancer,**