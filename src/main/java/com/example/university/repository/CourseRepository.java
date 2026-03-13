package com.example.university.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.university.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    Page<Course> findAll(Pageable pageable);
    
    List<Course> findAllByOrderByCourseNameAsc();
    
    List<Course> findByDepartment(String department);
}