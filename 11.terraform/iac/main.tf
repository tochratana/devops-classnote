resource "google_compute_instance" "vm" {

  count = 3

  name         = "terraform-vm-${count.index + 1}"
  machine_type = "e2-medium"

  tags = ["terraform"]

  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2404-lts-amd64"
      size  = 20
    }
  }

  network_interface {
    network = "default"

    access_config {
    }
  }
}