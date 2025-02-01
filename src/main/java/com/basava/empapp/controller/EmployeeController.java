package com.basava.empapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.basava.empapp.response.EmployeeResponse;
import com.basava.empapp.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService emplService;
	
	
	@GetMapping("/employees/{id}")
	private ResponseEntity<EmployeeResponse> getEmployeeDetailsById(@PathVariable("id") int id){
		EmployeeResponse emplresp=emplService.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(emplresp);
		
	}
	
	

}
