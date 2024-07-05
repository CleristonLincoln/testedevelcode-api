pipeline {
    agent any

    stages {
    
        stage ('inicial teste'){
            steps {
                echo 'teste'
            }
        }
    
        stage('create image dockerhub') {
            steps {
                script {
                   dockekerapp = docker.build('cleristonlincoln/teste-develcode', '-f .Dockerfile .')
                }
            }
        }

        stage('push docker image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        dockerImage.push('latest')
                    }
                }
            }
        }
    }
}
