## Noted For setup and Install zsh

### 1. Clone ZSH
```bash
sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

# If on Ubuntu can't use this command we can install zsh fist 
sudo apt install zsh
```

### 2. Install syntax highlighting and autosuggestions
```bash
# autosuggestions
git clone https://github.com/zsh-users/zsh-autosuggestions.git $ZSH_CUSTOM/plugins/zsh-autosuggestions

# highlighting
git clone https://github.com/zsh-users/zsh-syntax-highlighting.git $ZSH_CUSTOM/plugins/zsh-syntax-highlighting
```

### 3. Config `~/.zshrc`
```bash
nano ~/.zshrc
```
Find `plugins=(git)` and copy `zsh-autosuggestions zsh-syntax-highlighting` for it


### 4. After complete this, exit from nano and restart zsh
```bash
source ~/.zshrc
```