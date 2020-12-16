#!/usr/bin/env groovy

def call() {
    cleanWs()
    ws("workspace/${env.JOB_NAME}/${env.BRANCH_NAME}") {
        try {
            stage('Checkout') {
                // Clean workspace and checkout shared library repository on the jenkins master
                cleanWs()
                checkout scm
            }
            stage('Job Seeding') {
                def workspace = pwd()
                def proyectos = listProjects()
                jobDsl(targets: 'resources/jobDSL/*.groovy', sandbox: false, additionalParameters: [projects: proyectos])
            }
        } catch (e) {
            throw e
        }
    }
    return this
}