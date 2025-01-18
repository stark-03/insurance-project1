node {
    def mavenHome
    def mavenCMD
    def dockerCMD
    def tagName

    stage('Prepare Environment') {
        echo 'Initializing all the variables...'
        mavenHome = tool name: 'maven', type: 'maven'
        mavenCMD = "${mavenHome}/bin/mvn"
        dockerCMD = "/usr/bin/docker" // Assuming Docker is installed at /usr/bin/docker
        tagName = "3.0"
    }

    stage('Git Code Checkout') {
        try {
            echo 'Checking out code from Git repository...'
            git 'https://github.com/Undertaker69cyprus/insurance-project.git'
        } catch (Exception e) {
            echo 'Exception occurred in Git Code Checkout Stage'
            currentBuild.result = "FAILURE"
            emailext body: """
            Dear All,

            The Jenkins job ${JOB_NAME} has failed. Please investigate the issue at the following link:
            ${BUILD_URL}
            """,
            subject: "Job ${JOB_NAME} ${BUILD_NUMBER} has failed",
            to: 'adithyamr20@gmail.com'
            throw e
        }
    }

    stage('Build the Application') {
        echo "Cleaning, Compiling, Testing, Packaging..."
        sh "${mavenCMD} clean package"
    }

    stage('Publish Test Reports') {
        publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'target/surefire-reports', // Relative path
            reportFiles: 'index.html',
            reportName: 'HTML Report',
            reportTitles: ''
        ])
    }

    stage('Containerize the Application') {
        echo 'Creating Docker image...'
        sh "${dockerCMD} build -t addi2/insure-me:${tagName} ."
    }

    stage('Push to DockerHub') {
        echo 'Pushing the Docker image to DockerHub...'
        withCredentials([string(credentialsId: 'dock-password', variable: 'dockerHubPassword')]) {
            sh "${dockerCMD} login -u addi2 -p ${dockerHubPassword}"
            sh "${dockerCMD} push addi2/insure-me:${tagName}"
        }
    }

    stage('Configure and Deploy to Test Server') {
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
