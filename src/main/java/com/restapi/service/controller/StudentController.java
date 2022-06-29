package com.restapi.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.restapi.service.beans.StudentWrapper;
import com.restapi.service.exception.RecordNotFoundException;
import com.restapi.service.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService  studentService;
	
	@PostMapping("/saveStudent")
	public ResponseEntity<StudentWrapper>saveStudent(@RequestBody StudentWrapper studentWrapper){
		studentWrapper=studentService.saveStudent(studentWrapper);
		return ResponseEntity.ok(studentWrapper);
	}
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<StudentWrapper>getStudentById(@PathVariable Long id)throws RecordNotFoundException{
		StudentWrapper studentWrapper=studentService.getStudentById(id);
		return ResponseEntity.ok(studentWrapper);
	}
	@PutMapping("/updateStudent")
	public ResponseEntity<StudentWrapper>updateStudent(@RequestBody StudentWrapper studentWrapper)throws RecordNotFoundException{
		studentWrapper=studentService.updateStudentDetails(studentWrapper);
		return ResponseEntity.ok(studentWrapper);
	}
@DeleteMapping("/deleteStudent/{id}")
public ResponseEntity<StudentWrapper>deleteStudentById(@PathVariable Long id)throws RecordNotFoundException{
  studentService.deleteById(id);
	return ResponseEntity.noContent().build();
}
}
