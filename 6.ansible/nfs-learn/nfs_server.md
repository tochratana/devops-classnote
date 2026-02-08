### 1. What is NFS ?
> Network File System allow you to share files and folders between computers over a network.
- One machine acts as a server (shares file)
- Other machines act as clients (access files)
It feels like a local folder, but the data actually lives on another server.
---
### 2. Why do we use NFS ?
- Multiple servers need shared data
- Centralized storage is required
- Avoid copying files between machines
- Used in DevOps, CI/CD, Kubernetes, Cloud
Real examples: 
- Shared `/var/www` for mulfiple web servers
- Shared home directories for Linux users
- Shared storage for Jenkins builds
### 3. How NFS works ?
```bash
        +------------------+
        |   NFS Server     |
        |  /data/shared    |
        +--------+---------+
                 |
        ------------------------
        |           |          |
+-------+----+ +----+------+ +--+-------+
| Client 1   | | Client 2  | | Client 3 |
| /mnt/nfs   | | /mnt/nfs  | | /mnt/nfs |
+------------+ +------------+ +---------+

All clients see the same files
```
### 4. NFS Components
> `NFS Server`
- Exports (Shares) directories
- Config file:
- `etc/exports`
> `NFS Client`
- Mounts remote directory
- Accesses files like local files
### 5. NFS Versions (Important)
```bash
| Version | Notes                                    |
| ------- | ---------------------------------------- |
| NFSv3   | Fast, no encryption                      |
| NFSv4   | Secure, firewall-friendly, recommended   |
```
### 6. How to setup NFS
> On NFS Server
```bash
sudo apt install nfs-kernel-server
```

Create shared directory:
```bash
sudo mkdir -p /data/shared
sudo chmod 777 /data/shared
```

Edit exports:
```bash
sudo nano /etc/exports
```

Add:
```bash
/data/shared 192.168.1.0/24(rw,sync,no_subtree_check)
```

Apply config:
```bash
sudo exportfs -ra
sudo systemctl restart nfs-server
```

> On NFS Client
```bash
sudo apt install nfs-common
```
Mount:
```bash
sudo mount 192.168.1.10:/data/shared /mnt
```
### 7. Important Export Options (Must Know)
Option	Meaning
rw	Read & Write
ro	Read Only
sync	Safe write (recommended)
async	Faster but risky
no_root_squash	Root access (dangerous)
root_squash	Root mapped to nobody (default)
### 8. NFS vs SMB (Quick Compare)
Feature	NFS	SMB
OS	Linux/Unix	Windows
Performance	Faster	Slower
Use case	Servers, DevOps	File sharing
### 9. Pros & Cons
> Advantages
- Easy to setup
- Centralized storage
- Works like local filesystem
> Disadvantages
- Depends on network
- ecurity risk if misconfigured
- Not good for high-latency networks
### 10. NFS in Real DevOps / Cloud
- Jenkins shared workspace
- Kubernetes Persistent Volumes
- GCP / AWS NFS-like services (Filestore, EFS)
- Multi-server web apps