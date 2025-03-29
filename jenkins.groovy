pipeline {
    agent any
    stages {
        stage('git-clone') {
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
             withSonarQubeEnv(installationName:'sonar',credentialsId: 'sonar-cred') {
               sh '/opt/maven/bin/mvn sonar:sonar'
                sh 'echo "Test successfully done"'
              }
        }
    }
        stage('quality-gate') {
            steps {
               timeout(time: 5, unit: 'MINUTES'){
                waitForQualityGate abortPipeline: true
            }
        }
    }
        
        stage('deploy') {  // Corrected indentation and placement
            steps {
                sh 'echo "Deploy step placeholder"'
            }
        }
    }
}

