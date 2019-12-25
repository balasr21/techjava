node{
  stage('Preparation'){
    deleteDir()
    checkout scm
  }
  stage('Build'){
    sh 'mvn package'
  }

}
