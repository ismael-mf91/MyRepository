package com.example.utils;

import android.os.AsyncTask;

import com.example.myrestapplication.data.model.LoggedInUser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ClientManageWorkItems extends AsyncTask<String, Void, String> {

    final String METHOD_GET = "GET";

    private LoggedInUser userLogged;
    private String id;
    private String action;

    public ClientManageWorkItems(LoggedInUser userLogged, String id, String action) {

        this.userLogged = userLogged;
        this.id = id;
        this.action = action;
    }

    @Override
    protected String doInBackground(String... urls) {

        String output = null;
        Utils utils = new Utils();
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_GET);
            //conn.setRequestProperty("Accept", "application/json");
            conn.addRequestProperty("owner", userLogged.getUserName());
            conn.addRequestProperty("authorization", userLogged.getBasicAuth());
            conn.addRequestProperty("id", id);
            conn.addRequestProperty("action", action);

            output = utils.getOutputJson(conn);
            System.out.println("salida json " + output);
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
