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

        stage("Deploy to QA"){
            steps{
                echo("QA Deployed")
            }
        }
stage ('Git Checkout') {
       steps {
         git branch: 'main', url: 'https://github.com/AutomationTester19/Orange_HRM_Test_Automation_FrameWork'
      }
    }
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/AutomationTester19/Orange_HRM_Test_Automation_FrameWork.git'
                    sh "mvn clean test"

                }
            }
        }


        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
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

        stage("Deploy to Stage"){
            steps{
                echo("Stage Deployed")
            }
        }

        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/AutomationTester19/Orange_HRM_Test_Automation_FrameWork.git'
                    sh "mvn clean test"

                }
            }
        }



        stage('Publish  Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'reports',
                                  reportFiles: 'TestExecutionReport.html',
                                  reportName: 'HTML Sanity Extent Report',
                                  reportTitles: ''])
            }
        }


        stage("Deploy to PROD"){
            steps{
                echo("PROD Deployed")
            }
        }


    }
}