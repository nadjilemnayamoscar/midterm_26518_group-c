package com.example.university.service;

import com.example.university.model.Course;
import com.example.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public String saveCourse(Course course) {
        Optional<Course> existing = courseRepository.findById(course.getId());
        if (existing.isPresent()) {
            return "Course already exists";
        }
        courseRepository.save(course);
        return "Course saved successfully";
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElse(null);
    }

    public Page<Course> getCoursesWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return courseRepository.findAll(pageable);
    }

    public List<Course> getCoursesByDepartment(String department) {
        return courseRepository.findByDepartment(department);
    }

    public String updateCourse(Long id, Course updatedCourse) {
        Optional<Course> existingOpt = courseRepository.findById(id);
        if (!existingOpt.isPresent()) {
            return "Course not found";
        }
        Course existing = existingOpt.get();
        existing.setCourseName(updatedCourse.getCourseName());
        existing.setCredits(updatedCourse.getCredits());
        existing.setInstructor(updatedCourse.getInstructor());
        courseRepository.save(existing);
        return "Course updated successfully";
    }

    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}