package com.example.PracticeThymeleaf.models;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    @Size(min=3,max=50, message = "name has to be between 3 and 50 character")
    @NotBlank
    private String name;


    @OneToOne(cascade= CascadeType.ALL)
    @NotNull
    private EventDetails eventDetails;

    @ManyToOne
    @NotNull(message = "category is required")
    private EventCategory eventCategory;

    @ManyToMany(mappedBy = "tagName")
    private final List<EventTag> tags= new ArrayList<>();

    public Event(String name, EventDetails eventDetails, EventCategory eventCategory) {
        this.name = name;
        this.eventDetails = eventDetails;
        this.eventCategory = eventCategory;
    }

    public Event() {
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public List<EventTag> getTags() {
        return tags;
    }

    public void addTag( EventTag eventTag){
        this.tags.add(eventTag);
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
