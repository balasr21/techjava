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
    
  }

}
