package com.rungroup.web.service;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Event;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();
}
