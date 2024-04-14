package com.example.security.repository;

import com.example.security.entity.TestResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultsRepository extends JpaRepository<TestResults, Long> {
}
