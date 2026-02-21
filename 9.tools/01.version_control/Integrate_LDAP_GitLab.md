## Focus on LDAP with GitLab

- https://chatgpt.com/share/69992f4c-8af8-8010-bb8a-bfd126fc179c

What is LDAP ?

`LDAP(Lightweight Directory Access Protocol)` គឺជា protocol (វិធីសាស្រ្តទំនាក់ទំនង) មួយសម្រាប់ ចូលប្រើ និងគ្រប់គ្រង Directory Service តាម network

វាត្រូវបានប្រើសម្រាប់ រក្សាទុក និងស្វែងរកព័ត៌មានអំពី user, group, computer, organization ជាដើម។

### 1. Directory Service ជាអ្វី?

Directory Service គឺជា database ពិសេសមួយ ដែលរក្សាទុកព័ត៌មានជារៀបចំជាទម្រង់ tree structure (hierarchical structure)

ឧទាហរណ៍ព័ត៌មានដែលអាចរក្សាទុក៖
* Username
* Password (hashed)
* Email
* Phone number
* Department
* Group membership

---

### 2. LDAP មានតួនាទីអ្វី?

LDAP អនុញ្ញាតឱ្យ application ធ្វើការ៖

* Search user
* Authenticate user (Login)
* Add new user
* Update user info
* Delete user

---

### 3. ឧទាហរណ៍ System ដែលប្រើ LDAP

---


This integration works with most LDAP-compliant directory servers, including:

Microsoft Active Directory.
Apple Open Directory.
OpenLDAP.
389 Server.