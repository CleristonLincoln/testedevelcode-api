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
                    dockekerapp = docker.build('api-jenkins', '-f .Dockerfile .')
                 }
             }
         }
    }
}
