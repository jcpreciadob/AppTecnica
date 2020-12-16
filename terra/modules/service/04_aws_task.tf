resource "aws_ecs_task_definition" "ecs_task_family" {
  family                   = "QA-${var.project_name}-${var.component_type}-${var.component_name}-task-family"
  container_definitions    = data.template_file.containerTaskJson.rendered
  network_mode             = "host"
  execution_role_arn       = var.iam_role_ecs_task_arn
  task_role_arn            = var.iam_role_ecs_task_arn
  requires_compatibilities = ["EC2"]
  volume {
    name      = "sharedclasses"
    host_path = "/opt/shareclasses"
  }
}