package com.example.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.university.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    boolean existsByStudentNumber(String studentNumber);
    
    Optional<Student> findByStudentNumber(String studentNumber);
    
    List<Student> findByDepartment(String department);
}