resource "null_resource" "containerVars" {
  count = length(var.containerNames)

  triggers = {
    name  = element(var.containerNames, count.index)
    value = element(var.containerValues, count.index)
  }
}

data "template_file" "containerTaskJson" {
  template = file("./templates/templateTask.json")
  vars = {
    project       = var.project_name
    tipo          = var.component_type
    name          = var.component_name
    region        = var.task_region
    cpu           = var.task_cpu
    memoryRes     = var.task_memoryRes
    cuenta        = var.account
    host_port     = var.host_port
    cont_port     = var.cont_port
    protocol      = var.protocol
    containerVars = jsonencode(concat(null_resource.containerVars.*.triggers))
    containerPath = var.containerPath
    sourceVolume  = var.sourceVolume
    privileged    = true
    imagen        = var.imagenD
  }
}
