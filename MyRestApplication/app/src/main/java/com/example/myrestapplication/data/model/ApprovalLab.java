package com.example.myrestapplication.data.model;

import java.util.Collection;

public class ApprovalLab {

    private Collection<Approval>  mApprovals;
    private static ApprovalLab sApprovalLab;

    public ApprovalLab(WorkItem workItem) {
        mApprovals = workItem.getApproval();

    }

    public static ApprovalLab get(WorkItem workItem) {
        if (sApprovalLab == null) {
            sApprovalLab = new ApprovalLab(workItem);
        }
        return sApprovalLab;
    }

    public Collection<Approval> getApprovals() {
        return mApprovals;
    }
}
