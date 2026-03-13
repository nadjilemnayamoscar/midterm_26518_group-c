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
    
    // Point 7: existBy() method
    boolean existsByEmail(String email);
    
    Optional<User> findByEmail(String email);
    
    // Point 8: Get users by province code
    @Query("SELECT u FROM User u WHERE u.village.parent.parent.parent.parent.code = :provinceCode")
    List<User> findByProvinceCode(@Param("provinceCode") String provinceCode);
    
    // Point 8: Get users by province name
    @Query("SELECT u FROM User u WHERE u.village.parent.parent.parent.parent.name = :provinceName")
    List<User> findByProvinceName(@Param("provinceName") String provinceName);
    
    // Point 3: Pagination
    Page<User> findAll(Pageable pageable);
    
    List<User> findAllByOrderByLastNameAsc();
}