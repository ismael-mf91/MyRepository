package com.example.myrestapplication;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myrestapplication.data.model.WorkItem;
import com.example.myrestapplication.data.model.WorkItemLab;


public class WorkItemOnlyActivity extends AppCompatActivity {

    private final String WORKITEMID = "workitemid";


    private TextView textView_id_workItem;
    private TextView textView_id_request;
    private TextView textView_requester;
    private TextView textView_description;

    private WorkItem workItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workitem);

        Intent intent = getIntent();
        String id = intent.getStringExtra(WORKITEMID);

        workItem = WorkItemLab.getWorkItem(id);

        textView_id_workItem = (TextView)findViewById(R.id.id_workItem_textView);
        textView_id_request = (TextView)findViewById(R.id.id_request_textView);
        textView_requester = (TextView)findViewById(R.id.requester_textView);
        textView_description= (TextView)findViewById(R.id.description_textView);

        textView_id_workItem.setText(id);
        textView_id_request.setText(workItem.getRequestid());
        textView_requester.setText(workItem.getRequester());
        textView_description.setText(workItem.getDescription());

        ApprovalFragment approvalFragment = new ApprovalFragment();
        approvalFragment.setArguments(getIntent().getExtras());

        System.out.println("WorkItemOnlyActivity arguments "  + approvalFragment.getArguments().get(WORKITEMID));

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction().replace(R.id.id_frameLayout_approvals, approvalFragment).commit();
        //getSupportFragmentManager().beginTransaction().add(R.id.id_frameLayout_approvals, approvalFragment).commit();

        System.out.println("WorkItemOnlyActivity commit() "  );

    }

}
