provider "aws" {
    access_key = "AKIATSFVULOPNSVLKVTJ"
    secret_key = "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"
    region = "us-east-2"
}

terraform {
    required_providers {
      aws = {
          source="hashicorp/aws"
      }
    }
    required_version=">= 0.13.5"
}