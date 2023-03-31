def COMMIT_MSGS = ''
def LIST = ''
pipeline {
    agent any
    environment {
        BITBUCKET_CREDS             = ''
        GIT_BRANCH                  = ''
        GIT_REPO_URL                = ''
        WORKSPACE                   = pwd()
    }
     parameters {
        text(name:'ENVIRONMENT',defaultValue:'QA')
        text(name:'WEBBROWSER', defaultValue:'grid-chrome')
    }
    options {
        buildDiscarder(logRotator(numToKeepStr:'10', artifactNumToKeepStr:'10'))
        skipDefaultCheckout()
        disableConcurrentBuilds()
    }
    stages {
        stage('CleanWorkspace') {
            steps {
                cleanWs()
                dir("${env.WORKSPACE}@tmp") {
                    deleteDir()
                }
                dir("${env.WORKSPACE}@script") {
                    deleteDir()
                }
                dir("${env.WORKSPACE}@script@tmp") {
                    deleteDir()
                }
                step([$class:'WsCleanup'])
            }
        }
        stage('Checkout') {
            steps {
                slackSend (color: '#FFFF00', message: "STARTED: Job: '${env.JOB_NAME} [${env.BUILD_NUMBER}]', (${env.BUILD_URL})")
                git branch:"${GIT_BRANCH}", credentialsId:"${env.BITBUCKET_CREDS}", url:"${env.GIT_REPO_URL}"
            }
        }
        stage('Sonarqube') {
            environment {
                scannerHome = tool name: 'SonarQubeScanner' , type: 'hudson.plugins.sonar.SonarRunnerInstallation'
            }
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.branch.name=$GIT_BRANCH"
                }
            }
        } 
        /*stage('Quality gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
                    waitForQualityGate abortPipeline:false
                }
            }
        }*/
        stage("Polaris Analysis"){
            steps {
                slackSend (color: '#FFFF00', message: "Polaris Analysis STARTED")
                script {
                    def polarisStatus = polaris arguments: 'analyze -w --incremental $CHANGE_SET_FILE_PATH', createChangeSetFile: [excluding: '', including: '*.java,*.php,*.js', returnSkipCode: true], polarisCli: 'Polaris CLI', returnStatus: true
                    if (polarisStatus == -1) {
                    print 'Incremental analysis was skipped because no change set file could be created. Falling back on full analysis'
                    polarisStatus = polaris arguments: 'analyze -w', polarisCli: 'Polaris CLI', returnStatus: true
                    }

                    if (polarisStatus == 0) {
                        slackSend (color: '#00FF00', message: "Polaris Analysis done successfully")
                        print 'Success: Polaris static analysis succeeded, perform issue check'
                        polarisIssueCheck()
                    } else {
                        slackSend (color: '#FF0000', message: "Polaris Analysis failed")
                        print 'Failure: Both incremental analysis and full analysis failed'
                    }
                }
            }
        }
        stage('Polaris blackduck') {
            steps {
                script {
                    synopsys_detect detectProperties: ''' 
                    --blackduck.trust.cert=true --detect.project.version.phase=RELEASED --detect.project.version.distribution=EXTERNAL --detect.policy.check.fail.on.severities=CRITICAL --detect.timeout=300 --detect.accuracy.required=NONE --detect.wait.for.results=false
                    ''', downloadStrategyOverride: [$class: 'ScriptOrJarDownloadStrategy']
                }
            }
        } 
        stage("Maven Build") {
            steps {
                script {
                    sh '''
					pwd
                    export envi=${ENVIRONMENT}
                    export browser=${WEBBROWSER}
                    mvn clean install 
                    '''
                }
            }
        }
        stage("Get Change logs") {
			steps {
				script {
					//script to get GIT_COMMIT Change logs
					LIST = getChangeString(COMMIT_MSGS)
					LIST.toString()
				}
			}
        }
    }
	post {  
        always {
			script {
				sh '''
				kill $(ps -ef | grep "smoketests_tunnel" | awk '{print $2}')
				'''
                if ("${currentBuild.currentResult}" == 'SUCCESS') {
					slackSend (color: '#00FF00', message: "*${currentBuild.currentResult}:* Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' \n ChangeLogs: ${LIST} \n More info at: (${env.BUILD_URL})")
				} else {
					slackSend (color: '#FF0000', message: "*${currentBuild.currentResult}:* Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' \n ChangeLogs: ${LIST} \n More info at: (${env.BUILD_URL})")
				}
			}
        }
    } 
}
@NonCPS
def getChangeString(COMMIT_MSGS) {
 	MAX_MSG_LEN = 100
 	def changeString = ""
 	echo "Gathering SCM changes"
 	def changeLogSets = currentBuild.changeSets
 	for (int i = 0; i < changeLogSets.size(); i++) {
 		def entries = changeLogSets[i].items
 		for (int j = 0; j < entries.length; j++) {
 			def entry = entries[j]
			//echo "Commit by ${entry.author} at ${new Date(entry.timestamp)}: ${entry.msg}; \n"
			COMMIT_MSGS += "Commit by ${entry.author} at ${new Date(entry.timestamp)}: ${entry.msg}; \n"
			//echo "COMMIT_MSGS: ${COMMIT_MSGS}"
		}
 	}
 	if (!changeString) {
 		changeString = " - No new changes"
 	}
 	//return [changeString,COMMIT_MSGS];
	return [COMMIT_MSGS];
}