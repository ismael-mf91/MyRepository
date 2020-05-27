package com.example.myrestapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.myrestapplication.data.model.WorkItem;
import com.example.myrestapplication.data.model.LoggedInUser;
import com.example.myrestapplication.data.model.LoggedInUserLab;
import com.example.myrestapplication.data.model.WorkItemLab;

public class WorkItemFragment extends Fragment {

    private static final String WORKITEM_ID = "workitemid";
    private WorkItem workItem;

    public static WorkItemFragment newInstance(String workitemid) {
        Bundle args = new Bundle();
        args.putSerializable(WORKITEM_ID, workitemid);

        WorkItemFragment fragment = new WorkItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String workitemid = (String) getArguments().getSerializable(WORKITEM_ID);
        LoggedInUser user = LoggedInUserLab.getLoggedInUser();

        workItem = WorkItemLab.get(getActivity(),user).getWorkItem(workitemid);
    }
}
