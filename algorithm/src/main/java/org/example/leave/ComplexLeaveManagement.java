package org.example.leave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplexLeaveManagement {


    private List<LeavePeriod> leavePeriods;

    public ComplexLeaveManagement() {
        this.leavePeriods = new ArrayList<>();
    }

    // 用于表示一次请假的时间段
    private class LeavePeriod {
        Date leaveStartTime;
        Date leaveEndTime;
        List<Date[]> cancellationTimeRanges;

        public LeavePeriod(Date leaveStart, Date leaveEnd) {
            this.leaveStartTime = leaveStart;
            this.leaveEndTime = leaveEnd;
            this.cancellationTimeRanges = new ArrayList<>();
        }

        public void addCancellation(Date cancellationStart, Date cancellationEnd) {
            cancellationTimeRanges.add(new Date[]{cancellationStart, cancellationEnd});
        }

        public boolean isTimeInLeaveRange(Date checkTime) {
            // 先检查是否有销假单
            if (!cancellationTimeRanges.isEmpty()) {
                // 遍历每个销假单的时间范围
                for (Date[] cancellationRange : cancellationTimeRanges) {
                    Date cancellationStartTime = cancellationRange[0];
                    Date cancellationEndTime = cancellationRange[1];

                    // 检查输入时间是否在当前销假单时间范围内
                    if (checkTime.after(cancellationStartTime) && checkTime.before(cancellationEndTime)) {
                        return true;
                    }
                }
            }

            // 如果不在任何销假单时间范围内，检查是否在原始请假时间范围内
            return checkTime.after(leaveStartTime) && checkTime.before(leaveEndTime);
        }
    }

    public void addLeavePeriod(Date leaveStart, Date leaveEnd) {
        LeavePeriod leavePeriod = new LeavePeriod(leaveStart, leaveEnd);
        leavePeriods.add(leavePeriod);
    }

    public void addCancellationForLastLeavePeriod(Date cancellationStart, Date cancellationEnd) {
        if (!leavePeriods.isEmpty()) {
            LeavePeriod lastLeavePeriod = leavePeriods.get(leavePeriods.size() - 1);
            lastLeavePeriod.addCancellation(cancellationStart, cancellationEnd);
        } else {
            System.out.println("还未添加任何请假时间段，无法添加销假单。");
        }
    }

    public boolean isTimeInLeaveRange(Date checkTime) {
        // 遍历所有请假时间段
        for (LeavePeriod leavePeriod : leavePeriods) {
            if (leavePeriod.isTimeInLeaveRange(checkTime)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ComplexLeaveManagement leaveManager = new ComplexLeaveManagement();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 第一次请假及销假
        try {
            Date firstLeaveStart = sdf.parse("2024-11-21 00:00:00");
            Date firstLeaveEnd = sdf.parse("2024-11-21 23:59:59");
            leaveManager.addLeavePeriod(firstLeaveStart, firstLeaveEnd);
            Date firstCancellationStart = sdf.parse("2024-11-21 10:00:00");
            Date firstCancellationEnd = sdf.parse("2024-11-21 15:00:00");
            leaveManager.addCancellationForLastLeavePeriod(firstCancellationStart, firstCancellationEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 第二次请假及销假
        try {
            Date secondLeaveStart = sdf.parse("2024-11-21 00:00:00");
            Date secondLeaveEnd = sdf.parse("2024-11-21 23:59:59");
            leaveManager.addLeavePeriod(secondLeaveStart, secondLeaveEnd);
            Date secondCancellationStart = sdf.parse("2024-11-21 16:00:00");
            Date secondCancellationEnd = sdf.parse("2024-11-21 20:00:00");
            leaveManager.addCancellationForLastLeavePeriod(secondCancellationStart, secondCancellationEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 第三次请假及销假（只请下午假）
        try {
            Date thirdLeaveStart = sdf.parse("2024-11-21 12:00:00");
            Date thirdLeaveEnd = sdf.parse("2024-11-21 18:00:00");
            leaveManager.addLeavePeriod(thirdLeaveStart, thirdLeaveEnd);
            Date thirdCancellationStart = sdf.parse("2024-11-21 14:00:00");
            Date thirdCancellationEnd = sdf.parse("2024-11-21 16:00:00");
            leaveManager.addCancellationForLastLeavePeriod(thirdCancellationStart, thirdCancellationEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date checkTime;
        try {
            checkTime = sdf.parse("2024-11-21 15:00:00");
            boolean result = leaveManager.isTimeInLeaveRange(checkTime);
            if (result) {
                System.out.println("该时间在请假（或销假）时间范围内。");
            } else {
                System.out.println("该时间不在请假（或销价）时间范围内。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}