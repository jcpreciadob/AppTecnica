module "vpc" {

  source = "./modules/vpc"
  /*   vpcId                = "vpc-0d606441758ee0de5" */
  vpcName      = "PTecnica"
  vpcId        = "vpc-bd16a9d6"
  routeTableId = "rtb-0c783f67"
  cluster_name = "cluster-PTecnica"

  nlb_name = "nlb-pTecnica"


  #Vpc Vars
  name = "vpc"
  cidr = "10.0.0.0/16"

}
