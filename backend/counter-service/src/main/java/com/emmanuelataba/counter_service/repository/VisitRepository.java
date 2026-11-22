package com.emmanuelataba.counter_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emmanuelataba.counter_service.entity.VisitCounter; 

public interface VisitRepository extends JpaRepository <VisitCounter, String> {

    // Atomic "insert or increment": creates the row with 1 if missing, otherwise adds 1.
    //Native SQL bacause ON DUPLICATED KEY UPDATE is MYSQL-specific (JPQL can't express it).
    @Modifying(clearAutomatically = true)
    @Query (value = """
            INSERT INTO visit_counter (counter_id, site_visits) 
            VALUES (:counterId, 1)
            ON DUPLICATE KEY UPDATE site_visits = site_visits + 1
        """, nativeQuery = true)
        int upsertIncrement(@Param("counterId") String counterId);
}