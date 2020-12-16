module "servicioBD" {
  source        = "./modules/service"
  alb_arn       = module.vpc.alb_arn
  cluster_id    = module.vpc.cluster_services_id
  desired_count = 1

  project_name          = "pTecnica"
  component_type        = "serviceDB"
  component_name        = "PTdb"
  task_region           = "us-east-2"
  task_cpu              = 70
  task_memoryRes        = 128
  iam_role_ecs_task_arn = module.vpc.iam_role_ecs_task_arn
  containerPath         = "/opt/shareclasse"
  account               = var.aws_account
  host_port             = 5432
  cont_port             = 5432
  protocol              = "tcp"
  sourceVolume          = "sharedclasses"
  imagenD = "postgres:11.6-alpine"
  containerNames = [
    "POSTGRES_USER",
    "POSTGRES_PASSWORD",
    "POSTGRES_DB"
  ]
  containerValues = [
    "codimd",
    "123456h*",
    "codimd"
  ]
}
