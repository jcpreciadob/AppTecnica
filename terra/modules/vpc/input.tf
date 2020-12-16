variable "name" {
  description = "VPC's Name"
  type        = string
}

variable "vpcId" {
  description = "VPC's Id"
  type        = string
}

variable "account" {
  description = "AWS Account ID"
  type        = string
  default     = "245194054558"
}

variable "vpcName" {
  description = "VPC's Nombre"
  type        = string
}

variable "routeTableId" {
  description = "Route Table Id"
  type        = string
}

variable "cluster_name" {
  description = "cluster_name"
  type        = string
}

variable "nlb_name" {
  description = "Name for nlb"
  type        = string
}

variable "cidr" {
  description = "VPC CIDR"
  type        = string
  default     = "10.0.0.0/16"
}


variable "default_cidr" {
  description = "Default CIDR"
  type        = string
  default     = "0.0.0.0/0"
}

#Key Pair Vars
variable "key_pair_name" {
  description = "Key Pair name for EC2 instances"
  type        = string
  default     = "ConexionAWS"
}

variable "key_pair_public_key" {
  description = "Key Pair public content"
  type        = string
  default     = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCg+E+DTCFHtSLWxSlLpi3oypx2YHNL+Ngj8ppyNHk3Cu6MVC5sYTIyxTMYXBF4zpuaCNpMo9RMCMhpOReA5VzNX7sqpy8/u2QxWD1HpWc7pU2NwP7jQmgHJhULMKxv3GFZaqmisAbdHT3zWw4OY6pxjHvyqBeywxtetmiUybwCq6XOHzdAlaPbFdFHZoLPX5O+2RJeWNcLGGZ/fbVVJK+rYk/QDzHJgaye9E2TBPQ0nRTbnipX1VzKpf/oFL8xojF7NBR60YbvHObC7io2PaztgegqfeJpBVpPlmAAYbsRL/jyIFRUQ/BOjfs31qEEkrne5k/jMMKEgW7QtKCnAj09"
}



/* User Data Used */
variable "lt_ec2_internal_services_user_data" {
  description = "User Data for EC2 Internal Services Instances"
  type        = string
  default     = ""
}

variable "lt_ec2_external_services_user_data" {
  description = "User Data for EC2 External Services Instances"
  type        = string
  default     = ""
}
#Auto Scaling EC2 Microservice Vars

variable "ag_ec2_microservices_max" {
  description = "Auto Scaling max instances number"
  type        = number
  default     = 3
}

variable "ag_ec2_microservices_min" {
  description = "Auto Scaling min instances number"
  type        = number
  default     = 0
}

variable "ag_ec2_microservices_desired" {
  description = "Auto Scaling desired instances number"
  type        = number
  default     = 3
}

variable "ag_ec2_microservices_grace" {
  description = "Auto Scaling instances grace period"
  type        = number
  default     = 20
}

