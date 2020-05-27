package com.example.myrestapplication.data.model;

import android.os.AsyncTask;

import com.example.utils.ClientManageApprovals;
import com.example.utils.ClientManageWorkItems;

import java.util.concurrent.ExecutionException;

public class ManageWorkItemLab {

    private static String ouput;

    final String URI = "http://192.168.1.131:8000/identityiq/rest/";
    final String METHOD_MANAGEWI = "manageworkitem";

    private ManageWorkItemLab(LoggedInUser userLogged, String workitemId, String action) {
        AsyncTask<String, Void, String> task = new ClientManageWorkItems(userLogged, workitemId, action).execute(URI.concat(METHOD_MANAGEWI));

        try {
            ouput = task.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void manageWorkItem(LoggedInUser userLogged, String workitemId, String action) {
        new ManageWorkItemLab(userLogged, workitemId, action);
    }

}
