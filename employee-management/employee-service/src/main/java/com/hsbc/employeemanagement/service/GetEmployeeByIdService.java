/*
 * Any use, copying, modification, distribution and selling of this software
 * and its documentation for any purposes without AILLERON's written permission
 * is hereby prohibited
 *
 */
package com.hsbc.employeemanagement.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hsbc.employeemanagement.exception.EmployeeNotFoundException;
import com.hsbc.employeemanagement.model.Employee;
import com.hsbc.employeemanagement.repository.EmployeeRepository;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * GetUsersByIdsService
 * <br>
 * <p/>
 * Creation date: 16.11.2019<br>
 *
 * @author lucu
 */
@Service
public class GetEmployeeByIdService extends BaseEmployeeRepository implements InterfaceGetEmployeeById {

  public GetEmployeeByIdService(EmployeeRepository employeeRepository) {
    super(employeeRepository);
  }

  public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
   Optional<Employee> employee= employeeRepository.findById(id);
    if (!employee.isPresent()) {
      throw new EmployeeNotFoundException(id);
    }
   return employee.get();
  }

}
