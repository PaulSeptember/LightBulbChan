package com.light.bulb.chan.lightbulbchan;

import com.light.bulb.chan.lightbulbchan.models.FullSchedule;
import com.light.bulb.chan.lightbulbchan.models.Response;
import com.light.bulb.chan.lightbulbchan.models.SubjectSchedule;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;

@Service
public class ScheduleManager {
    private final RestTemplate restTemplate;

    public static String parseSchedule(final FullSchedule fullSchedule) {
        StringBuilder result = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = (dayOfWeek + 5) % 7;
        for (int i = 0; i < 7; ++i) {
            if ((i + dayOfWeek) % 7 == 6) {
                continue;
            }
            if (fullSchedule.getSchedules()[(i + dayOfWeek) % 7].getSchedule() == null) {
                continue;
            }

            SubjectSchedule[] subjects = fullSchedule.getSchedules()[(i + dayOfWeek) % 7].getSchedule();
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < subjects.length; ++j) {
                for (Long x : subjects[i].getWeekNumber()) {
                    if (x.equals(fullSchedule.getCurrentWeekNumber())) {
                        temp.append("Предмет: ").append(subjects[j].getSubject()).append(" ")
                                .append(subjects[j].getLessonType()).append("\n");
                        if (subjects[j].getAuditory().length > 0) {
                            temp.append("Аудитория: ").append(subjects[j].getAuditory()[0]).append("\n");
                        }
                        if (subjects[j].getEmployee().length > 0) {
                            temp.append("Ведет: ").append(subjects[j].getEmployee()[0].getFio()).append("\n");
                        }
                        temp.append("Время: ").append(subjects[j].getLessonTime()).append("\n\n\n");
                        break;
                    }

                }
            }

            if (temp.length() > 0) {
                result.append("Ближайший учебный день: ").append(fullSchedule.getSchedules()[(i + dayOfWeek) % 7].getWeekDay()).append("\n").append(temp);
                return result.toString();
            }
        }

        result.append("Знаешь я не нашел пар так что... Отдыхай )");
        return result.toString();
    }

    public static String parseForDay(final FullSchedule fullSchedule, final int day) {
        StringBuilder result = new StringBuilder();

        if (fullSchedule == null || fullSchedule.getSchedules() == null || fullSchedule.getSchedules().length <= day) {
            result.append("Знаешь я не нашел пар так, что... Отдыхай )");
            return result.toString();
        }

        SubjectSchedule[] subjects = fullSchedule.getSchedules()[day].getSchedule();
        StringBuilder temp = new StringBuilder();

        for (int j = 0; j < subjects.length; ++j) {
            for (Long x : subjects[j].getWeekNumber()) {
                if (x.equals(fullSchedule.getCurrentWeekNumber())) {
                    temp.append("Предмет: ").append(subjects[j].getSubject()).append(" ")
                            .append(subjects[j].getLessonType()).append("\n");
                    if (subjects[j].getAuditory().length > 0) {
                        temp.append("Аудитория: ").append(subjects[j].getAuditory()[0]).append("\n");
                    }
                    if (subjects[j].getEmployee().length > 0) {
                        temp.append("Ведет: ").append(subjects[j].getEmployee()[0].getFio()).append("\n");
                    }
                    temp.append("Время: ").append(subjects[j].getLessonTime()).append("\n\n\n");
                    break;
                }

            }
        }

        if (temp.length() > 0) {
            result.append(fullSchedule.getSchedules()[day].getWeekDay()).append("\n").append(temp);
            return result.toString();
        }

        result.append("Знаешь я не нашел пар так, что... Отдыхай )");
        return result.toString();
    }

    public ScheduleManager() {
        restTemplate = new RestTemplate();
    }

    public Response<FullSchedule> getSchedule(final String groupId) {
        String url = String.format("https://journal.bsuir.by/api/v1/studentGroup/schedule?studentGroup=%s", groupId);
        try {
            FullSchedule response = restTemplate.getForObject(url, FullSchedule.class);
            return new Response<>(response, true);
        } catch (RestClientException e) {
            e.getLocalizedMessage();
            return new Response<>(null, false);
        }
    }

}
