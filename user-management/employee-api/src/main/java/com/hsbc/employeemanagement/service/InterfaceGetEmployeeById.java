/*
 * Any use, copying, modification, distribution and selling of this software
 * and its documentation for any purposes without AILLERON's written permission
 * is hereby prohibited
 *
 */
package com.hsbc.employeemanagement.service;

import java.util.List;

import com.hsbc.employeemanagement.exception.EmployeeNotFoundException;
import com.hsbc.employeemanagement.model.Employee;

/**
 * InterfaceGetEmployeeById
 * <br>
 * <p/>
 * Creation date: 16.11.2019<br>
 *
 * @author lucu
 */
public interface InterfaceGetEmployeeById {
  public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException;
}
