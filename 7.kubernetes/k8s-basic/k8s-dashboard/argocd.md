```bash

# for username 
admin
# generate password for login in Argocd : 
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 --decode

```


username : admin
passwrd  : ascD123!$