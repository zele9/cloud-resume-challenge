package com.emmanuelataba.counter_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emmanuelataba.counter_service.entity.VisitCounter;
import com.emmanuelataba.counter_service.repository.VisitRepository;


@Service 
public class VisitService {
    private static final String COUNTER_ID = "visitCount";
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Transactional(readOnly = true)
    public long getVisits() {
        // TODO: return the current count, or 0 if the row doesn't exist
        return visitRepository.findById(COUNTER_ID)
                                .map(VisitCounter::getSiteVisits)
                                .orElse(0L);
    }

    @Transactional 
    public long recordVisit() {
        visitRepository.upsertIncrement(COUNTER_ID);    //insert or increment atomically
        return visitRepository.findById(COUNTER_ID)     // then read the real count
                                .map(VisitCounter::getSiteVisits)
                                .orElseThrow();
    }
}