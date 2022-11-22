package com.example.PracticeThymeleaf.Data;

import com.example.PracticeThymeleaf.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PracticeData {

    private static final Map<Integer, Event> events= new HashMap<>();


    public static void remove (int id){

        events.remove(id);
    }

    public static Collection<Event>getALL(){

        return events.values();
    }

    public static Event getByid(int id){

        return events.get(id);
    }

    public static void add(Event event){

        events.put(event.getId(),event);
    }




}
