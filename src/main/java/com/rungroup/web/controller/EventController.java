package com.rungroup.web.controller;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Event;
import com.rungroup.web.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String evenList(Model model) {
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return  "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }


    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("event") EventDto eventDto, BindingResult result, Model model, RedirectAttributes attributes){
       if(result.hasErrors()) {
           model.addAttribute("event", eventDto);
           return "events-create";
       } try {
            eventService.createEvent(clubId, eventDto);
            attributes.addFlashAttribute("success", "Your event has been successfully created!");
        } catch (Exception e){
           attributes.addFlashAttribute("error", "Something went wrong :(");
        }
       return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto event = eventService.findByEventId(eventId);
        model.addAttribute("event", event);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updatedEvent(@PathVariable("eventId") Long eventId, @Valid @ModelAttribute("event") EventDto event, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()){
            model.addAttribute("event", event);
            return "events-edit";
        } try {
            EventDto eventDto = eventService.findByEventId(eventId);
            event.setId(eventId);
            event.setClub(eventDto.getClub());
            eventService.updateEvent(event);
            attributes.addFlashAttribute("success", "Your event has been successfully updated!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Something went wrong :(");
        }
        return "redirect:/events/" + eventId + "/edit";
    }

    @GetMapping("/events/search")
    public String searchEvent(@RequestParam(value = "query") String query, Model model) {
        List<EventDto> events = eventService.searchEvents(query);
        model.addAttribute("events", events);
        return "events-list";
    }
}
