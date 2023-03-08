pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo ('Hello jenkins')
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}