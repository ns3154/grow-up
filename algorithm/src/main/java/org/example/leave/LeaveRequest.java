package org.example.leave;

import java.util.Date;

public class LeaveRequest {

    private Date startTime;

    private Date endTime;

    public LeaveRequest(Date startTime, Date endTime) {
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
