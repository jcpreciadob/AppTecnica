variable "alb_arn" {
  description = "alb Arn"
  type        = string
}

#VPC Vars


#ECS Vars
variable "cluster_id" {
  description = "Cluster for Internal Services ID"
  type        = string
}

variable "desired_count" {
  description = "Desired Count"
  type        = string
  default     = "2"
}

#Common Vars
variable "containerNames" {
  description = "Container Names"
  type        = list
  default     = []
}

variable "containerValues" {
  description = "Container Names"
  type        = list
  default     = []
}

#Common Vars
variable "tags" {
  description = "A map of tags to add to all resources"
  type        = map(string)
  default     = {}
}

#Roles
variable "iam_role_ecs_task_arn" {
  description = "ECS Task Execution Role ARN"
  type        = string
}

variable "project_name" {
  description = "Project Name"
  type        = string
}

variable "component_type" {
  description = "Tipo de Componente (Adapter, Manager, etc)"
  type        = string
}

variable "component_name" {
  description = "Tipo de Componente (Adapter, Manager, etc)"
  type        = string
}

variable "task_region" {
  description = "Region"
  type        = string
}

variable "task_cpu" {
  description = "CPU"
  type        = number
}

variable "task_memoryRes" {
  description = "Memoria reservada para la tares"
  type        = number
}

variable "account" {
  description = "Cuenta"
  type        = string
}

variable "host_port" {
  description = "Host Port"
  type        = number
}

variable "cont_port" {
  description = "Container Port"
  type        = number
}

variable "protocol" {
  description = "Protocol"
  type        = string
}

variable "containerPath" {
  description = "Container Path"
  type        = string
}

variable "sourceVolume" {
  description = "Source Volume"
  type        = string
}

variable "imagenD" {
  description = "Imagen docker"
  type = string
}
