package com.example.university.service;


import com.example.university.model.Student;
import com.example.university.model.User;
import com.example.university.repository.StudentRepository;
import com.example.university.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public String saveStudent(Student student, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            return "User not found";
        }
        Optional<Student> existing = studentRepository.findByStudentNumber(student.getStudentNumber());
        if (existing.isPresent()) {
            return "Student with number " + student.getStudentNumber() + " already exists";
        }
        student.setUser(user.get());
        studentRepository.save(student);
        return "Student saved successfully";
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student getStudentByNumber(String studentNumber) {
        Optional<Student> student = studentRepository.findByStudentNumber(studentNumber);
        return student.orElse(null);
    }

    public List<Student> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartment(department);
    }

    public String updateStudent(Long id, Student updatedStudent) {
        Optional<Student> existingOpt = studentRepository.findById(id);
        if (!existingOpt.isPresent()) {
            return "Student not found";
        }
        Student existing = existingOpt.get();
        existing.setDepartment(updatedStudent.getDepartment());
        existing.setSemester(updatedStudent.getSemester());
        studentRepository.save(existing);
        return "Student updated successfully";
    }

    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}