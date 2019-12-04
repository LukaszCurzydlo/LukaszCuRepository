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

/**
 * ModifyUserService
 * <br>
 * <p/>
 * Creation date: 16.11.2019<br>
 *
 * @author lucu
 */
@Service
public class ModifyEmployeeService extends BaseEmployeeRepository implements InterfaceModifyEmployee {

  public ModifyEmployeeService(EmployeeRepository employeeRepository) {
    super(employeeRepository);
  }

  @Override
  public void modifyEmployee(Employee employee) {
    Optional<Employee> optionalUserFromDb = employeeRepository.findById(employee.getId());
    if (optionalUserFromDb.isPresent()) {
      Employee employeeFromDb = optionalUserFromDb.get();
      if (employee.getName() != null) {
        employeeFromDb.setName(employee.getName());
      }
      if (employee.getSurname() != null) {
        employeeFromDb.setSurname(employee.getSurname());
      }
      if (employee.getGrade() != null) {
        employeeFromDb.setGrade(employee.getGrade());
      }
      if (employee.getSalary() != null) {
        employeeFromDb.setSalary(employee.getSalary());
      }
      employeeRepository.save(employeeFromDb);
    }
    else{
        throw new EmployeeNotFoundException(employee.getId());

    }
  }

}

