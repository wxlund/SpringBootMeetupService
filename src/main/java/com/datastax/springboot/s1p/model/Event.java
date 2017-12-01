package com.datastax.springboot.s1p.model;


import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table ("meetup")
public class Event {

    @PrimaryKey
    private String mid;

    private String lat;
    private String lon;
    private String event_url;

    public Event(String mid, String lat, String lon, String event_url) {
        this.mid = mid;
        this.lat = lat;
        this.lon = lon;
        this.event_url = event_url;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getEvent_url() {
        return event_url;
    }

    public void setEvent_url(String event_url) {
        this.event_url = event_url;
    }

    public String getId() {
        return mid;
    }

    public void setId(String mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return String.format("Event[mid='%s', lat=%f, lon=%f, event_url='%s']", this.mid,
                this.lat, this.lon, this.event_url);
    }
}
