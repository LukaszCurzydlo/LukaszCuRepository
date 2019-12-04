package com.hsbc.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hsbc.employeemanagement.model.Employee;

/**
 * Employee Repository
 * <br>
 * <p/>
 * Creation date: 16.11.2019<br>
 *
 * @author lucu
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

  static final String BASE_SELECT = "SELECT DISTINCT(e)  FROM Employee e WHERE ";
  static final String ONE_STRING = "UPPER(e.name) = :searchPhraseString or UPPER(e.surname) = :searchPhraseString ";
  static final String TWO_OR_MORE_STRINGS = "UPPER(e.name) IN (:searchPhraseListOfStrings) and UPPER(e.surname) IN (:searchPhraseListOfStrings) ";
  static final String ONE_NUMBER = "e.id = :searchPhraseInteger or e.salary = :searchPhraseInteger or e.grade = :searchPhraseInteger ";
  static final String TWO_NUMBERS = "e.id IN (:searchPhraseListOfIntegers) and e.salary IN (:searchPhraseListOfIntegers) or e.id IN (:searchPhraseListOfIntegers) and e.grade IN (:searchPhraseListOfIntegers) or e.grade IN (:searchPhraseListOfIntegers) and e.salary IN (:searchPhraseListOfIntegers)  ";
  static final String THREE_OR_MORE_NUMBERS = "e.id IN (:searchPhraseListOfIntegers) and e.salary IN (:searchPhraseListOfIntegers) and e.grade IN (:searchPhraseListOfIntegers) ";
  static final String AND = " and ";
  static final String SIGN_FIRST = "(";
  static final String SIGN_SECOND = ")";

  @Query(BASE_SELECT + ONE_STRING)
  List<Employee> getEmployeesByOneString(@Param("searchPhraseString") String searchPhraseString);

  @Query(BASE_SELECT + TWO_OR_MORE_STRINGS)
  List<Employee> getEmployeesByTwoStringsOrMore(@Param("searchPhraseListOfStrings") List<String> searchPhraseListOfStrings);

  @Query(BASE_SELECT + ONE_NUMBER)
  List<Employee> getEmployeesByOneInteger(@Param("searchPhraseInteger") Integer searchPhraseInteger);

  @Query(BASE_SELECT + TWO_NUMBERS)
  List<Employee> getEmployeesByTwoIntegers(@Param("searchPhraseListOfIntegers") List<Integer> searchPhraseListOfIntegers);

  @Query(BASE_SELECT + THREE_OR_MORE_NUMBERS)
  List<Employee> getEmployeesByMoreThanTwoIntegers(@Param("searchPhraseListOfIntegers") List<Integer> searchPhraseListOfIntegers);


  @Query(BASE_SELECT + ONE_STRING + AND + SIGN_FIRST + ONE_NUMBER + SIGN_SECOND)
  List<Employee> getEmployeesByOneStringAndOneInteger(@Param("searchPhraseString") String searchPhraseString,
                                                      @Param("searchPhraseInteger") Integer searchPhraseInteger);

  @Query(BASE_SELECT + ONE_STRING + AND + SIGN_FIRST + TWO_NUMBERS + SIGN_SECOND)
  List<Employee> getEmployeesByOneStringAndTwoInteger(@Param("searchPhraseString") String searchPhraseString,
                                                      @Param("searchPhraseListOfIntegers") List<Integer> searchPhraseListOfIntegers);

  @Query(BASE_SELECT + ONE_STRING + AND + SIGN_FIRST + THREE_OR_MORE_NUMBERS + SIGN_SECOND)
  List<Employee> getEmployeesByOneStringAndMoreThanTwoInteger(@Param("searchPhraseString") String searchPhraseString,
                                                              @Param("searchPhraseListOfIntegers")
                                                                List<Integer> searchPhraseListOfIntegers);


  @Query(BASE_SELECT + TWO_OR_MORE_STRINGS + AND + SIGN_FIRST + ONE_NUMBER + SIGN_SECOND)
  List<Employee> getEmployeesByTwoStringsAndOneInteger(@Param("searchPhraseListOfStrings") List<String> searchPhraseListOfStrings,
                                                       @Param("searchPhraseInteger") Integer searchPhraseInteger);

  @Query(BASE_SELECT + TWO_OR_MORE_STRINGS + AND + SIGN_FIRST + TWO_NUMBERS + SIGN_SECOND)
  List<Employee> getEmployeesByTwoStringsAndtwoInteger(@Param("searchPhraseListOfStrings") List<String> searchPhraseListOfStrings,
                                                       @Param("searchPhraseListOfIntegers") List<Integer> searchPhraseListOfIntegers);

  @Query(BASE_SELECT + TWO_OR_MORE_STRINGS + AND + SIGN_FIRST + THREE_OR_MORE_NUMBERS + SIGN_SECOND)
  List<Employee> getEmployeesByTwoStringsAndMoreThantwoInteger(@Param("searchPhraseListOfStrings") List<String> searchPhraseListOfStrings,
                                                               @Param("searchPhraseListOfIntegers")
                                                                 List<Integer> searchPhraseListOfIntegers);

}