package com.hsbc.employeemanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hsbc.employeemanagement.model.Employee;
import com.hsbc.employeemanagement.service.*;

import lombok.RequiredArgsConstructor;

import static com.hsbc.employeemanagement.controller.EmployeeController.EMPLOYEES;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Employee Controller
 * <br>
 * <p/>
 * Creation date: 16.11.2019<br>
 *
 * @author lucu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/" + EMPLOYEES, produces = APPLICATION_JSON_VALUE)
public class EmployeeController {

  public static final String EMPLOYEES = "employees";
  private final InterfaceDeleteEmployee interfaceDeleteEmployee;
  private final InterfaceGetEmployeeById interfaceGetEmployeeById;
  private final InterfaceInsertEmployee interfaceInsertEmployee;
  private final InterfaceModifyEmployee interfaceModifyEmployee;
  private final InterfaceGetEmployeesBySearchPhrase interfaceGetEmployeesBySearchPhrase;

  @RequestMapping(path = "/find/{id}", method = GET)
  public ResponseEntity<Employee> find(@PathVariable (name ="id") Integer id) {
    return ResponseEntity.ok(interfaceGetEmployeeById.getEmployeeById(id));
  }

  @RequestMapping(path = "/delete/{id}", method = DELETE)
  public ResponseEntity delete(@PathVariable(value = "id") Integer id) {
    interfaceDeleteEmployee.deleteEmployee(id);
    return new ResponseEntity(OK);
  }

  @RequestMapping(path = "/insert", method = POST)
  public ResponseEntity insert(@RequestBody Employee employee) {
    interfaceInsertEmployee.insertEmployee(employee);
    return new ResponseEntity(OK);
  }

  @RequestMapping(path = "/modify", method = PUT)
  public ResponseEntity modify(@RequestBody Employee employee) {
    interfaceModifyEmployee.modifyEmployee(employee);
    return new ResponseEntity(OK);
  }

  @RequestMapping(path = "/findBySearchPhrases", method = GET)
  public ResponseEntity<List<Employee>> findBySearchPhrases(@RequestParam(value = "searchPhrases") List<String> searchPhrases) {
    return ResponseEntity.ok(interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(searchPhrases));
  }

}