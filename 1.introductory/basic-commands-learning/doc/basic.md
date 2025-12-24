## Note
```bash
sudo apt update && apt upgrade -y
pwd # show current directory
ls # list files in current directory
ls -la # list all files in current directory

# generate ssh key
ssh-keygen

sudo apt install tldr -y
# show manual of command
man <command>
tldr <command>
# show directory tree
tree
# show file content
cat <file>
less <file>
# search file
find / -name <file> 2>/dev/null
# search string in file
grep -r <string> /path/to/search
# show process
top
htop

cd # go to home directory 
cd ~ # go to home directory ~ = /home/username 
cd /home/keo/Document
cd ~/Document
mkdir Document # create a directory 
touch textfile.txt 

echo "Hello world " > firstfile.txt 
# write content to files 
# nano vs vim , neovim ( lazyvim )

nano filename # -> Ctrl O ,Enter , Ctrl X
vim filename  # i for insert mode , esc for command mode , :wq! for save and quit

sudo apt install tree -y 
tree . 
tree directoryname

sudo apt install nginx -y 
curl localhost # show response page of nginx 
curl ifconfig.me # show the public ip address

more /etc/passwd 
cat /etc/passwd 
getent passwd

sudo apt install neofetch -y
neofetch # show the system information

# Show system information 
uname -a

# delete file or directory 
# sudo 
rm -rf filename # directory or file 
# rm -f , rmdir

# copy file or directory 
cp source destination
cp message.txt message-copy.txt
mv message-copy.txt destdirectory

cp *.txt destdirectory # copy all .txt files 
mv *.txt sourcedirectory # move all .txt files

# check disk on machine 
df -h 

getent group
getent services 

# sudo systemctl verb service 
sudo service nginx status 
sudo sytemctl status nginx 
sudo systemctl reload nginx 
sudo systemctl stop nginx 
sudo systemctl start nginx 
sudo systemctl disable nginx 

sudo systemctl daemon-reload 

sudo init 0 # shutdown
sudo reboot # reboot 
```