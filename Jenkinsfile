pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build And Run') {
        steps{
                 catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                             git branch:'main', url:'https://github.com/AutomationTester19/Orange_HRM_Test_Automation_FrameWork.git'
                             sh "mvn clean test -Dsuite=src/main/resources/testng.xml"
                 }
                }
            }
                stage('Extent Report'){
                    
                    steps {
                        
                       publishHTML([ 
                                  allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'Build', 
                                  reportFiles: 'OrangeHRMExtentReport.html', 
                                  reportName: 'Orange HRM Test Automation Report', 
                                  reportTitles: ''])
                    }

                }

        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
    }
}
    