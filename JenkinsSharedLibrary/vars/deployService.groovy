#!/usr/bin/env groovy

def call(project) {
        def mavenHome = tool name: "maven_3.6.1"
        env.PATH = "${mavenHome}/bin:${env.PATH}" 
        def folderNameSH = "inventario"
        def ambiente
        def cluster
        def cuenta
        def perfil
        def region
        def perfilAWS
        def nemonico = 'adpe'
        def channelSlack = 'jenkins_adpe'
        def encryptedFilesList = []
        def fileDecryptedList = []

        ambiente = 'qa' 
        cluster = project.clusterQA 
        cuenta = project.cuentaQA
        perfil = 'adpe-qa'
        region = 'us-east-1'
        perfilAWS = 'AWS_ECR_QA'

        stage("Init Pipeline") {
            checkout scm
        }

        stage("Docker build"){
            //sh "docker build -t st-pagd-service-ftps3 ."
            slackSend color: 'good', channel: channelSlack, message: "${env.JOB_NAME} # ${env.BUILD_NUMBER} - Docker Build Started."
            docker.build("${ambiente}-${nemonico}-${project.standardName}")
        }
        stage ("Docker push"){
            slackSend color: 'good', channel: channelSlack, message: "${env.JOB_NAME} # ${env.BUILD_NUMBER} - Docker Push Started."
            docker.withRegistry("https://${cuenta}.dkr.ecr.us-east-1.amazonaws.com", "ecr:${region}:${perfilAWS}") {
                docker.image("${ambiente}-${nemonico}-${project.standardName}").push('latest')
            }
        }
        }
        stage("Actualizar TASK"){
            slackSend color: 'good', channel: channelSlack, message: "${env.JOB_NAME} # ${env.BUILD_NUMBER} - Actualizar Task Started"
            def taskVARS = "${ambiente} ${project.standardName} ${cluster} ${perfil} ${nemonico}"
            sh "sh ${folderNameSH}/resources/commands/updateTask.sh ${taskVARS}"
        }
        stage ("Actualizar SERVICE"){
            slackSend color: 'good', channel: channelSlack, message: "${env.JOB_NAME} # ${env.BUILD_NUMBER} - Actualizar Service Started"
            def serviceVARS = "${ambiente} ${project.standardName} ${cluster} ${perfil} ${nemonico}"
            sh "sh ${folderNameSH}/resources/commands/updateService.sh ${serviceVARS}"
        }
    return this
}