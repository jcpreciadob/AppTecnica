
resource "aws_ecs_service" "ecs_service" {
  name                               = var.component_name
  cluster                            = "ServiciosPruebaTecnica"
  task_definition                    = aws_ecs_task_definition.ecs_task_family.arn
  desired_count                      = var.desired_count
  deployment_minimum_healthy_percent = 0
  deployment_maximum_percent         = 100
  ordered_placement_strategy {
    type  = "spread"
    field = "attribute:ecs.availability-zone"
  }
}