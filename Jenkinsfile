pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository

               echo("Build Deployed")

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
}
            stage('Orange HRM Regression Test Suite'){
            steps{
            git 'https://github.com/AutomationTester19/Orange_HRM_Test_Automation_FrameWork.git'
            sh "mvn -Dmaven.test.failure.ignore=true install"
            }
        }
        
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
        }
       }
    