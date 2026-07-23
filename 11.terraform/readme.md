## Terraform

this is use for running infrastructure as code for setup 3vm in google cloud

1. install `gcloud`
```sh
sudo apt update
sudo apt install -y apt-transport-https ca-certificates gnupg curl

curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | \
gpg --dearmor | \
sudo tee /usr/share/keyrings/cloud.google.gpg >/dev/null


echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | \
sudo tee /etc/apt/sources.list.d/google-cloud-sdk.list

sudo apt update
sudo apt install -y google-cloud-cli

gcloud version
```