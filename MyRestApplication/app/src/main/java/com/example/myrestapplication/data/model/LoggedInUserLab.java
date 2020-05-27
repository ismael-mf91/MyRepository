package com.example.myrestapplication.data.model;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.utils.ClientAuthentication;

import java.util.concurrent.ExecutionException;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUserLab {

    private static LoggedInUserLab sLoggedInUserLab;
    private static LoggedInUser loggedInUser;

    final String URI = "http://192.168.1.131:8000/identityiq/rest/";
    final String METHOD_AUTH = "authentication";


    @RequiresApi(api = Build.VERSION_CODES.O)
    private LoggedInUserLab(String username, String password) {
        AsyncTask<String, Void, LoggedInUser> task = new ClientAuthentication(username, password).execute(URI.concat(METHOD_AUTH));
        try {
            loggedInUser = task.get();
            System.out.println("LoggedInUserLab - loggedInUser : " + loggedInUser);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LoggedInUserLab get(String username, String password) {
        if (sLoggedInUserLab == null) {
            sLoggedInUserLab = new LoggedInUserLab(username, password);
        }
        return sLoggedInUserLab;
    }

    public static LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public static void destroyUserLogged() {
        System.out.println("destroyUserLogged() " + loggedInUser.getUserName());
        loggedInUser = null;
        sLoggedInUserLab = null;
        System.out.println("destroyUserLogged() ->>" + loggedInUser);

    }
}
