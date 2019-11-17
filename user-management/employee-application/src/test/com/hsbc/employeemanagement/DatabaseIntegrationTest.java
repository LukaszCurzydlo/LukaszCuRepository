package com.hsbc.employeemanagement;

import java.util.List;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.hsbc.employeemanagement.EmployeeManagementApplication;
import com.hsbc.employeemanagement.exception.EmployeeNotFoundException;
import com.hsbc.employeemanagement.model.Employee;
import com.hsbc.employeemanagement.repository.EmployeeRepository;
import com.hsbc.employeemanagement.service.InterfaceGetEmployeeById;
import com.hsbc.employeemanagement.service.InterfaceGetEmployeesBySearchPhrase;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeManagementApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DatabaseIntegrationTest {

  @Autowired
  private InterfaceGetEmployeeById interfaceGetEmployeeById;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private InterfaceGetEmployeesBySearchPhrase interfaceGetEmployeesBySearchPhrase;

  @Test
  public void getByIdSuccessTest() {
    employeeRepository.save(new Employee());
    employeeRepository.save(new Employee());
    employeeRepository.save(new Employee());
    Employee employee = interfaceGetEmployeeById.getEmployeeById(2);
    assertEquals(employee.getId(), 2);
  }

  @Test(expected = EmployeeNotFoundException.class)
  public void getByIdFailureTest() {
    employeeRepository.save(new Employee());
    Employee employee = interfaceGetEmployeeById.getEmployeeById(2);
    assertEquals(employee.getId(), 2);
  }

  @Test
  public void getBySearchPhraseOneString() {
    Employee employee = new Employee();
    employee.setName("LUKASZ");
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setName("Maciek");
    employeeRepository.save(employee2);

    List<String> list = Arrays.asList("lukasz");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getName(), "LUKASZ");
  }

  @Test
  public void getBySearchPhraseTwoStrings() {
    Employee employee = new Employee();
    employee.setName("LUKASZ");
    employee.setSurname("KOWALSKI");

    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setName("Maciek");
    employee2.setName("KOWALSKI");

    employeeRepository.save(employee2);

    List<String> list = Arrays.asList("lukasz","KOWALSKI");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getName(), "LUKASZ");
    assertEquals(employees.get(0).getSurname(), "KOWALSKI");
    assertEquals(employees.size(),1);
  }

  @Test
  public void getEmployeesBySearchPhraseOneInteger() {
    Employee employee = new Employee();
    employee.setSalary(2000);
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setSalary(3000);
    employeeRepository.save(employee2);

    List<String> list = Arrays.asList("2000");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getSalary(), 2000);
    assertEquals(employees.size(),1);

  }

  @Test
  public void getEmployeesBySearchPhraseTwoInteger() {
    Employee employee = new Employee();
    employee.setSalary(2000);
    employee.setGrade(5);
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setGrade(10);
    employee2.setSalary(3000);
    employeeRepository.save(employee2);

    List<String> list = Arrays.asList("5","2000");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getSalary(), 2000);
    assertEquals(employees.size(),1);

  }

  @Test
  public void getEmployeesBySearchPhraseTwoOrMoreInteger() {
    Employee employee = new Employee();
    employee.setSalary(2000);
    employee.setGrade(5);
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setGrade(10);
    employee2.setSalary(3000);
    employeeRepository.save(employee2);

    List<String> list = Arrays.asList("5","2000","1");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getSalary(), 2000);
    assertEquals(employees.size(),1);

  }


  @Test
  public void getEmployeesBySearchPhraseOneStringAndOneNumber() {
    Employee employee = new Employee();
    employee.setSalary(2000);
    employee.setName("lukasz");
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setSalary(2000);
    employee2.setName("tomek");
    employeeRepository.save(employee2);

    List<String> list = Arrays.asList("tomek","2000");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getName(), "tomek");
    assertEquals(employees.size(),1);
  }

  @Test
  public void getEmployeesBySearchPhraseOneStringAndTwoNumber() {
    Employee employee = new Employee();
    employee.setSalary(3000);
    employee.setGrade(10);
    employee.setName("lukasz");
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setSalary(3000);
    employee2.setName("tomek");
    employee2.setGrade(10);
    employeeRepository.save(employee2);

    List<String> list = Arrays.asList("tomek","3000","10");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getName(), "tomek");
    assertEquals(employees.size(),1);

  }

  @Test
  public void getEmployeesBySearchPhraseOneStringAndMoreThanTwoNumber() {
    Employee employee = new Employee();
    employee.setSalary(3000);
    employee.setGrade(10);
    employee.setName("lukasz");
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setSalary(3000);
    employee2.setName("tomek");
    employee2.setGrade(10);
    employeeRepository.save(employee2);

    List<String> list = Arrays.asList("tomek","3000","10","2");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getName(), "tomek");
    assertEquals(employees.size(),1);

  }

  @Test
  public void getEmployeesBySearchPhraseTwoStringAndOneNumber() {
    Employee employee = new Employee();
    employee.setSalary(2000);
    employee.setName("lukasz");
    employee.setSurname("nowak");
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setSalary(2000);
    employee2.setName("tomek");
    employee2.setSurname("kowalski");
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setSalary(2000);
    employee3.setName("tomek");
    employee3.setSurname("nowak");
    employeeRepository.save(employee3);

    List<String> list = Arrays.asList("tomek","2000","kowalski");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getName(), "tomek");
    assertEquals(employees.size(),1);
  }

  @Test
  public void getEmployeesBySearchPhraseTwoStringAndTwoNumber() {
    Employee employee = new Employee();
    employee.setSalary(2000);
    employee.setGrade(10);
    employee.setName("lukasz");
    employee.setSurname("nowak");
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setSalary(2000);
    employee2.setName("tomek");
    employee2.setGrade(10);
    employee.setSurname("kowalski");
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setSalary(2000);
    employee3.setName("tomek");
    employee3.setSurname("nowak");
    employee3.setGrade(10);
    employeeRepository.save(employee3);

    List<String> list = Arrays.asList("tomek","2000","10","nowak");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getName(), "tomek");
    assertEquals(employees.size(),1);

  }

  @Test
  public void getEmployeesBySearchPhraseMoreThanTwoStringAndTwoNumber() {
    Employee employee = new Employee();
    employee.setSalary(2000);
    employee.setGrade(10);
    employee.setName("lukasz");
    employee.setSurname("nowak");
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setSalary(2000);
    employee2.setName("tomek");
    employee2.setGrade(10);
    employee.setSurname("kowalski");
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setSalary(2000);
    employee3.setName("tomek");
    employee3.setSurname("nowak");
    employee3.setGrade(10);
    employeeRepository.save(employee3);

    List<String> list = Arrays.asList("tomek","2000","10","nowak","lukasz");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertNotEquals(employees.get(0).getSurname(), "kowalski");
    assertNotEquals(employees.get(1).getSurname(), "kowalski");
    assertEquals(employees.size(),2);

  }


  @Test
  public void getEmployeesBySearchPhraseTwoStringAndMoreThanTwoNumber() {
    Employee employee = new Employee();
    employee.setSalary(2000);
    employee.setGrade(10);
    employee.setName("lukasz");
    employee.setSurname("nowak");
    employeeRepository.save(employee);

    Employee employee2 = new Employee();
    employee2.setSalary(2000);
    employee2.setName("tomek");
    employee2.setGrade(10);
    employee.setSurname("kowalski");
    employeeRepository.save(employee2);

    Employee employee3 = new Employee();
    employee3.setSalary(2000);
    employee3.setName("tomek");
    employee3.setSurname("nowak");
    employee3.setGrade(10);
    employeeRepository.save(employee3);

    List<String> list = Arrays.asList("tomek","2000","10","nowak","3");
    List<Employee> employees = interfaceGetEmployeesBySearchPhrase.getEmployeesBySearchPhrase(list);
    assertEquals(employees.get(0).getName(), "tomek");
    assertEquals(employees.size(),1);

  }



}
