package com.example.myrestapplication.data.model;

import android.os.AsyncTask;
import com.example.utils.ClientManageApprovals;
import java.util.concurrent.ExecutionException;

public class ManageApprovalLab {

    private static String ouput;

    final String URI = "http://192.168.1.131:8000/identityiq/rest/";
    final String METHOD_MANAGEAPP = "manageapproval";

    private ManageApprovalLab(LoggedInUser userLogged, String workitemId, String approvalId, String action) {
        AsyncTask<String, Void, String> task = new ClientManageApprovals(userLogged, workitemId, approvalId, action).execute(URI.concat(METHOD_MANAGEAPP));

        try {
            ouput = task.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void manageApproval(LoggedInUser userLogged, String workitemId, String approvalId, String action) {
        new ManageApprovalLab(userLogged, workitemId, approvalId, action);
    }

}
