package org.example.leave;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveManagementSystem {


    // 请假单类
    static class LeaveRequest {
        private Date startDate;
        private Date endDate;

        public LeaveRequest(Date startDate, Date endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getEndDate() {
            return endDate;
        }
    }

    // 销假单类
    static class CancelLeaveRequest {
        private Date cancelStartDate;
        private Date cancelEndDate;

        public CancelLeaveRequest(Date cancelStartDate, Date cancelEndDate) {
            this.cancelStartDate = cancelStartDate;
            this.cancelEndDate = cancelEndDate;
        }

        public Date getCancelStartDate() {
            return cancelStartDate;
        }

        public Date getCancelEndDate() {
            return cancelEndDate;
        }
    }

    // 判断给定时间是否在请假时间范围内
    public boolean isTimeInLeavePeriod(Date checkTime, LeaveRequest leave, List<CancelLeaveRequest> cancelLeaves) {
        // 检查给定的时间是否在请假单的时间范围内
        boolean isInLeavePeriod = leave != null && !checkTime.before(leave.getStartDate()) && !checkTime.after(leave.getEndDate());

        // 检查销假单是否都在请假单的时间范围内
        boolean allCancelLeavesWithinLeave = cancelLeaves.stream()
                .allMatch(cl -> checkIfCancelLeaveWithinLeave(cl, leave));

        // 如果销假单都在请假单时间范围内，且检查时间不在请假时间范围内，则输出“不在请假范围内”
        return !allCancelLeavesWithinLeave || (isInLeavePeriod && !allCancelLeavesWithinLeave);
    }

    // 辅助方法，用于判断销假单是否在请假单的时间范围内
    private boolean checkIfCancelLeaveWithinLeave(CancelLeaveRequest cancelLeave, LeaveRequest leave) {
        return !cancelLeave.getCancelStartDate().before(leave.getStartDate()) && !cancelLeave.getCancelEndDate().after(leave.getEndDate());
    }

    public static void main(String[] args) {
        LeaveManagementSystem system = new LeaveManagementSystem();

        // 示例：用户提交了请假单
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date startDate = sdf.parse("2024-11-12 08:00");
            Date endDate = sdf.parse("2024-11-12 17:00");
            LeaveRequest leaveRequest = new LeaveRequest(startDate, endDate);

            // 示例：用户提交了两个销假单，这两个销假单的时间范围超出了请假单的时间范围
            List<CancelLeaveRequest> cancelLeaveRequests = new ArrayList<>();
            cancelLeaveRequests.add(new CancelLeaveRequest(sdf.parse("2024-11-12 08:00"), sdf.parse("2024-11-12 11:00")));
            cancelLeaveRequests.add(new CancelLeaveRequest(sdf.parse("2024-11-12 13:00"), sdf.parse("2024-11-12 17:00")));

            // 检查特定时间是否在请假期间
            Date checkTime = sdf.parse("2024-11-12 12:00"); // 用户想要检查的特定时间
            boolean isInLeavePeriod = system.isTimeInLeavePeriod(checkTime, leaveRequest, cancelLeaveRequests);

            System.out.println("时间 " + sdf.format(checkTime) + " 是否在请假时间范围内？ " + (isInLeavePeriod ? "是" : "不在"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
