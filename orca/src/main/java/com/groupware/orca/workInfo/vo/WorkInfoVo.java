package com.groupware.orca.workInfo.vo;

import lombok.Data;

@Data
public class WorkInfoVo {

    private String work_no;
    private String emp_no;
    private String work_date;
    private String start_time;
    private String end_time;
    private String overtime_work;
    private String holiday_work;

}
