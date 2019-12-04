/*
 * Any use, copying, modification, distribution and selling of this software
 * and its documentation for any purposes without AILLERON's written permission
 * is hereby prohibited
 *
 */
package com.hsbc.employeemanagement.service;

import org.springframework.stereotype.Service;

import com.hsbc.employeemanagement.repository.EmployeeRepository;

/**
 * DeleteEmployeeService
 * <br>
 * <p/>
 * Creation date: 16.11.2019<br>
 *
 * @author lucu
 */
@Service
public class DeleteEmployeeService extends BaseEmployeeRepository implements InterfaceDeleteEmployee {

  public DeleteEmployeeService(EmployeeRepository employeeRepository) {
    super(employeeRepository);
  }

  @Override
  public void deleteEmployee(Integer id) {
    employeeRepository.deleteById(id);
  }
}

