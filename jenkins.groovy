pipeline {
    agent any
    stages {
        stage('git-clonke') {
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
               // sh '''/opt/maven/bin/mvn sonar:sonar \\
           //  -Dsonar.projectKey=student-app \\
             // -Dsonar.host.url=http://16.170.173.14:9000 \\
             //-Dsonar.login=e95a7bea19e9c7a64ab2793d78ef117fb58bd47b'''
              withSonarQubeEnv(installationName: 'sonar',credentialsId: 'sonar-cred') {
                  sh '/opt/maven/bin/mvn sonar:sonar'
                sh 'echo "Test successfully done"'
            }
        }
        }
          stage('quality- gate') {
            steps {
                timeout(5) {
                waitForQualityGate abortPipeline: true
                
            }
            }
        stage('deploy') {
            steps {
                sh 'echo "Deploy step placeholder"'
                
            }
        }
    }
}
