package com.basava.empapp.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.basava.empapp.entity.Employee;
import com.basava.empapp.feignClient.AddressClient;
import com.basava.empapp.repository.EmployeeRepo;
import com.basava.empapp.response.AddressResponse;
import com.basava.empapp.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo emplRepo;
	
	@Autowired
	private ModelMapper mapper;
	
    // Spring will create the implementation
    // for this class
    // and will inject the bean here (proxy)
    @Autowired
    private AddressClient addressClient;
	
	public EmployeeResponse getEmployeeById(int id) {
		Optional<Employee> employee=emplRepo.findById(id);
		EmployeeResponse employeeResponse=mapper.map(employee,EmployeeResponse.class);
		
		 // Using FeignClient
        ResponseEntity<AddressResponse> addressResponse = addressClient.getAddressByEmployeeId(id);
        employeeResponse.setAddressResponse(addressResponse.getBody());

		
		return employeeResponse;
	}

}
