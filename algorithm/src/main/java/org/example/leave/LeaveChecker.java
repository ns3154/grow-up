package org.example.leave;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveChecker {

    public static boolean isOnLeave(List<LeaveRequest> leaveRequests, List<RevokeLeaveRequest> revokeRequests, Date checkTime) {
        // 默认认为不在请假
        boolean onLeave = false;

        // 遍历所有请假记录
        for (LeaveRequest leaveRequest : leaveRequests) {
            if (!checkTime.before(leaveRequest.getStartTime()) && !checkTime.after(leaveRequest.getEndTime())) {
                onLeave = true; // 在某个请假时间段内
                // 检查是否有销假记录覆盖了这个时间点
                for (RevokeLeaveRequest revokeRequest : revokeRequests) {
                    if (!checkTime.before(revokeRequest.getStartTime()) && !checkTime.after(revokeRequest.getEndTime())) {
                        onLeave = false; // 如果在销假期间，则不在请假状态
                        break;
                    }
                }
                if (onLeave) {
                    break; // 如果已经确定在请假状态，不再检查其他请假记录
                }
            }
        }

        return onLeave;
    }


    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        // 创建请假请求
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        leaveRequests.add(new LeaveRequest(sdf.parse("2024-11-21 00:00"), sdf.parse("2024-11-21 23:59"))); // 第一次请假
        leaveRequests.add(new LeaveRequest(sdf.parse("2024-11-21 00:00"), sdf.parse("2024-11-21 23:59"))); // 第二次请假
        leaveRequests.add(new LeaveRequest(sdf.parse("2024-11-21 12:00"), sdf.parse("2024-11-21 23:59"))); // 第三次请假

        // 创建销假请求
        List<RevokeLeaveRequest> revokeRequests = new ArrayList<>();
        revokeRequests.add(new RevokeLeaveRequest(sdf.parse("2024-11-21 00:00"), sdf.parse("2024-11-21 23:59"))); // 第一次销假
        revokeRequests.add(new RevokeLeaveRequest(sdf.parse("2024-11-21 00:00"), sdf.parse("2024-11-21 12:00"))); // 第二次销假

        // 测试时间点
        Date testTime1 = sdf.parse("2024-11-21 09:00"); // 在第一次销假期间
        Date testTime2 = sdf.parse("2024-11-21 15:00"); // 不在销假期间，但在第三次请假期间
        Date testTime3 = sdf.parse("2024-11-21 20:00"); // 在第三次请假期间

        System.out.println("Test Time 1 is on leave: " + LeaveChecker.isOnLeave(leaveRequests, revokeRequests, testTime1)); // 应该输出 false
        System.out.println("Test Time 2 is on leave: " + LeaveChecker.isOnLeave(leaveRequests, revokeRequests, testTime2)); // 应该输出 true
        System.out.println("Test Time 3 is on leave: " + LeaveChecker.isOnLeave(leaveRequests, revokeRequests, testTime3)); // 应该输出 true
    }
}
