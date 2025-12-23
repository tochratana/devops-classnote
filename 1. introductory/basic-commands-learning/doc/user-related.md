## Note
commands relate to working with user in linux machines
```bash
id # show id, name of group user
groups # show group name

echo $SHELL # show current shell that we use 
cat /etc/shells

# /usr/bin/zsh
# to change shell of user 
sudo chsh -s /bin/bash bona 
sudo chsh -s /bin/bas

# 1. Create User 
useradd 
adduser 

sudo useradd therock # useful for script writing 
sudo adduser thor  # high-level  interactive 
sudo useradd \
    --shell /usr/bin/bash \
    --create-home loki

sudo passwd loki
exit 
su loki  # switch to user , but doesn't change the dir
su - loki # switch user to its home directory 

sudo passwd # your user 
sudo passwd username # sudo passwd root 

# change the username 
sudo usermod -l new-name old-name
sudo usermod -l superman thor

man userdel # man command 
# finger userdel 

sudo userdel superman 
sudo userdel superman -r # --remove 

# add user to specific group 
sudo usermod -aG groupname username 
sudo usermod -aG sudo james 
sudo usermod -aG docker username 
```
## Group Reloated commands 
```bash
groups username # show the group name that use in

# Remove user from secific group
sudo deluser username group
```