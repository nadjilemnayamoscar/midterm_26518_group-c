package com.example.university.service;


import com.example.university.repository.EnrollmentRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.model.Course;
import com.example.university.model.Enrollment;
import com.example.university.model.Student;
import com.example.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public String enrollStudent(Long studentId, Long courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()) {
            return "Student not found";
        }
        Optional<Course> course = courseRepository.findById(courseId);
        if (!course.isPresent()) {
            return "Course not found";
        }
        boolean exists = enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
        if (exists) {
            return "Student already enrolled in this course";
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student.get());
        enrollment.setCourse(course.get());
        enrollmentRepository.save(enrollment);
        return "Student enrolled successfully";
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        return enrollment.orElse(null);
    }

    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public String updateEnrollmentStatus(Long id, String status) {
        Optional<Enrollment> existingOpt = enrollmentRepository.findById(id);
        if (!existingOpt.isPresent()) {
            return "Enrollment not found";
        }
        Enrollment existing = existingOpt.get();
        existing.setStatus(status);
        enrollmentRepository.save(existing);
        return "Enrollment status updated successfully";
    }

    public String updateGrade(Long id, String grade) {
        Optional<Enrollment> existingOpt = enrollmentRepository.findById(id);
        if (!existingOpt.isPresent()) {
            return "Enrollment not found";
        }
        Enrollment existing = existingOpt.get();
        existing.setGrade(grade);
        enrollmentRepository.save(existing);
        return "Grade updated successfully";
    }

    public boolean deleteEnrollment(Long id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}