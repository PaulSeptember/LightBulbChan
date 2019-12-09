package com.light.bulb.chan.lightbulbchan.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DaySchedule {
    private String weekDay;
    private SubjectSchedule[] schedule;

    public String getWeekDay() {
        return weekDay;
    }

    public DaySchedule setWeekDay(String weekDay) {
        this.weekDay = weekDay;
        return this;
    }

    public SubjectSchedule[] getSchedule() {
        return schedule;
    }

    public DaySchedule setSchedule(SubjectSchedule[] schedule) {
        this.schedule = schedule;
        return this;
    }
}
