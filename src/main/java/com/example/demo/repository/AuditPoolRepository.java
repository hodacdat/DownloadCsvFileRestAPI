package com.example.demo.repository;

import com.example.demo.entity.AuditPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditPoolRepository extends JpaRepository<AuditPool, Long> {

    @Query(value = "SELECT * FROM audit_pool WHERE id = :id", nativeQuery = true)
    AuditPool findByAuditPoolId(long id);

    @Query(value = "SELECT * FROM audit_pool WHERE bio_id = :bioid", nativeQuery = true)
    List<AuditPool> findByAuditPoolBioId(String bioid);
}
