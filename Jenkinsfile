pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build And Run') {

                // Get some code from a GitHub repository
                 steps
                 {
                             git 'https://github.com/AutomationTester19/Orange_HRM_Test_Automation_FrameWork.git'
                             sh "mvn -Dmaven.test.failure.ignore=true install"
                 }
                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"

}

        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
    }
}
    