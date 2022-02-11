package com.ntr.api;

public enum WeekReference{
    MON("一","Mon",1),
    TUE("二","Tue",2),
    WED("三","Wed",3),
    THU("四","Thu",4),
    FRI("五","Fri",5),
    SAT("六","Sat",6),
    SUN("日","Sun",7),
    ;

    private final String name;
    private final String refName;
    private final Integer code;

    WeekReference(String name,String refName,Integer code) {
        this.name = name;
        this.refName = refName;
        this.code = code;
    }



    public String getName() {
        return this.name;
    }

    public String getRefName() {
        return this.refName;
    }

    public Integer getCode() {
        return this.code;
    }
}
