pipeline {
    agent any
    environment {
        mavenHome = tool name: 'maven', type: 'maven'
        mavenCMD = "${mavenHome}/bin/mvn"
        dockerCMD = "/usr/bin/docker" // Adjust this path dynamically if needed
        tagName = "3.0"
    }
    stages {
        stage('Prepare Environment') {
            steps {
                echo 'Environment initialized successfully.'
            }
        }

        stage('Git Code Checkout') {
            steps {
                script {
                    try {
                        echo 'Checking out code from Git repository...'
                        git url: 'https://github.com/Undertaker69cyprus/insurance-project.git'
                    } catch (Exception e) {
                        error "Git Checkout failed: ${e.getMessage()}"
                    }
                }
            }
        }

        stage('Build the Application') {
            steps {
                echo "Cleaning, Compiling, Testing, Packaging..."
                sh "${mavenCMD} clean package"
            }
        }

        stage('Publish Test Reports') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'HTML Report'
                ])
            }
        }

        stage('Containerize the Application') {
            steps {
                echo 'Creating Docker image...'
                sh "${dockerCMD} build -t addi2/insure-me:${tagName} ."
            }
        }

        stage('Push to DockerHub') {
            steps {
                echo 'Pushing the Docker image to DockerHub...'
                withCredentials([string(credentialsId: 'dock-password', variable: 'dockerHubPassword')]) {
                    sh """
                        ${dockerCMD} login -u addi2 -p ${dockerHubPassword}
                        ${dockerCMD} push addi2/insure-me:${tagName}
                    """
                }
            }
        }

        stage('Configure and Deploy to Test Server') {
            steps {
                echo 'Deploying the application using Ansible...'
                ansiblePlaybook(
                    become: true,
                    credentialsId: 'ansible-key',
                    disableHostKeyChecking: true,
                    installation: 'ansible',
                    inventory: '/etc/ansible/hosts',
                    playbook: 'ansible-playbook.yml'
                )
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            emailext body: """
            Dear All,

            The Jenkins job ${JOB_NAME} has failed. Please investigate the issue at the following link:
            ${BUILD_URL}
            """,
            subject: "Job ${JOB_NAME} ${BUILD_NUMBER} has failed",
            to: 'adithyamr20@gmail.com'
        }
    }
}
