package com.rungroup.web.service;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Event;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
}
