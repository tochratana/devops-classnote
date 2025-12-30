## Change Owner
```bash
chown [OPTION] NEW_OWNER[:NEW_GROUP] FILE(s)
# NEW_OWNER: The new owner (user) of the file or directory.
# NEW_GROUP: The new group for the file or directory (optional).
# FILE(s): The file or directory whose ownership you want to change

# Change owner of the file
sudo chown alex report.txt

# Change both the owner and the group
sudo chown john:developers project_folder

# Change only the group (without changing the owner)
sudo chown :staff data.txt

# Change owner and set group to the owner's primary group:
sudo chown bob: file.txt
```