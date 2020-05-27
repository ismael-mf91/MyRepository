package iiq.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sailpoint.object.QueryOptions;
import sailpoint.object.Filter;
import sailpoint.object.WorkItem;
import sailpoint.object.ApprovalSet;
import sailpoint.object.ApprovalItem;
import sailpoint.api.SailPointContext;
import sailpoint.tools.GeneralException;

import sailpoint.object.WorkItem;
import sailpoint.api.Workflower;
import sailpoint.object.ApprovalSet;
import sailpoint.object.ApprovalItem;

public class IIQMethodsLibrary {
       
    public List getApprovals(String owner, SailPointContext context){
        System.out.println("enter method getApprovals");
        System.out.println("owner: " + owner);
        System.out.println("context: " + context);
            
        QueryOptions qo =  new QueryOptions();
        qo.addFilter(Filter.eq("type","Approval"));
        qo.addFilter(Filter.eq("owner.name",owner));
            
        List workItemlist = new ArrayList();
        List itemlist=null;
            
        try {
            List<WorkItem> workItems = context.getObjects(WorkItem.class,qo);
            
            for (WorkItem wi : workItems){
                iiq.objects.WorkItem workitem = new iiq.objects.WorkItem();
                
                workitem.setWorkitemid(wi.getId());
                workitem.setRequester(wi.getRequester().getName());
                workitem.setDescription(wi.getDescription());
                workitem.setRequestid(wi.getIdentityRequestId());
                
                ApprovalSet approvalSet = wi.getApprovalSet();
                System.out.println("approvalSet " + approvalSet.toXml());
                List<ApprovalItem> items = approvalSet.getItems();               
                
                
                itemlist = new ArrayList();
                for (ApprovalItem item : items){
                    
                    System.out.println("Se itera item " + item.toXml());
                    iiq.objects.Approval approval = new iiq.objects.Approval();
                    
                    approval.setOperation(item.getAttribute("operation").toString());
                    approval.setIdapproval(item.getAttribute("id").toString());
                    approval.setRole(item.getDisplayValue());
                    
                    itemlist.add(approval);
                    
                    System.out.println("workitem " + workitem);
                    workitem.setApproval(itemlist);
                }
                
                workItemlist.add(workitem);
                
            }
        } catch (GeneralException ex) {
            Logger.getLogger(IIQMethodsLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("workItemlist: " + workItemlist);
        return workItemlist;
        
    }
    
    public void manageWorkItem(String id, String owner, SailPointContext context, String action){
        
        try {
            QueryOptions qo =  new QueryOptions();
            //qo.addFilter(Filter.eq("name","0000000146"));
            qo.addFilter(Filter.eq("id", id));
            List<WorkItem> workItems = context.getObjects(WorkItem.class, qo);
            
            if (workItems != null && !workItems.isEmpty()){
                WorkItem wi = workItems.get(0);
                if (wi.getDescription()!=null && !wi.getDescription().equals("")){
                    
                    ApprovalSet approval = wi.getApprovalSet();
                    List<ApprovalItem> items = approval.getItems();
                    
                    for (ApprovalItem appItem: items){
                        if (action.equalsIgnoreCase("approve")) {
                            appItem.approve();
                            System.out.println("Se aprueba: " + appItem.toXml());
                        }else if (action.equalsIgnoreCase("reject")) {
                            appItem.reject();
                            System.out.println("Se rechaza: " + appItem.toXml());
                        }
                                           
                    }
                    
                    wi.setCompleter(owner);
                    wi.setState(WorkItem.State.Finished);
                    Workflower workflower = new Workflower(context);
                    workflower.process(wi, false);
                    
                }                        
            }
        } catch (GeneralException ex) {
            Logger.getLogger(IIQMethodsLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void manageApproval(String WorkItemId, String ApprovalId, String owner, SailPointContext context, String action){
        
        try {
            QueryOptions qo =  new QueryOptions();
            qo.addFilter(Filter.eq("id", WorkItemId));
            List<WorkItem> workItems = context.getObjects(WorkItem.class, qo);
            
            if (workItems != null && !workItems.isEmpty()){
                WorkItem wi = workItems.get(0);
                if (wi.getDescription()!=null && !wi.getDescription().equals("")){
                    
                    ApprovalSet approval = wi.getApprovalSet();
                    List<ApprovalItem> items = approval.getItems();
                    
                    for (ApprovalItem appItem: items){
                            String idAppItem = appItem.getAttributes().get("id").toString();
                            System.out.println("manageApproval : idAppItem " + idAppItem);                   
                            System.out.println("manageApproval : ApprovalId " + ApprovalId);                   

                        if (idAppItem.equals(ApprovalId)){
                     
                            if (action.equalsIgnoreCase("approve")) {
                                appItem.approve();
                            }else if (action.equalsIgnoreCase("reject")) {
                                appItem.reject();
                            }
                                                       
                            //return appItem.isApproved();                                                   
                        }
                        System.out.println(action + " : " + appItem.toXml());                   
                    }
                    
                    if(approval.isAllProvisioned()){
                        wi.setCompleter(owner);
                        wi.setState(WorkItem.State.Finished);
                        Workflower workflower = new Workflower(context);
                        workflower.process(wi, false);
                    }
                    
                }                        
            }
        } catch (GeneralException ex) {
            Logger.getLogger(IIQMethodsLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public String convertObjectToJSON(Object object){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);

        return json;
        
    }    
    
}
