package com.datastax.springboot.s1p.controller;

import java.util.ArrayList;
import java.util.List;
import com.datastax.springboot.s1p.model.Event;
import com.datastax.springboot.s1p.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
public class MeetupController {

    private static final Logger LOG = LoggerFactory.getLogger(MeetupController.class);

    @Autowired
    private EventRepository repository;

    @RequestMapping("/meetup")
    public ArrayList<Event> greeting(@RequestParam(value = "lat", defaultValue = "34") double lat,
                                     @RequestParam(value = "lon", defaultValue = "-118") double lon) {
        return new ArrayList<Event>(getMeetupEvents(lat, lon));
    }

    private ArrayList<Event> getMeetupEvents(double lat, double lon) {

        List<Event> events = new ArrayList<Event>();
        try {

           // solr query search for events in FL and CA
            for (Event event : this.repository.findByBbox(lat,lon)) {
//            for (Event event : this.repository.findAll()) {
                Event e = new Event(event.getId(),
                        event.getLat(),
                        event.getLon(),
                        event.getEvent_url());
                events.add(e);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (ArrayList<Event>) events;
    }
}

