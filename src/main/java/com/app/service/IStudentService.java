package com.app.service;

import java.util.List;

import com.app.model.Student;

public interface IStudentService {

	public Integer saveStudent(Student s);
	public List<Student> getAllStudents();
	public Student getOneStudent(Integer id);
	public void deleteStudent(Integer id);
	
	
	
	
	
	
	
	
	
}









