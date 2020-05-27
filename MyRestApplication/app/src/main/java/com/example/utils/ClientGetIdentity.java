package com.example.utils;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.myrestapplication.data.model.Identity;
import com.example.myrestapplication.data.model.LoggedInUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ClientGetIdentity extends AsyncTask<String, Void, Identity> {

    final String METHOD_GET = "GET";
    final int SUCCESS = 200;

    private String identityName;

    public ClientGetIdentity(String identityName) {
        this.identityName = identityName;
    }

    @Override
    protected Identity doInBackground(String... urls) {
        Utils utils = new Utils();
        String attributes = null;
        Map map = new HashMap();
        String output= null;
        Identity identity = new Identity();

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_GET);
            //se hace peticion con el token de spadmin, que tiene permisos para ver identidad
            conn.addRequestProperty("authorization", "Basic c3BhZG1pbjphZG1pbg==");

            output = utils.getOutputJson(conn);
            JSONObject json = utils.convertStringToJson(output);
            JSONObject jsonAttr = (JSONObject)json.get("viewableIdentityAttributes");
            attributes = jsonAttr.toString();
            System.out.println("ClientGetIdentity : attributes " + attributes);
            map = utils.convertStringToMap(attributes);
            identity.setViewableIdentityAttributes(map);
            conn.disconnect();
        } catch ( MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return identity;
    }
}
