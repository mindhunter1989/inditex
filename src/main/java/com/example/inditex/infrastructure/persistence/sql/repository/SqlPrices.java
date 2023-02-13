package com.example.inditex.infrastructure.persistence.sql.repository;

import com.example.inditex.infrastructure.persistence.sql.model.SqlPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface SqlPrices extends JpaRepository<SqlPrice, Long> {

    @Query("SELECT p FROM SqlPrice p WHERE " +
            "p.productId = :productId AND " +
            "p.brandId = :brandId AND " +
            ":applicationDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    Page<SqlPrice> findByApplicationDateAndProductIdAndBrandId(
            Pageable pageable,
            @Param("applicationDate") Instant applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId);

}
