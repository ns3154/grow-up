package org.example.leave;

import java.util.Date;

public class RevokeLeaveRequest {

    private Date startTime;

    private Date endTime;

    public RevokeLeaveRequest(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
