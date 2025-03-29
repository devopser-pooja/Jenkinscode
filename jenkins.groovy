pipeline {
    agent any
    stages {
        stage('git-clone') { // Fixed stage name
            steps {
                git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'
            }
        }
        stage('build') {
            steps {
                sh '/opt/maven/bin/mvn clean package'
                sh 'echo "Build successfully done"'
            }
        }
        stage('test') {
            steps {
                withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonar-cred') {
                    sh '/opt/maven/bin/mvn sonar:sonar'
                    sh 'echo "Test successfully done"'
                }
            }
        }
        stage('quality-gate') { // Fixed indentation & added missing braces
            steps {
                timeout(time: 5, unit: 'MINUTES') { // Added unit for clarity
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('deploy') { // Moved inside stages block
            steps {
               deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://172.31.34.191:8080')], contextPath: '/', war: '**/*.war'
                sh 'echo "Deploy step placeholder"'
            }
        }
    }
}

