resource "aws_cloudwatch_log_group" "cloudwatch_log_group" {
  name              = "${var.project_name}_${var.component_type}_${var.component_name}"
  retention_in_days = 1

}