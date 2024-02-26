package com.example.demo.repository;

import com.example.demo.model.StudentDataModel;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StudentDataRepo {
    private Map<Integer, StudentDataModel> studentMap = new HashMap<>();
    private int idCounter = 1;

    public int addStudent(String name, String age, String adhar, String university) {
        int id = idCounter++;
        StudentDataModel newStudent = new StudentDataModel(id, name, age, adhar, university);
        studentMap.put(id, newStudent);
        return id;
    }

    public StudentDataModel getStudentById(final int id) {
        return studentMap.getOrDefault(id, null);
    }

    public List<StudentDataModel> getAllStudentByUniversity(String university) {
        List<StudentDataModel> stuByUniList = new ArrayList<>();
        for (StudentDataModel student : studentMap.values()) {
            if (student.getUniversity().equalsIgnoreCase(university)) {
                stuByUniList.add(student);
            }
        }
        return stuByUniList;
    }
}
