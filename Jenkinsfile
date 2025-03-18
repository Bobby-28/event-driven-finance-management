pipeline {
    agent any

    tools {
        maven 'Maven-3.8.6'  // Ensure Maven is installed in Jenkins
        jdk 'JDK-17'         // Ensure Java is installed in Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Bobby-28/event-driven-finance-management.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target\\*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo "Build & Test Successful 🎉"
        }
        failure {
            echo "Build or Test Failed ❌"
        }
        always {
            cleanWs() // Cleans up the workspace after the build
        }
    }
}
