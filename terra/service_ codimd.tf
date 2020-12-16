module "serviciocodimd" {
  source        = "./modules/service"
  alb_arn       = module.vpc.alb_arn
  cluster_id    = module.vpc.cluster_services_id
  desired_count = 1

  project_name          = "pTecnica"
  component_type        = "serviceCodimd"
  component_name        = "codimd"
  task_region           = "us-east-2"
  task_cpu              = 70
  task_memoryRes        = 128
  iam_role_ecs_task_arn = module.vpc.iam_role_ecs_task_arn
  containerPath         = "/opt/shareclasse"
  account               = var.aws_account
  host_port             = 3000
  cont_port             = 3000
  protocol              = "tcp"
  sourceVolume          = "sharedclasses"
  imagenD = "nabo.codimd.dev/hackmdio/hackmd:2.2.0"
  containerNames = [
    "CMD_DB_URL",
    "CMD_USECDN"
  ]
  containerValues = [
    "postgres://codimd:change_password@database/codimd",
    "false"
  ]
}
