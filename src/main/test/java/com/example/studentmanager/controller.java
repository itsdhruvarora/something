import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }
}

@RestController
@RequestMapping("/students")
class StudentController {

    private final Map<Integer, Student> studentsMap = new HashMap<>();
    private int idCounter = 1;

    @PostMapping("/add")
    public ResponseEntity<Integer> addStudent(@RequestBody Student student) {
        int id = idCounter++;
        student.setId(id);
        studentsMap.put(id, student);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/university/{university}")
    public ResponseEntity<List<Student>> getAllStudentsByUniversity(@PathVariable String university) {
        List<Student> students = new ArrayList<>();
        for (Student student : studentsMap.values()) {
            if (student.getUniversity().equalsIgnoreCase(university)) {
                students.add(student);
            }
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentsMap.get(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

class Student {
    private int id;
    private String name;
    private int age;
    private String adhar;
    private String university;

    // Constructors, Getters, and Setters
    public Student() {}

    public Student(String name, int age, String adhar, String university) {
        this.name = name;
        this.age = age;
        this.adhar = adhar;
        this.university = university;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdhar() {
        return adhar;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
