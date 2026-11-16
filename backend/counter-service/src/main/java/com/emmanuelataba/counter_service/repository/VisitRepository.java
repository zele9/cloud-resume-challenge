package com.emmanuelataba.counter_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emmanuelataba.counter_service.entity.VisitCounter; 

public interface VisitRepository extends JpaRepository <VisitCounter, String> {

    // Atomic increment: one UPDATE statement, so the database applies concurrent visits one at a time
    @Modifying(clearAutomatically = true)
    @Query("UPDATE VisitCounter v SET v.siteVisits = v.siteVisits + 1 WHERE v.counterId = :counterId")
    int incrementVisits(@Param("counterId") String counterId);
}