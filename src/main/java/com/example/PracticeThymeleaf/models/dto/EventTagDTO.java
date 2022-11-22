package com.example.PracticeThymeleaf.models.dto;

import com.example.PracticeThymeleaf.models.Event;
import com.example.PracticeThymeleaf.models.EventTag;

import javax.validation.constraints.NotNull;

public class EventTagDTO {
    @NotNull
    private Event event;
    @NotNull
    private EventTag eventTag;

    public EventTagDTO() {
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public EventTag getEventTag() {
        return eventTag;
    }

    public void setEventTag(EventTag eventTag) {
        this.eventTag = eventTag;
    }
}
