package com.example.PracticeThymeleaf.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventTag {
    @Id
    @GeneratedValue
    private int tagId;
    @ManyToMany()
    private String tagName;

    private final List<Event> events=new ArrayList<>();

    public EventTag(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public EventTag() {
    }





    public List<Event> getEvents() {
        return events;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
