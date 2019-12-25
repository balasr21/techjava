node{
  stage('Preparation'){
    deleteDir()
    checkout scm
  }
  stage('Build'){
    sh 'mvn package'
  }
  stage('PackageAndPush'){
  
   sh 'docker build -t balasr21/marvelinfo:1.0.0  .' 
   
   withCredentials([string(credentialsId: 'docker-pwdcredentials', variable: 'docker-password')]) {
     sh "docker login -u balasr3 -p  ${docker-password} "
	 sh 'docker push balasr21/marvelinfo:1.0.0'
   }	
   
  }

}
