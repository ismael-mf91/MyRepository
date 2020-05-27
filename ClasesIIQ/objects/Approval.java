package iiq.objects;

/**
 *
 * @author midpoint
 */
public class Approval {
    private String operation;
    private String role;
    private String idapproval;

    public Approval(String operation, String role, String idapproval) {
        this.operation = operation;
        this.role = role;
        this.idapproval = idapproval;
    }

    public Approval() {

    }
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdapproval() {
        return idapproval;
    }

    public void setIdapproval(String idapproval) {
        this.idapproval = idapproval;
    }
      
}

