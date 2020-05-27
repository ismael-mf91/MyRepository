package com.example.utils;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.myrestapplication.data.model.LoggedInUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ClientAuthentication extends AsyncTask<String, Void, LoggedInUser> {

    final String METHOD_GET = "GET";
    final int SUCCESS = 200;

    private String username;
    private String password;

    public ClientAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected LoggedInUser doInBackground(String... urls) {
        Utils utils = new Utils();
        String status= null;
        String output= null;
        String userCredentials = username +":" + password;
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(userCredentials.getBytes());

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_GET);
            //conn.setRequestProperty("Accept", "application/json");
            conn.addRequestProperty("authorization",basicAuth);
            System.out.println("userCredentials --->> " + userCredentials);
            System.out.println("basicAuth --->> " + basicAuth);
            output = utils.getOutputJson(conn);
            JSONObject json = utils.convertStringToJson(output);
            status = (String) json.get("authentication");
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return status.equals("success")? new LoggedInUser(basicAuth,username) : null;
    }
}
