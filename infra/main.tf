terraform {
  required_version = ">= 1.9.0"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
  backend "local" {}
}

provider "aws" {
  region = "us-east-1"
}

resource "aws_ecr_repository" "ratemyschools_backend" {
  name                 = "ratemyschools-backend"
  force_delete         = true
  image_tag_mutability = "MUTABLE"
}

resource "aws_iam_role" "codebuild_role" {
  name = "ratemyschools-codebuild-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "codebuild.amazonaws.com"
        }
      }
    ]
  })
}

resource "aws_iam_role_policy" "codebuild_policy" {
  name = "ratemyschools-codebuild-policy"
  role = aws_iam_role.codebuild_role.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect   = "Allow"
        Action   = "ecr:GetAuthorizationToken"
        Resource = "*"
      },
      {
        Effect = "Allow"
        Action = [
          "ecr:BatchCheckLayerAvailability",
          "ecr:GetDownloadUrlForLayer",
          "ecr:BatchGetImage",
          "ecr:PutImage",
          "ecr:InitiateLayerUpload",
          "ecr:UploadLayerPart",
          "ecr:CompleteLayerUpload"
        ]
        Resource = aws_ecr_repository.ratemyschools_backend.arn
      },
      {
        Effect = "Allow"
        Action = [
          "logs:CreateLogGroup",
          "logs:CreateLogStream",
          "logs:PutLogEvents"
        ]
        Resource = "*"
      }
    ]
  })
}

resource "aws_codebuild_project" "ratemyschools_backend_build" {
  name         = "ratemyschools-backend-build"
  service_role = aws_iam_role.codebuild_role.arn

  artifacts {
    type = "NO_ARTIFACTS"
  }

  environment {
    compute_type    = "BUILD_GENERAL1_SMALL"
    image           = "aws/codebuild/standard:7.0"
    type            = "LINUX_CONTAINER"
    privileged_mode = true
  }

  source {
    type            = "GITHUB"
    location        = "https://github.com/Christopher4113/ratemyschools2.git"
    buildspec       = "buildspec.yml"
    git_clone_depth = 1

    git_submodules_config {
      fetch_submodules = false
    }
  }

  source_version = "master"
}

resource "aws_iam_role" "apprunner_ecr_access_role" {
  name = "ratemyschools-apprunner-ecr-access-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "build.apprunner.amazonaws.com"
        }
      }
    ]
  })
}

resource "aws_iam_role_policy_attachment" "apprunner_ecr_policy" {
  role       = aws_iam_role.apprunner_ecr_access_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSAppRunnerServicePolicyForECRAccess"
}

resource "aws_iam_role" "apprunner_instance_role" {
  name = "ratemyschools-apprunner-instance-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "tasks.apprunner.amazonaws.com"
        }
      }
    ]
  })
}

resource "aws_iam_role_policy" "apprunner_instance_policy" {
  name = "ratemyschools-apprunner-instance-policy"
  role = aws_iam_role.apprunner_instance_role.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Action = [
          "logs:CreateLogGroup",
          "logs:CreateLogStream",
          "logs:PutLogEvents"
        ]
        Resource = "*"
      },
      {
        Effect = "Allow"
        Action = [
          "secretsmanager:GetSecretValue"
        ]
        Resource = "arn:aws:secretsmanager:us-east-1:530743905127:secret:ratemyschools/*"
      }
    ]
  })
}

resource "aws_secretsmanager_secret" "spring_datasource_url" {
  name                    = "ratemyschools/spring-datasource-url"
  recovery_window_in_days = 7
}

resource "aws_secretsmanager_secret_version" "spring_datasource_url" {
  secret_id     = aws_secretsmanager_secret.spring_datasource_url.id
  secret_string = var.spring_datasource_url
}

resource "aws_secretsmanager_secret" "jwt_secret_key" {
  name                    = "ratemyschools/jwt-secret-key"
  recovery_window_in_days = 7
}

resource "aws_secretsmanager_secret_version" "jwt_secret_key" {
  secret_id     = aws_secretsmanager_secret.jwt_secret_key.id
  secret_string = var.jwt_secret_key
}

resource "aws_secretsmanager_secret" "app_password" {
  name                    = "ratemyschools/app-password"
  recovery_window_in_days = 7
}

resource "aws_secretsmanager_secret_version" "app_password" {
  secret_id     = aws_secretsmanager_secret.app_password.id
  secret_string = var.app_password
}

resource "aws_secretsmanager_secret" "groq_api_key" {
  name                    = "ratemyschools/groq-api-key"
  recovery_window_in_days = 7
}

resource "aws_secretsmanager_secret_version" "groq_api_key" {
  secret_id     = aws_secretsmanager_secret.groq_api_key.id
  secret_string = var.groq_api_key
}

output "ecr_repository_url" {
  value = aws_ecr_repository.ratemyschools_backend.repository_url
}

output "codebuild_project_name" {
  value = aws_codebuild_project.ratemyschools_backend_build.name
}

output "apprunner_ecr_access_role_arn" {
  value = aws_iam_role.apprunner_ecr_access_role.arn
}

output "apprunner_instance_role_arn" {
  value = aws_iam_role.apprunner_instance_role.arn
}

resource "aws_apprunner_service" "ratemyschools_backend" {
  service_name = "ratemyschools-backend"

  source_configuration {
    authentication_configuration {
      access_role_arn = aws_iam_role.apprunner_ecr_access_role.arn
    }

    image_repository {
      image_identifier      = "${aws_ecr_repository.ratemyschools_backend.repository_url}:latest"
      image_repository_type = "ECR"

      image_configuration {
        port = "8080"

        runtime_environment_variables = {
          SUPPORT_EMAIL = "ratemyschools@gmail.com"
          FRONTEND_URL  = "https://rate-my-schools.vercel.app"
        }

        runtime_environment_secrets = {
          SPRING_DATASOURCE_URL = aws_secretsmanager_secret.spring_datasource_url.arn
          JWT_SECRET_KEY        = aws_secretsmanager_secret.jwt_secret_key.arn
          APP_PASSWORD          = aws_secretsmanager_secret.app_password.arn
          GROQ_API_KEY          = aws_secretsmanager_secret.groq_api_key.arn
        }
      }
    }

    auto_deployments_enabled = false
  }

  instance_configuration {
    instance_role_arn = aws_iam_role.apprunner_instance_role.arn
    cpu               = "1024"
    memory            = "2048"
  }

  health_check_configuration {
    protocol            = "TCP"
    path                = "/"
    interval            = 10
    timeout             = 5
    healthy_threshold   = 1
    unhealthy_threshold = 5
  }

  tags = {
    Name = "ratemyschools-backend"
  }
}

output "apprunner_service_url" {
  value = aws_apprunner_service.ratemyschools_backend.service_url
}
