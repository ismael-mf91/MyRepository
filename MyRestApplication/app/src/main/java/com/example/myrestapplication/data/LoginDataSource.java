package com.example.myrestapplication.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.myrestapplication.data.model.LoggedInUser;
import com.example.myrestapplication.data.model.LoggedInUserLab;

import java.io.IOException;


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    LoggedInUserLab userLab;
    LoggedInUser user;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Result<LoggedInUser> login(String username, String password) {
        System.out.println("AUTH login LoginDataSource" + username + ":" + password);

        userLab = LoggedInUserLab.get(username, password);
        user = LoggedInUserLab.getLoggedInUser();
        if (user != null){
            return new Result.Success<>(user);
        }else{
            return new Result.Error(new IOException("Error logging in", new Exception()));
        }


    }

    public void logout() {
        //TODO implement logout
    }
}
