## Noted : 
## 1. Run the playbook to create 4, 5 machines
clone git repo
```bash
git clone https://github.com/kubernetes-sigs/kubespray.git
rm -rf .git
rm -rf .github
rm -rf .gitlab-ci
```

install requirements.txt
```bash
cd kubespray
pip install -r requirements.txt
```

after clone code
# edit the inventory file for our infrastructures
`kubespray/inventory/sample/inventory.ini`


## 2. Define what we neet to installed inside our clusters
`kubespray/inventory/sample/group_vars/k8s_cluster/addons.yml`
Change the addons for include these in the cluster setup
- dashboard_enabled: true
- helm_enabled: true
- metrics_server_enabled: true
- ingress_nginx_enabled: true
- cert_manager_enabled: true
- argocd_enabled: true


