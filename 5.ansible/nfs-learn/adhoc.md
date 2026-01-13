```bash
ansible all -i inventory.ini -m ping # run all machine
ansible web -i inventory.ini -m shell -a "uptime" # web → host group, shell → run shell commands, uptime → show system running time
ansible all -i inventory.ini -m command -a "df -h"
ansible all -m command -a "df -h"
ansible all -i inventory.ini -b -m apt -a "name=nginx state=present"
ansible all -b -m yum -a "name=httpd state=present"
ansible all -b -m service -a "name=nginx state=started" # ansible all -b -m service -a "name=nginx state=started"
ansible all -i inventory.ini -b -m copy -a "src=test.txt dest=/tmp/test.txt" # Copy file to remote hosts, src → local file, dest → remote path
ansible all -b -m file -a "path=/opt/app state=directory mode=0755" # Create a file / directory, States: directory, touch, absent

ansible all -m command -a "free -h" # Check memory & CPU
ansible all -m command -a "top -bn1 | head" # Check memory & CPU
ansible all -m setup | grep ansible_distribution # Check OS information
ansible all -b -m reboot # Reboot servers
ansible all -b --become-user=www-data -m shell -a "whoami" # Run command as specific user
ansible all -i inventory.ini --limit web1 -m ping # Limit hosts


```