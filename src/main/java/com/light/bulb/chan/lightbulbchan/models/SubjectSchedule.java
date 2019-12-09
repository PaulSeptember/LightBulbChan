package com.light.bulb.chan.lightbulbchan.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectSchedule {
    private Long[] weekNumber;
    private String lessonType;
    private String[] auditory;
    private String subject;
    private Employee[] employee;
    private String lessonTime;

    public Long[] getWeekNumber() {
        return weekNumber;
    }

    public SubjectSchedule setWeekNumber(Long[] weekNumber) {
        this.weekNumber = weekNumber;
        return this;
    }

    public String getLessonType() {
        return lessonType;
    }

    public SubjectSchedule setLessonType(String lessonType) {
        this.lessonType = lessonType;
        return this;
    }

    public String[] getAuditory() {
        return auditory;
    }

    public SubjectSchedule setAuditory(String[] auditory) {
        this.auditory = auditory;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public SubjectSchedule setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Employee[] getEmployee() {
        return employee;
    }

    public SubjectSchedule setEmployee(Employee[] employee) {
        this.employee = employee;
        return this;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public SubjectSchedule setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
        return this;
    }
}
