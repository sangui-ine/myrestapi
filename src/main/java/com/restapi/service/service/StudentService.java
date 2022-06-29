package com.restapi.service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.service.beans.StudentWrapper;
import com.restapi.service.exception.RecordNotFoundException;
import com.restapi.service.model.Student;
import com.restapi.service.repository.StudentRepository;


@Service
public class StudentService {
	@Autowired
	private StudentRepository repository;
	public StudentWrapper saveStudent(StudentWrapper inputStudent) {
		Student student=new Student();
//		student.setId(inputStudent.getId());
		student.setName(inputStudent.getName());
		
		student=repository.save(student);
		
		inputStudent.setId(student.getId());
		inputStudent.setName(student.getName());
		return inputStudent;
	}
	public StudentWrapper getStudentById(Long id) throws RecordNotFoundException {
		StudentWrapper studentWrapperOutput =null;
		Optional<Student>studentOptionalData=repository.findById(id);
		if(studentOptionalData.isPresent()) {
			studentWrapperOutput=new StudentWrapper();
			Student student=studentOptionalData.get();
			studentWrapperOutput.setId(student.getId());
            studentWrapperOutput.setName(student.getName());
		}else {
			throw new RecordNotFoundException("student record not found");
			
		}
		return studentWrapperOutput;
		
	}
	public StudentWrapper updateStudentDetails(StudentWrapper inputStudent) throws RecordNotFoundException {
		Optional<Student>studentOptionalData=repository.findById(inputStudent.getId());
		if(studentOptionalData.isPresent()) {
			Student student=studentOptionalData.get();
//			update the name
			student.setName(inputStudent.getName());
			repository.save(student);
			//populated the update details in the object output
			
		
			StudentWrapper studentWrapper=new StudentWrapper();
			studentWrapper.setId(student.getId());
            studentWrapper.setName(student.getName());
            return studentWrapper;
		}else {
			throw new RecordNotFoundException("student record not found");
			
		}
		
	}	
	public void deleteById(Long id)throws RecordNotFoundException{
		Optional<Student>studentOptionalData=repository.findById(id);
		if(studentOptionalData.isPresent()) {
			Student student=studentOptionalData.get();
			repository.delete(student);
			}else {
				throw new RecordNotFoundException("student record not found");
			}
	}


}
