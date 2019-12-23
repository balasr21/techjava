node{
  stage('Preparation'){
    deleteDir()
    checkout scm
  }
  stage('Build'){
    sh 'mvn package'
  }
  stage('PackageAndPush'){
    
   withCredentials([string(credentialsId: 'docker-pwd', variable: 'docker-pwd')]) {
     sh "docker login -u balasr3 -p  ${docker-pwd} "
     sh 'docker build -t balasr21/marvelinfo:1.0.0  .'
     sh 'docker push balasr21/marvelinfo:1.0.0'
   } 
  }

}
