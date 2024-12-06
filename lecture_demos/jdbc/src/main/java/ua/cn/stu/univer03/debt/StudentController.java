package ua.cn.stu.univer03.debt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
// import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{groupName}")
    ResponseEntity<List<Student>> getStudentsByGroup(@PathVariable String groupName) {
        return ResponseEntity.ok(studentRepository.findByGroupName(groupName));
    }

    // @GetMapping("/{Id}")
    // ResponseEntity<List<Student>> getStudentsByGroup(@PathVariable UUID Id) {
    // return ResponseEntity.ok(studentRepository.findAllById());
    // }

}
