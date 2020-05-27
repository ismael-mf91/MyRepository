package com.example.myrestapplication.data.model;

import android.content.Context;
import android.os.AsyncTask;

import com.example.utils.ClientGetApprovals;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

public class WorkItemLab {

    private static WorkItemLab sWorkItemLab;
    private static Collection<WorkItem> mWorkItems;

    final String URI = "http://192.168.1.131:8000/identityiq/rest/";
    final String METHOD_SHOWAPP = "showapprovals";

    private WorkItemLab(Context context, LoggedInUser userLogged) {
        AsyncTask<String, Void, Collection<WorkItem>> task = new ClientGetApprovals(userLogged).execute(URI.concat(METHOD_SHOWAPP));

        try {
            mWorkItems = task.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WorkItemLab get(Context context, LoggedInUser userLogged) {
        if (sWorkItemLab == null) {
            sWorkItemLab = new WorkItemLab(context, userLogged);
        }
        return sWorkItemLab;
    }

    public Collection<WorkItem> getWorkItems() {
        return mWorkItems;
    }

    public static WorkItem getWorkItem(String id) {
        for (WorkItem workItem : mWorkItems) {
            if (workItem.getWorkitemid().equals(id)) {
                return workItem;
            }
        }
        return null;
    }
}
