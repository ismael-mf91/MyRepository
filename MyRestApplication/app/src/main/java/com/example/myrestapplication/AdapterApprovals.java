package com.example.myrestapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrestapplication.data.model.Approval;
import com.example.myrestapplication.data.model.LoggedInUser;
import com.example.myrestapplication.data.model.LoggedInUserLab;
import com.example.myrestapplication.data.model.ManageApprovalLab;
import com.example.myrestapplication.data.model.WorkItem;

import java.util.Collection;

public class AdapterApprovals extends RecyclerView.Adapter<AdapterApprovals.MyViewHolder>{
    private ManageApprovalLab manageApprovalLab;
    private LoggedInUser user;
    private Collection<Approval> mDataSet;
    private WorkItem workItem;

    public AdapterApprovals(Collection<Approval> mDataSet, WorkItem workItem) {
        System.out.println("AdapterApprovals invoked");
        this.mDataSet = mDataSet;
        this.workItem = workItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_approvals, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Approval approval = (Approval)mDataSet.toArray()[position];
        System.out.println("onBindViewHolder approval --> " + approval.getIdapproval());
        holder.bindData(approval);
        holder.setOnClickListerner();
    }

    @Override
    public int getItemCount() {
        System.out.println("getItemCount() " + mDataSet.size());
        return (mDataSet != null) ? mDataSet.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mOperation;
        private TextView mEntitlement;
        private Approval mApproval;

        private Button mApproveButton;
        private Button mRejectButton;

        public MyViewHolder(@NonNull View v) {
            super(v);

            mOperation =  (TextView) v.findViewById(R.id.id_action);
            mEntitlement =  (TextView) v.findViewById(R.id.id_entitlement);

            mApproveButton = (Button) v.findViewById(R.id.id_approve_button);
            mRejectButton = (Button) v.findViewById(R.id.id_reject_button);
        }

        public void bindData(final Approval data) {
            mApproval = data;
            mOperation.setText(mApproval.getOperation());
            mEntitlement.setText("Acceso a " + mApproval.getRole());
        }

        void setOnClickListerner(){
            mApproveButton.setOnClickListener(this);
            mRejectButton.setOnClickListener(this);
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
            manageApprovalLab.manageApproval(user, workItem.getWorkitemid(), mApproval.getIdapproval(), action);
            System.out.println("approval " + mApproval.getIdapproval() + " " + action);
        }
    }
}
