package com.app.controller.rest;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/rest/student")
public class StudentRestController {

	@Autowired
	private IStudentService service;

	//1.Insert
	@PostMapping("")
	public ResponseEntity<String> saveStudent(@RequestBody Student student){

		ResponseEntity<String> resp =null;
		try {
			Integer id =service.saveStudent(student);
			resp=new ResponseEntity<String>("Student '"+id+"' created", HttpStatus.OK);
		} catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	//2.Fetch All records
	@GetMapping("/all")
	public  ResponseEntity<?> getAllStudents(){

		ResponseEntity<?> resp =null;

		try {
			List<Student> list =service.getAllStudents();
			if(list !=null && !list.isEmpty()) {
				resp =new ResponseEntity<List<Student>>(list, HttpStatus.OK);
			}else {
				resp =new ResponseEntity<String>("NO DATA FOUND",HttpStatus.OK );
			}

		} catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resp;
	}


	//3.Get the Student By id

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneStudent(@PathVariable("id")Integer sid){
		ResponseEntity<?> resp =null;

		try {
			Student s =service.getOneStudent(sid);
			if(s !=null) {
				resp =new ResponseEntity<Student>(s, HttpStatus.OK);
			}else {
				resp =new ResponseEntity<String>("NO DATA FOUND",HttpStatus.NO_CONTENT );
			}

		} catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resp;
	}

	//4.Delete the student based on id...

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneStudent(@PathVariable("id")Integer sid) 
	{
		ResponseEntity<String> resp=null;
		try {
			service.deleteStudent(sid);
			resp=new ResponseEntity<String>("Deleted '"+sid+"' Successfully", HttpStatus.OK);
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to Delete/Id Not Found", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resp;
	}

	//5. Update the student record based on id.
	@PutMapping("/update")
	public ResponseEntity<?> updateStudent() {
		return new ResponseEntity<String>("Under development", HttpStatus.OK);
	}
}

