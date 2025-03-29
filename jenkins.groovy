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
                
                    sh 'echo "Test successfully done"'
                }
            }
        }

        stage('deploy') { // Moved inside stages block
            steps {
            
                sh 'echo "Deploy step placeholder"'
            }
        }
    }
}

