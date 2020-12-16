output "alb_arn" {
  value       = aws_alb.balanceador.arn
  description = "alb Arn"
}

output "cluster_services_id" {
  value       = aws_ecs_cluster.cluster_services.id
  description = "Cluster for Internal Services ID"
}

output "iam_profile_ecs_instance_arn" {
  value = aws_iam_instance_profile.iam_profile_ecs_instance.arn
  description = "Role for instances for PAGD Ext services"
}

output "iam_role_ecs_task_arn" {
  value = aws_iam_role.iam_role_ecs_task.arn
  description = "ECS Task Execution Role"
}