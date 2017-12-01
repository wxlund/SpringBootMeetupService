package com.datastax.springboot.s1p.repositories;
import java.util.List;

import com.datastax.springboot.s1p.model.Event;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, String> {

    @Query("SELECT mid,event_url,lat,lon FROM meetup WHERE  solr_query = '{\"q\": \"*:*\", \"fq\": \"{!bbox sfield=geo pt=?0,?1 d=550}\"}'")
    public List<Event> findByBbox(double lat, double lon);
    @Query("SELECT mid,event_url,lat,lon FROM meetup")
    public List<Event> findAll(double lat, double lon);

}