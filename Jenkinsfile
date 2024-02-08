pipeline
{
    agent any

    tools{
        maven 'maven'
        }

    stages
    {
        stage('Build')
        {
            steps
            {
                echo("Build Deployed")
            }
        }
              stage("Deploy to Stage"){
                    steps{
                        echo("Stage Deployed")
                    }
                }
stage ('Git Checkout') {
       steps {
         git branch: 'main', url: 'https://github.com/AutomationTester19/Orange_HRM_Test_Automation_FrameWork.git'
      }
    }
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/AutomationTester19/Orange_HRM_Test_Automation_FrameWork'
                    sh "mvn clean test"

                }
            }
        }

stage("Deploy to PROD"){
            steps{
                echo("PROD Deployed")
            }
        }

        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'Reports',
                                  reportFiles: 'OrangeHRMTestAutomation.html',
                                  reportName: 'HTML Regression Extent Report',
                                  reportTitles: ''])
            }
        }




    }
}