package com.example.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.university.model.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    
    Optional<Location> findByCode(String code);
    
    List<Location> findByLevel(String level);
    
    List<Location> findByParentId(Long parentId);
    
    @Query("SELECT l FROM Location l WHERE l.level = 'PROVINCE'")
    List<Location> findAllProvinces();
    
    @Query("SELECT l FROM Location l WHERE l.parent.id = :parentId AND l.level = :level")
    List<Location> findByParentAndLevel(@Param("parentId") Long parentId, @Param("level") String level);
}