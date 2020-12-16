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
        if(project.proyecto == 'PAGD'){
            nemonico = 'pagd'
            channelSlack = 'jenkins_pagd'
        }
        if(project.proyecto == 'PEE'){
            nemonico = 'pee'
        }
        switch(env.BRANCH_NAME) {
            case 'QA':
                ambiente = 'qa' 
                cluster = project.clusterQA 
                cuenta = project.cuentaQA
                perfil = 'adpe-qa'
                region = 'us-east-1'
                perfilAWS = 'AWS_ECR_QA'
            break
            case 'ST':
                ambiente = 'st'
                cluster = project.clusterST 
                cuenta = project.cuentaST
                perfil = 'adpe-st'
                region = 'us-east-1'
                perfilAWS = 'AWS_ECR_ST'
            break
            case 'master':
                ambiente = 'pr'
                cluster = project.clusterPR 
                cuenta = project.cuentaPR
                perfil = 'adpe-pr'
                region = 'us-east-1'
                perfilAWS = 'AWS_ECR_PR'
            break
            default:
                ambiente = 'null'
                cluster = 'null'
                cuenta = 'null'
            break
        }
        stage("Init Pipeline") {
            checkout scm
        }
        stage("Build Project"){
            //sh "mvn dependency:tree"
            slackSend color: 'good', channel: channelSlack, message: "${env.JOB_NAME} # ${env.BUILD_NUMBER} - Build Project Started."
            sh "mvn clean package -DskipTests"
        }
        stage("Docker build"){
            //sh "docker build -t st-adpe-manager-notification ."
            slackSend color: 'good', channel: channelSlack, message: "${env.JOB_NAME} # ${env.BUILD_NUMBER} - Docker Build Started."
            if(project.proyecto == 'PAGD' || project.proyecto == 'ADPE') {
                fileDecryptedList = verifyEncryptedFiles()
                dockerFileCreateJ9(fileDecryptedList)
            }else
                if (project.proyecto == 'PEE'){
                fileDecryptedList = verifyEncryptedFiles()
                dockerFileCreateJ9pee(fileDecryptedList)                    
                }
            docker.build("${ambiente}-${nemonico}-${project.standardName}")
        }
        stage ("Docker push"){
            slackSend color: 'good', channel: channelSlack, message: "${env.JOB_NAME} # ${env.BUILD_NUMBER} - Docker Push Started."
            docker.withRegistry("https://${cuenta}.dkr.ecr.us-east-1.amazonaws.com", "ecr:${region}:${perfilAWS}") {
                docker.image("${ambiente}-${nemonico}-${project.standardName}").push('latest')
            }
        }
        stage("Importando Inventario"){
            dir(folderNameSH) {
                slackSend color: 'good', channel: channelSlack, message: "${env.JOB_NAME} # ${env.BUILD_NUMBER} - Importando Inventario Started."
            git branch: 'master',
                credentialsId: 'git-cforer3',
                url: 'https://github.com/bdb-dns/DDTGA-ADPE-JenkinsSharedLibrary.git'
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
/*         stage("SonarQube Scan"){
            slackSend color: 'good', channel: channelSlack, message: "${env.JOB_NAME} # ${env.BUILD_NUMBER} - SonarQube Scan Started."
            sh "mvn sonar:sonar -Dsonar.host.url=http://elb-st-ddtga-servers-761442156.us-east-1.elb.amazonaws.com:8082"
        } */
    return this
}