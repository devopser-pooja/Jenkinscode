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
                sh 'mvn clean package'
                sh 'echo "Build successfully done"'
            }
        }
        stage('test') {
            steps {
                sh 'echo "Test successfully done"'
            }
        }
        stage('deploy') {
            steps {
                sh 'echo "Deploy step placeholder"'
                
            }
        }
    }
}
