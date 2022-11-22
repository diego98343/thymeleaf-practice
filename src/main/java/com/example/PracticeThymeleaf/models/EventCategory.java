package com.example.PracticeThymeleaf.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {


    private String name;

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> event= new ArrayList<>();

    public EventCategory(int id, String name) {

        this.name = name;
    }

    public EventCategory() {
    }

    public List<Event> getEvent() {
        return event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
