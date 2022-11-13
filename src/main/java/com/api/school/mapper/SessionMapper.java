package com.api.school.mapper;

import com.api.school.dto.SessionDto;
import com.api.school.entity.Course;
import com.api.school.entity.Session;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {
    
    public Session toEntity(SessionDto model, Course c) throws ParseException {
        Session entity = new Session();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateTime = DateFor.parse(model.getStartDateTime());
        Date endDateTime = DateFor.parse(model.getEndDateTime());
        entity.setStart_date_time(startDateTime);
        entity.setEnd_date_time(endDateTime);
        entity.setCourse(c);
        return entity;
    }
    
}
