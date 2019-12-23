node{
  stage('Preparation'){
    deleteDir()
    checkout scm
  }
  stage('Build'){
    sh 'mvn package'
  }
  stage('PackageAndPush'){
     
   withDockerRegistry(credentialsId: 'docker-creds') {
      sh 'docker build -t balasr21/marvelinfo:1.0.0  .'
   }
  }

}
