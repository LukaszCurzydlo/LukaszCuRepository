/*
 * Any use, copying, modification, distribution and selling of this software
 * and its documentation for any purposes without AILLERON's written permission
 * is hereby prohibited
 *
 */
package com.hsbc.employeemanagement.service;

import org.springframework.stereotype.Service;

import com.hsbc.employeemanagement.model.Employee;
import com.hsbc.employeemanagement.repository.EmployeeRepository;

/**
 * InsertEmployeeService
 * <br>
 * <p/>
 * Creation date: 16.11.2019<br>
 *
 * @author lucu
 */
@Service
public class InsertEmployeeService extends BaseEmployeeRepository implements InterfaceInsertEmployee {

  public InsertEmployeeService(EmployeeRepository employeeRepository) {
    super(employeeRepository);
  }

  @Override
  public void insertEmployee(Employee employee) {
    employee.setId(null);
    employeeRepository.save(employee);
  }

}
