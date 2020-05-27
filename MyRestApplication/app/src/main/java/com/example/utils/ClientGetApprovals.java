package com.example.utils;

import android.os.AsyncTask;

import com.example.myrestapplication.data.model.WorkItem;
import com.example.myrestapplication.data.model.LoggedInUser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

public class ClientGetApprovals extends AsyncTask <String, Void, Collection<WorkItem>> {

    final String METHOD_GET = "GET";

    private LoggedInUser userLogged;
    public ClientGetApprovals(LoggedInUser userLogged) {
        this.userLogged = userLogged;
    }

    @Override
    protected Collection<WorkItem> doInBackground(String... urls) {
        String output = "";
        Collection<WorkItem>  wi = null;
        Utils utils = new Utils();

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_GET);
            //conn.setRequestProperty("Accept", "application/json");
            conn.addRequestProperty("owner", userLogged.getUserName());
            conn.addRequestProperty("authorization", userLogged.getBasicAuth());

            output = utils.getOutputJson(conn);
            wi = utils.convertStringToObject(output);
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wi;
    }
}
