package com.light.bulb.chan.lightbulbchan.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FullSchedule {
    private DaySchedule[] schedules;
    private Long currentWeekNumber;

    public DaySchedule[] getSchedules() {
        return schedules;
    }

    public FullSchedule setSchedules(DaySchedule[] schedules) {
        this.schedules = schedules;
        return this;
    }

    public Long getCurrentWeekNumber() {
        return currentWeekNumber;
    }

    public FullSchedule setCurrentWeekNumber(Long currentWeekNumber) {
        this.currentWeekNumber = currentWeekNumber;
        return this;
    }
}
