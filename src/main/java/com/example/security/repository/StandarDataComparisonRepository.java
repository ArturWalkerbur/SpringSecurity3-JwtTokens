package com.example.security.repository;

import com.example.security.entity.StandarDataComparison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StandarDataComparisonRepository extends JpaRepository<StandarDataComparison, Long>{

    @Query("SELECT s FROM StandarDataComparison s " +
            "WHERE s.indicator = :indicator " +
            "AND s.gender = :gender " +
            "AND (s.age = (SELECT MAX(s3.age) FROM StandarDataComparison s3 WHERE s3.gender = :gender AND s3.indicator = :indicator AND s3.age <= :age))")
    StandarDataComparison findByGenderAndAge(@Param("indicator") String indicator, @Param("gender") String gender, @Param("age") int age);

}
