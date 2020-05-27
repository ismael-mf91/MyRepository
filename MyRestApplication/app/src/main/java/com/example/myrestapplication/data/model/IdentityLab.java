package com.example.myrestapplication.data.model;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.utils.ClientAuthentication;
import com.example.utils.ClientGetIdentity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class IdentityLab {

    private static IdentityLab sIdentityLab;
    private static Identity identity;

    final String URI = "http://192.168.1.131:8000/identityiq/rest/";
    final String METHOD_AUTH = "identities";
    final String SEPARATOR = "/";

    @RequiresApi(api = Build.VERSION_CODES.O)
    private IdentityLab(String username) {
        AsyncTask<String, Void, Identity> task = new ClientGetIdentity(username).execute(URI.concat(METHOD_AUTH).concat(SEPARATOR).concat(username));
        try {
            identity = task.get();
            System.out.println("sIdentityLab - Identity : " + identity);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static IdentityLab get(String username) {
        if (sIdentityLab == null) {
            sIdentityLab = new IdentityLab(username);
        }
        return sIdentityLab;
    }

    public Identity getIdentity() {
        return identity;
    }
}
