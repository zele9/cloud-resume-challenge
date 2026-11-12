package com.emmanuelataba.counter_service.repository;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository 
public class VisitRepository {
    // Temporary in-memory storage; will be replaced by MYSQL
    private final AtomicLong visits = new AtomicLong(0);

    public long getCount() {
        return visits.get();
    }

    public long increment() {
        return visits.incrementAndGet();
    }
}
