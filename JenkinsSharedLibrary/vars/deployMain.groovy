#!/usr/bin/env groovy

def call() {
    try {
        if (env.BUILD_ID == '1'){
            echo "Build ejecutado por el Scan... Ignorando!!!"
            currentBuild.result = 'NOT_BUILT'
            error('Aborting build triggered by scan process')
        }
        //sh 'printenv'
        //echo "Hello ${verbosity}"
        projects = listProjects()
        def proyecto
        for (project in projects) {
            proyecto = project
            projectName = project.name + '/' + env.BRANCH_NAME
            if(projectName == env.JOB_NAME){
                switch(project.tipo) {
                    //Deploy to ECS
                    case 'service':
                        deployService(project)    
                        break
                    default:
                        script {
                            error "Tipo de proyecto invalido"
                        }
                        break
                }
                break
            }
        }
    }catch(e){
            throw e
    }   
    return this
}