package iiq.custom;

import sailpoint.rest.SailPointRestApplication;


public class CustomRest extends SailPointRestApplication{
    
   
  public CustomRest() {
        super();
        register(ShowApprovalsResource.class);
        register(ManageWorkItemResource.class);
        register(ManageApprovalResource.class);
    } 
    
}

