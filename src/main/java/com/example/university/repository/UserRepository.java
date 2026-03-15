package com.example.university.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.university.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
   
    boolean existsByEmail(String email);
    
    Optional<User> findByEmail(String email);
    
        @Query("SELECT u FROM User u WHERE u.village.parent.parent.parent.parent.code = :provinceCode")
    List<User> findByProvinceCode(@Param("provinceCode") String provinceCode);
    
   
    @Query("SELECT u FROM User u WHERE u.village.parent.parent.parent.parent.name = :provinceName")
    List<User> findByProvinceName(@Param("provinceName") String provinceName);
    
    
    Page<User> findAll(Pageable pageable);
    
    List<User> findAllByOrderByLastNameAsc();
}