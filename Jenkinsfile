node{
  stage('Preparation'){
    deleteDir()
    checkout scm
  }
  stage('Build'){
    sh 'mvn package'
  }
  stage('PackageAndPush'){
  
   sh 'docker build -t balasr3/marvelinfo:1.0.0  .' 
    
   withCredentials([string(credentialsId: 'docker-pwd', variable: 'docker-pwd')]) {
     sh "docker login -u balasr3 -p  ${docker-pwd} "
   } 
    
    sh "docker push balasr3/marvelinfo:1.0.0"
  }

}
