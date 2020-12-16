
resource "aws_iam_role" "iam_role_ecs_instance" {
  name = "iam_role_ecs_instance"

  assume_role_policy = <<EOF
{
  "Version": "2008-10-17",
  "Statement": [
    {
      "Sid": "",
      "Effect": "Allow",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF

}

resource "aws_iam_instance_profile" "iam_profile_ecs_instance" {
  name = "iam_profile_ecs_instance"
  role = aws_iam_role.iam_role_ecs_instance.name
}