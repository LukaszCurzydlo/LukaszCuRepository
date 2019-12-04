/*
 * Any use, copying, modification, distribution and selling of this software
 * and its documentation for any purposes without AILLERON's written permission
 * is hereby prohibited
 *
 */
package com.hsbc.employeemanagement.service;

import com.hsbc.employeemanagement.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

/**
 * BaseEmployeeRepository
 * <br>
 * <p/>
 * Creation date: 16.11.2019<br>
 *
 * @author lucu
 */
@AllArgsConstructor
public  class BaseEmployeeRepository {

  protected final EmployeeRepository employeeRepository;

}
