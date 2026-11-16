package com.emmanuelataba.counter_service.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.emmanuelataba.counter_service.service.VisitService;


@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    public Map<String, Long> getVisits() {
        return Map.of("siteVisits", visitService.getVisits());
    }

    @PostMapping
    public Map<String, Long> incrementVisits() {
        return Map.of("siteVisits", visitService.recordVisit());
    }
}
