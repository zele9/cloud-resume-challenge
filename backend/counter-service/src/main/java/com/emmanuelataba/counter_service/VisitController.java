package com.emmanuelataba.counter_service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController 
@RequestMapping ("/api/visits")     // Base path for every method in this class
public class VisitController {
    
    //One controller object is shared by every request thread, so this must be thread-safe
    private final AtomicLong visits = new AtomicLong(0);

    @GetMapping     //GET  /api/visits
    public Map<String, Long> getVisits() {
        //TODO 1: return the NEW count WITHOUT changing it
        return Map.of("siteVisits", visits.get());
    }

    @PostMapping     //POST     /api/visits
    public Map<String, Long> incrementVisits() {
        //TODO 2: add 1 atomatically and return the NEW count
        return Map.of("siteVisits", visits.incrementAndGet());
    }
}
