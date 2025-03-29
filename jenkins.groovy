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
              sh ''' /opt/maven/bin/mvn sonar:sonar \\
           -Dsonar.projectKey=student-app \\
          -Dsonar.host.url=http://51.21.135.147:9000 \\
          -Dsonar.login=7b31ba34f37188238f13967c11f2cdddcd005bd5'''
                sh 'echo "Test successfully done"'
            }
        }
        stage('deploy') {  // Corrected indentation and placement
            steps {
                sh 'echo "Deploy step placeholder"'
            }
        }
    }
}

