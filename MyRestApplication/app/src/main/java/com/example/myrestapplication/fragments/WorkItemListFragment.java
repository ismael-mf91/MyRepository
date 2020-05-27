package com.example.myrestapplication.fragments;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrestapplication.data.model.WorkItem;
import com.example.myrestapplication.R;
import com.example.myrestapplication.data.model.LoggedInUser;
import com.example.myrestapplication.data.model.LoggedInUserLab;
import com.example.myrestapplication.data.model.WorkItemLab;

import java.util.Collection;

public class WorkItemListFragment extends Fragment {

    private RecyclerView mWorkItemRecyclerView;
    private WorkItemAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workitem_list, container, false);
        mWorkItemRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mWorkItemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    private void updateUI() {
        LoggedInUser user = LoggedInUserLab.getLoggedInUser();
        WorkItemLab workItemLab = WorkItemLab.get(getActivity(), user);
        Collection<WorkItem> workItems = workItemLab.getWorkItems();

        if (mAdapter == null) {
            mAdapter = new WorkItemAdapter(workItems);

            System.out.println("workItems WorkItemAdapter--> " + workItems.toString());
            mWorkItemRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class WorkItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mRequesterTextView;
        private TextView mDescriptionTextView;

        private WorkItem mWorkItem;

        public WorkItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mRequesterTextView =  itemView.findViewById(R.id.list_item_requester);
            mDescriptionTextView = itemView.findViewById(R.id.list_item_description);

        }

        public void bindWorkItem(WorkItem workItem) {
            mWorkItem = workItem;
            System.out.println("WorkItem ->>>>>> " + mWorkItem.getDescription());
            System.out.println("getRequester ->>>>>> " + mWorkItem.getRequester());

            mRequesterTextView.setText(workItem.getRequester());
            mDescriptionTextView.setText(workItem.getDescription());
        }

        @Override
        public void onClick(View v) {
            Intent intent = WorkItemActivity.newIntent(getActivity(), mWorkItem.getWorkitemid());
            startActivity(intent);
        }
    }

    private class WorkItemAdapter extends RecyclerView.Adapter<WorkItemHolder> {
        private Collection<WorkItem> mWorkItems;

        public WorkItemAdapter(Collection<WorkItem> workItems) {
            mWorkItems = workItems;
        }

        @Override
        public WorkItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            System.out.println("enter onCreateViewHolder --->> " );

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_workitem, parent, false);
            System.out.println("exit onCreateViewHolder --->> " );

            return new WorkItemHolder(view);
        }

        @Override
        public void onBindViewHolder(WorkItemHolder holder, int position) {
            System.out.println("Position -> " + position);
            WorkItem workItem = (WorkItem) mWorkItems.toArray()[position];
            System.out.println("WorkItem --->> " + workItem.getDescription());

            holder.bindWorkItem(workItem);
        }

        @Override
        public int getItemCount() {
            System.out.println("mWorkItems.size() --->> " + mWorkItems.size());

            return mWorkItems.size();
        }
    }
}
