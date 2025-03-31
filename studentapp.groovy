pipeline {
    agent any

    stages {
        stage('Git clone') {
            steps {
                  git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'
                sh 'echo "Git repo cloned successfully"'
            }
        }

        stage('Build') {
            steps {
                sh '/opt/maven/bin/mvn clean package'
                sh 'echo "Build successful"'
            }
        }

        stage('Test') {
            steps {
                withSonarQubeEnv(installationName:'sonar', credentialsId: 'sonar-cred') {
                    sh '/opt/maven/bin/mvn sonar:sonar'
                
                sh 'echo "Test successful"'
            }
        }
        }

        stage('Quality Gates') {
            steps {
              timeout(1)  {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
               deploy adapters: [tomcat9(credentialsId: 'tomcat-user', path: '', url: 'http://172.31.18.124:8080')], contextPath: '/', war: '**/*.war'
                sh 'echo "Deployment successful"'
            }
        }
    }
}
