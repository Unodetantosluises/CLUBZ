package com.rungroup.web.controller;

import com.rungroup.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }
}
