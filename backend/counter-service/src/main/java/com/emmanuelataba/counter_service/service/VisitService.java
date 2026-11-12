package com.emmanuelataba.counter_service.service;

import org.springframework.stereotype.Service;

import com.emmanuelataba.counter_service.repository.VisitRepository;

@Service
public class VisitService {

    private final VisitRepository visitRepository;          // the toolbox (no "new")

    public VisitService(VisitRepository visitRepository) {  // Spring hands it in
        this.visitRepository = visitRepository;             // store it: field = parameter
    }

    public long getVisits() {
        return visitRepository.getCount();
    }

    public long recordVisit() {
        return visitRepository.increment();
    }
}
