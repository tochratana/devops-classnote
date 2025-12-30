## Create User
```bash
sudo useradd testuser #no home dir, bash shell
sudo useradd -m -s ./bin/bash -c "Test user" testuser # -m init home dir, -s add bash shell, -c add description
sudo passwd testuser # add password to testuser nn123456
# Change home directory
sudo usermod -d /home/newhome username
# Change shell 
sudo usermod -s /bin/zsh
```
* -m	Create home directory
* -d /path	Set custom home directory
* -s /bin/zsh	Set default shell
* -u 1500	Set custom user ID
* -g group	Set primary group
* -G group1,group2	Add user to secondary groups
* -c "Full Name"	Add comment (user description)