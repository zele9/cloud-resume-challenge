package com.emmanuelataba.counter_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity  
@Table(name = "visit_counter")
public class VisitCounter {
    @Id 
    private String counterId;
    private Long siteVisits;

    protected VisitCounter() {

    }

    public String getCounterId() {
        return counterId;
    }

    public Long getSiteVisits() {
        return siteVisits;
    }
}
