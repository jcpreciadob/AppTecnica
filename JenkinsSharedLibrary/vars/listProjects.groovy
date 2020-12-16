#!/usr/bin/env groovy

def call() {
    projects = [
        [
            proyecto: 'PAGD',
            name: 'DDTGA-PAGD-SERVICE-FTPS3',
            description: 'Service FTPS3',
            displayName: 'DDTGA-PAGD-SERVICE-FTPS3',
            includeBranches: 'QA ST master',
            excludeBranches: 'devops',
            gitRepository: 'https://github.com/bdb-dns/DDTGA-PAGD-SERVICE-FTPS3.git',
            idUnico: 'f01d4e4f-6ff5-40d9-b244-7228c82f5aec',
            credenciales: 'git-jpreci1',
            tipo: 'service',
            quickName: 'pagd-service-ftps3',
            standardName: 'service-ftps3',
            clusterQA: 'qa-cluster-ext-services-PAGD',
            cuentaQA: '176787109913',
            clusterST: 'st-cluster-ext-services-PAGD',
            cuentaST: '754917589356',
            clusterPR: 'pr-cluster-ext-services-PAGD',
            cuentaPR: '365711244018',
            bucket: '',
            region: 'us-east-1',
            profileqa: 'AWS_ECR_QA',
            profilest: 'AWS_ECR_ST',
            profilepr: 'AWS_ECR_PR',
            cloudfrontqa: '',
            cloudfrontst: '',
            cloudfrontpr: ''
        ]
    ]
    return projects
}
