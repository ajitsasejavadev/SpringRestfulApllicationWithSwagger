package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Student;
import com.app.repo.StudentRepository;
import com.app.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {


	@Autowired
	private StudentRepository repo;

	@Transactional
	public Integer saveStudent(Student s) {

		s =repo.save(s);
		return s.getStdId();
	}

	@Transactional(readOnly =true)
	public List<Student> getAllStudents() {
		List<Student> list =repo.findAll();

		/** Sorting By using Stream ..**/
		if(list !=null && !list.isEmpty()){
			list.stream()
			.sorted((s1,s2)->s1.getStdName().compareTo(s2.getStdName()));	
		}
		return list;
	}

	@Transactional(readOnly =true)
	public Student getOneStudent(Integer id) {

		Optional<Student> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public void deleteStudent(Integer id) {
		repo.deleteById(id);

	}

}
