## Note

- For configuration testing : https://github.com/conradwt/metallb-test/blob/main/metallb-address-pool.yaml

```yaml
kind: L2Advertisement
spec:
  ipAddressPools:
  - my-ip-pool
```

| Field | Meaning |
|---|---|
| `kind: L2Advertisement` | Uses Layer 2 (ARP/NDP) mode to advertise IPs |
| `ipAddressPools` | Links to the pool above |

**What it does:** Tells MetalLB *how* to announce those IPs to the network. In L2 mode, a node **responds to ARP requests** for those IPs, making the network think the IP lives on that node. Traffic hits that node first, then Kubernetes routes it to the correct pod.

---

### How They Work Together
```bash
Client Request
      │
      ▼
192.168.1.24x  ◄── ARP announced by a K8s node (L2Advertisement)
      │
      ▼
  K8s Node  ──► kube-proxy ──► Service ──► Pod(s)
```