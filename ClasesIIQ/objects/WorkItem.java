
package iiq.objects;

import java.util.List;


public class WorkItem {
     
    private String workitemid;
    private String requestid;
    private String description;
    private String requester;
    private List<Approval> approval;

    public WorkItem(String workitemid, String requestid, String description, List<Approval> approval, String requester) {
        this.workitemid = workitemid;
        this.requestid = requestid;
        this.description = description;
        this.approval = approval;
        this.requester = requester;
        
    }

    public WorkItem() {

    }
    
    public String getWorkitemid() {
        return workitemid;
    }

    public void setWorkitemid(String workitemid) {
        this.workitemid = workitemid;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Approval> getApproval() {
        return approval;
    }

    public void setApproval(List<Approval> approval) {
        this.approval = approval;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }
    
    
    
    
}

