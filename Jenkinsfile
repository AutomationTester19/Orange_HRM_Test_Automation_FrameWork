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
           properties([parameters([choice(choices: ['main', 'temp'], description: 'Select desired branch to build', name: 'branches')])])

node{
stage ('Git Checkout') {
       steps {
       echo "checking out from branch ${params.branches}"
         git  url: 'https://github.com/AutomationTester19/Orange_HRM_Test_Automation_FrameWork',branch:"${params.branches}"
      }
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