## Note : 
- Data Persistent ការ backup data
- Storage, configuration

Deployment with kubernetes 
- Host path : volume that use to store data in one of node 
- emptyDir
* `hostPath` 
* `configMap` ប្រើដើម្បីរក្សារទុកនៅ configuration, env, shell script, config file 
* `secret` រក្សារទុកនៅ senstive data, pull image secret ... etc

```bash
ansible all -i inventory/ratanacluster/hosts.yaml -b -m package -a "name=nfs-common state=present"
```


if not work
```bash
tochratana@node2 ~> sudo nano /etc/exports
tochratana@node2 ~> sudo exportfs -rav

exporting *:/srv/nfs_shared/spring-demo-pv
exporting *:/srv/nfs_shared/spring-images-backup
```