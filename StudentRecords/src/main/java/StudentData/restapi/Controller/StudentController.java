package StudentData.restapi.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import StudentData.restapi.entity.Student;
import StudentData.restapi.repository.StudentRepository;

@RestController
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
	List<Student> studentListdata = new ArrayList<>();
@GetMapping("/students")
public List<Student> getAllStudents(){
	List<Student>studentList = studentRepository.findAll();
	return studentList;
}

@GetMapping("/students/{id}")
public Student  getStudent(@PathVariable int id) {
Student student = 	studentRepository.findById(id).get();
	return student;
	
}
@PostMapping("/student/add")
@ResponseStatus(code = HttpStatus.CREATED)
public void CreateStudent(@RequestBody Student student) {
	studentRepository.save(student);
	
}

@PutMapping("/student/update/{id}")
public ResponseEntity<Object> updateStudents(@RequestBody Student student, @PathVariable int id) {

Optional<Student>studentOptional = studentRepository.findById(id);
if(studentOptional.isEmpty())	
return ResponseEntity.notFound().build();
student.setRollNo(id);
studentRepository.save(student);

	return ResponseEntity.noContent().build();


 
}

@DeleteMapping("/student/delete/{id}")
public  void removeStudent(@PathVariable int id) {
Student student = 	studentRepository.findById(id).get();
	studentRepository.delete(student);
}

}



