## Noted : 
### 1. Run the playbook to create 4, 5 machines
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
##### edit the inventory file for our infrastructures
`kubespray/inventory/sample/inventory.ini`


### 2. Define what we neet to installed inside our clusters
`kubespray/inventory/sample/group_vars/k8s_cluster/addons.yml`
Change the addons for include these in the cluster setup
- dashboard_enabled: true
- helm_enabled: true
- metrics_server_enabled: true
- ingress_nginx_enabled: true
- cert_manager_enabled: true
- argocd_enabled: true
- change name of cluster `kubespray/inventory/sample/group_vars/k8s_cluster/k8s-cluster.yml` and then search `cluster_name`

> after change this we can run command to start cluster
```bash
cd kubespray
ansible-playbook -b -v -i inventory/sample/inventory.ini cluster.yml

# ping command for kubespray to know machines or not
ansible -i inventory/sample/inventory.ini all -m ping
```

> After successfull installation

this command if we use localhost machine for run, should be ssh to vm for run it
```bash
sudo kubectl get node
sudo kubectl get node -o wide
sudo kubectl get pod -A
```
> Run kubectl command without sudo

```bash
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```