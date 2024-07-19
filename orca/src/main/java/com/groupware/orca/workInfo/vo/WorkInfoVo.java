package com.groupware.orca.workInfo.vo;

import lombok.Data;

@Data
public class WorkInfoVo {

    private String workNo;
    private int empNo;
    private String workDate;
    private String startTime;
    private String endTime;
    private double overtimeWork;
    private double holidayWork;
    private String partName;
    private String nameOfPosition;
    private String teamName;
    private String name;

    public String getFormattedStartTime() {
        return formatTime(startTime);
    }

    public String getFormattedEndTime() {
        return formatTime(endTime);
    }

    private String formatTime(String time) {
        if (time != null && time.length() >= 16) { // 최소한 16자 이상이어야 하므로 length() >= 16으로 변경
            return time.substring(11, 16); // 문자열 "HH:mm" 형식으로 자르기 위해 인덱스 범위 수정
        }
        return "--:--";
    }

}
