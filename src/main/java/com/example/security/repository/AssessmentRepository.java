package com.example.security.repository;


import com.example.security.entity.Assessment;
import com.example.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    @Query("SELECT a.id FROM Assessment a WHERE a.user = :user")
    List<Long> findAllIdsByUser(@Param("user") Users user);


}
