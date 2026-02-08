# Note 
Goal : use ansible to create GCP Instance base on reqirement


# Ansible Variables Auto-Loading (group_vars & host_vars)

This document explains **how Ansible automatically loads variables**, focusing on
`group_vars`, `host_vars`, inventory groups, and playbooks.

---

## 1. Key Concept (Most Important)

> **Ansible loads variables based on INVENTORY, not playbook file names or folders**

- `group_vars` → loaded by **inventory group name**
- `host_vars` → loaded by **inventory host name**
- Playbook file name and folder structure **do not matter**

---

## 2. group_vars Auto-Loading Rules

### ✅ Folder name (MUST be exact)



❌ `group-var/`  
❌ `groupVars/`

### ✅ File names that are auto-loaded

| File name | Loaded for |
|---------|-----------|
| `all.yml` or `all.yaml` | All hosts |
| `<group>.yml` / `<group>.yaml` | Only that inventory group |

✔ Extensions allowed: `.yml`, `.yaml`  
❌ `.txt`, `.json`, `.md`

## 3. Example: Correct Structure
```bash
project/
├── inventory.ini
├── group_vars/
│ ├── all.yaml
│ ├── nfs_clients.yaml
│ └── nfs_server.yaml
├── playbooks/
│ └── nfs-client.yaml
├── tasks/
│ └── nfs-client-task.yaml
```

## 4. Inventory Controls Everything

### inventory.ini
```ini
[nfs_clients]
10.148.0.7
10.148.0.3

[nfs_server]
10.148.0.6
```



## Variable Loading Flow

inventory.ini
   ↓
inventory group name
   ↓
group_vars/<group>.yaml
   ↓
playbook (hosts:)
   ↓
tasks / include_tasks





