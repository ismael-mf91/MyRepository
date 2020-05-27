package com.example.myrestapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrestapplication.data.model.ManageApprovalLab;
import com.example.myrestapplication.data.model.ManageWorkItemLab;
import com.example.myrestapplication.data.model.WorkItem;
import com.example.myrestapplication.data.model.LoggedInUser;
import com.example.myrestapplication.data.model.LoggedInUserLab;
import com.example.myrestapplication.data.model.WorkItemLab;

import java.util.Collection;


public class WorkItemListActivity extends AppCompatActivity {

    private MyAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inicalizar el recyclerview
        setContentView(R.layout.fragment_workitem_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.crime_recycler_view);

        //Crear adaptador
        LoggedInUser user = LoggedInUserLab.getLoggedInUser();
        System.out.println("WorkItemListActivity -> user: " + user.getUserName());
        WorkItemLab workItemLab = WorkItemLab.get(this, user);
        Collection<WorkItem> workItems = workItemLab.getWorkItems();

        adapter = new MyAdapter(workItems);

        //Fijar el tamaño de los elementos
        recyclerView.setHasFixedSize(true);

        //Declarar la visualización de los elementos
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Asignar el adaptador al recyclerview
        recyclerView.setAdapter(adapter);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private ManageWorkItemLab manageWorkItemLab;
        private LoggedInUser user;
        private Collection<WorkItem> mDataSet;

        public MyAdapter(Collection<WorkItem> mDataSet) {
            this.mDataSet = mDataSet;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_workitem, parent, false);

            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            WorkItem workItem = (WorkItem)mDataSet.toArray()[position];
            holder.bindData(workItem);
        }

        @Override
        public int getItemCount() {

            return (mDataSet != null) ? mDataSet.size() : 0;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

            private TextView mDescriptionTextView;
            private TextView mRequesterTextView;
            private WorkItem mWorkItem;

            public MyViewHolder(@NonNull View v) {
                super(v);
                //mDescriptionTextView = (TextView) v.findViewById(android.R.id.text1);
                //mRequesterTextView =  (TextView) v.findViewById(R.id.text2);

                mDescriptionTextView =  (TextView) v.findViewById(R.id.list_item_description);
                mRequesterTextView =  (TextView) v.findViewById(R.id.list_item_requester);

                // Handle item click and set the selection
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Redraw the old selection and the new
                        String id = mWorkItem.getWorkitemid();
                        Log.d("ID","onClick->> " + id);
                        Intent intent = new Intent(WorkItemListActivity.this, WorkItemOnlyActivity.class);
                        intent.putExtra("workitemid", id);
                        startActivity(intent);
                    }
                });
            }



            public void bindData(final WorkItem data) {
                mWorkItem = data;
                mDescriptionTextView.setText(mWorkItem.getDescription());
                mRequesterTextView.setText(mWorkItem.getRequester());
            }

            @Override
            public void onClick(View v) {
                user = LoggedInUserLab.getLoggedInUser();
                String action = null;
                switch (v.getId()){
                    case R.id.id_approve_button:
                        action = "approve";
                        break;
                    case R.id.id_reject_button:
                        action = "reject";
                        break;
                }
                manageWorkItemLab.manageWorkItem(user, mWorkItem.getWorkitemid(), action);
                System.out.println("approval manageWorkItem " + mWorkItem.getWorkitemid() + " " + action);

            }
        }
    }
}
