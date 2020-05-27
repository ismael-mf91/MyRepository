package com.example.myrestapplication.fragments;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class WorkItemActivity extends SingleFragmentActivity {

    private static final String WORKITEM_ID = "workitemid";

    public static Intent newIntent(Context packageContext, String workitemid) {
        Intent intent = new Intent(packageContext, WorkItemActivity.class);
        intent.putExtra(WORKITEM_ID, workitemid);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return null;
    }
}
