package com.restapi.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restapi.service.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

}
