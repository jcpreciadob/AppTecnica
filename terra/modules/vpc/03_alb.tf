resource "aws_alb" "balanceador" {

  internal           = false
  ip_address_type    = "ipv4"
  load_balancer_type = "application"
  name               = "pTecnica-Balanceador"

  subnets = ["subnet-a33925d9","subnet-acd397e0"]

}
