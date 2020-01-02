node{
  
  def tag=sh "git describe --always"
  print tag
  
  def appname='marvelinfo'

  stage('Preparation'){
    deleteDir()
    checkout scm
  }
  stage('Build'){
    sh 'mvn package'
  }
  stage('PackageAndPush'){
  
   sh 'docker build -t balasr3/marvelinfo:1.0.0  .' 
  
	try{
   withCredentials([string(credentialsId: 'dpassword', variable: 'dpwd')]) {
     sh "docker login -u balasr3 -p ${dpwd} "
	 sh "docker push balasr3/marvelinfo:{$tag}"
	 sh "docker push balasr3/marvelinfo:latest"
   }	
   }catch(e){
    println("Error while pushing image to Docker")
	throw e
   }
  }
  
  stage('Deploy'){
    sh "cd helm/{$appname}"
	
	try {
	  helm install marvelinfo
	}
	catch(e){
	}
	
  }

}
