package com.hsbc.employeemanagement.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(Integer id) {
    super("Employees with following Id not found : " + id);
  }
}
