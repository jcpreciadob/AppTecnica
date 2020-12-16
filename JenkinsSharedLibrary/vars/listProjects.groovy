#!/usr/bin/env groovy

def call() {
    projects = [
        [
            proyecto: 'EJERCICIO',
            name: 'COMIDM',
            description: 'EJERCICIO',
            displayName: 'COMIDM',
            includeBranches: 'master',
            excludeBranches: 'devops',
            gitRepository: 'https://github.com/hackmdio/codimd.git',
            idUnico: 'f01d4e4f-6ff5-40d9-b244-7228c82f5aec',
            credenciales: 'jcpreciadob',
            tipo: 'service',
            quickName: 'comidmd',
            clusterQA: 'ServiciosPruebaTecnica',
            cuentaQA: '245194054558',
            bucket: '',
            region: 'us-east-1',
            profileqa: 'default',
        ]
    ]
    return projects
}
