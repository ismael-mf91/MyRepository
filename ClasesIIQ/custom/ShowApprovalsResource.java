
package iiq.custom;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import iiq.utils.IIQMethodsLibrary;
import java.util.List;
import sailpoint.rest.BaseResource;
import sailpoint.api.SailPointContext;

@Path("showapprovals")
public class ShowApprovalsResource extends BaseResource{

    @Context
    private UriInfo context;

    
    public ShowApprovalsResource() {
    }
 
     @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText(@HeaderParam("owner") String owner) {
        System.out.println("Invoke showapprovals");
        System.out.println("showapprovals - owner: " + owner);
        
        IIQMethodsLibrary library = new IIQMethodsLibrary();
        System.out.println("Obteniedno context ");
        SailPointContext cx = super.getContext();
        System.out.println("--contex " + cx);
        List list = library.getApprovals(owner, cx);
        String json = library.convertObjectToJSON(list);
        
        System.out.println("--json " + json);
        return json;           
    }

    /**
     * PUT method for updating or creating an instance of AuthenticateResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putXml(String content) {
    }
}

