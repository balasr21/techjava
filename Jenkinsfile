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
   
   withCredentials([string(credentialsId: 'dpassword', variable: 'dpwd')]) {
     print "${dpwd}"
     sh "docker login -u balasr3 -p ${dpwd} "
	 sh 'docker push balasr3/marvelinfo:1.0.0'
   }	
   
  }

}
