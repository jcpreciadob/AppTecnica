import jenkins.model.*

def call(){
  Jenkins.instance.getAllItems(AbstractItem.class).each {
  	println(it.fullName)
    if(it.fullName == 'JOBSEED' || it.fullName == 'JOBDELETE'){
        echo "No se borra " + it.fullName
    }else{
        it-delete()
    }
  }
}