package com.example.demo.resource;

import com.example.demo.model.StudentDataModel;
import com.example.demo.repository.StudentDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentResource {
    @Autowired
    private StudentDataRepo studentDataRepo;

    @GetMapping("/get/{id}")
    public StudentDataModel getStudent(@PathVariable int id) throws Exception {
        return studentDataRepo.getStudentById(id);
    }

    @PostMapping("/create")
    public int createStudent(@RequestParam String name, @RequestParam String age,
                             @RequestParam String adhar, @RequestParam String university) {
        return studentDataRepo.addStudent(name, age, adhar, university);
    }

    @GetMapping("/getByUniversity/{university}")
    public List<StudentDataModel> getStudentsByUniversity(@PathVariable String university) {
        return studentDataRepo.getAllStudentByUniversity(university);
    }
}
