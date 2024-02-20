package com.example.demo.repository;

import com.example.demo.entity.AuditPool;
import com.example.demo.entity.GroupOfColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupOfColumnRepository extends JpaRepository<GroupOfColumn, Long> {
    @Query(value = "SELECT * FROM group_of_column WHERE cur_id = :id", nativeQuery = true)
    GroupOfColumn findByCurId(long id);
}
