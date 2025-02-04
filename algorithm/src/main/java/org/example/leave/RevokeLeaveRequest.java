package org.example.leave;

import lombok.Getter;

import java.util.Date;

@Getter
public class RevokeLeaveRequest {

    private final Date startTime;

    private final Date endTime;

    public RevokeLeaveRequest(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
