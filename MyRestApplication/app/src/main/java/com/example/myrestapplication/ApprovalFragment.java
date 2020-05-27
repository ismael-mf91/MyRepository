package com.example.myrestapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrestapplication.data.model.Approval;
import com.example.myrestapplication.data.model.ApprovalLab;
import com.example.myrestapplication.data.model.WorkItem;
import com.example.myrestapplication.data.model.WorkItemLab;

import java.util.Collection;

public class ApprovalFragment extends Fragment {

    private final String WORKITEMID = "workitemid";

    private AdapterApprovals adapter;
    private Context context;
    private ApprovalLab mApprovalLab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_workitem, container, false);

        //View v = container.getRootView();
        //findViewById(android.R.id.content).getRootView();
        //context = v.getContext();


        //return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview_approval);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        System.out.println("Argumentos ->> " + getArguments());
        String id = getArguments().getString(WORKITEMID);
        WorkItem workItem = WorkItemLab.getWorkItem(id);

        Collection<Approval> approvals = getApprovals(workItem);
        inicializaAdaptador(approvals,workItem, recyclerView);

    }

    public void inicializaAdaptador(Collection<Approval> approvals, WorkItem workItem, RecyclerView recyclerView){
        adapter = new AdapterApprovals(approvals, workItem);
        recyclerView.setAdapter(adapter);
    }

    public Collection<Approval> getApprovals(WorkItem workItem){
        mApprovalLab = new ApprovalLab(workItem);
        return mApprovalLab.getApprovals();

    }


}
