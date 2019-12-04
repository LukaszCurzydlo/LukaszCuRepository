/*
 * Any use, copying, modification, distribution and selling of this software
 * and its documentation for any purposes without AILLERON's written permission
 * is hereby prohibited
 *
 */
package com.hsbc.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hsbc.employeemanagement.model.Employee;
import com.hsbc.employeemanagement.repository.EmployeeRepository;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * GetEmployeesBySearchPhrase
 * <br>
 * <p/>
 * Creation date: 16.11.2019<br>
 *
 * @author lucu
 */
@Service
public class GetEmployeesBySearchPhraseService extends BaseEmployeeRepository implements InterfaceGetEmployeesBySearchPhrase {

  public GetEmployeesBySearchPhraseService(EmployeeRepository employeeRepository) {
    super(employeeRepository);
  }

  @Override
  public List<Employee> getEmployeesBySearchPhrase(List<String> searchPhraseList) {
    if (isEmpty(searchPhraseList)) {
      return emptyList();
    }
    List<String> modifiableSearchPhraseList = new ArrayList<>();
    modifiableSearchPhraseList.addAll(searchPhraseList);
    List<Integer> listOfNumbers = getListOfNumbersAndRemoveThemFromOriginalList(modifiableSearchPhraseList);
    modifiableSearchPhraseList = modifiableSearchPhraseList.stream().map(String::toUpperCase).collect(Collectors.toList());
    int listOfNumbersSize = listOfNumbers.size();
    int listOfStringsSize = modifiableSearchPhraseList.size();
    return searchAccordingtToParameterKindsAndGetResult(listOfNumbersSize, listOfStringsSize, modifiableSearchPhraseList, listOfNumbers);
  }

  private List<Employee> searchAccordingtToParameterKindsAndGetResult(int listOfNumbersSize, int listOfStringsSize,
                                                                      List<String> listOfStrings, List<Integer> listOfNumbers) {
    if (listOfNumbersSize == 0 && listOfStringsSize == 0) {
      return emptyList();
    }

    if (listOfStringsSize == 0) {
      return getResultIfOnlyIntegers(listOfNumbers, listOfNumbersSize);
    }

    if (listOfNumbersSize == 0) {
      return getResultIfOnlyStrings(listOfStrings, listOfStringsSize);
    }

    if (listOfStringsSize == 1) {
      return getResultIfOneStringAndNumbers(listOfNumbers, listOfNumbersSize, listOfStrings.get(0));
    }

    return getResultIfTwoOrMoreStringsAndNumbers(listOfNumbers, listOfNumbersSize, listOfStrings);

  }

  private List<Employee> getResultIfOnlyIntegers(List<Integer> listOfNumbers, int listOfNumbersSize) {
    if (listOfNumbersSize == 1) {
      return employeeRepository.getEmployeesByOneInteger(listOfNumbers.get(0));
    }

    if (listOfNumbersSize == 2) {
      return employeeRepository.getEmployeesByTwoIntegers(listOfNumbers);
    }
    return employeeRepository.getEmployeesByMoreThanTwoIntegers(listOfNumbers);

  }

  private List<Employee> getResultIfOneStringAndNumbers(List<Integer> listOfNumbers, int listOfNumbersSize, String searchPhraseString) {
    if (listOfNumbersSize == 1) {
      return employeeRepository.getEmployeesByOneStringAndOneInteger(searchPhraseString, listOfNumbers.get(0));
    }
    if (listOfNumbersSize == 2) {
      return employeeRepository.getEmployeesByOneStringAndTwoInteger(searchPhraseString, listOfNumbers);
    }
    return employeeRepository.getEmployeesByOneStringAndMoreThanTwoInteger(searchPhraseString, listOfNumbers);
  }

  private List<Employee> getResultIfTwoOrMoreStringsAndNumbers(List<Integer> listOfNumbers, int listOfNumbersSize,
                                                               List<String> listOfStrings) {
    if (listOfNumbersSize == 1) {
      return employeeRepository.getEmployeesByTwoStringsAndOneInteger(listOfStrings, listOfNumbers.get(0));
    }
    if (listOfNumbersSize == 2) {
      return employeeRepository.getEmployeesByTwoStringsAndtwoInteger(listOfStrings, listOfNumbers);
    }
    return employeeRepository.getEmployeesByTwoStringsAndMoreThantwoInteger(listOfStrings, listOfNumbers);
  }

  private List<Employee> getResultIfOnlyStrings(List<String> stringList, int listOfStringsSize) {
    if (listOfStringsSize == 1) {
      return employeeRepository.getEmployeesByOneString(stringList.get(0));
    }
    return employeeRepository.getEmployeesByTwoStringsOrMore(stringList);
  }

  private List<Integer> getListOfNumbersAndRemoveThemFromOriginalList(List<String> searchPhraseList) {
    List<String> listOfNumber = new ArrayList<>();
    searchPhraseList.forEach(potentialNumber -> {
      if (checkIfPhraseIsNumber(potentialNumber)) {
        listOfNumber.add(potentialNumber);
      }
    });
    searchPhraseList.removeAll(listOfNumber);
    return listOfNumber.stream().map(Integer::valueOf).collect(Collectors.toList());
  }

  private static boolean checkIfPhraseIsNumber(String searchPhrase) {
    return searchPhrase.matches("\\d+");

  }

}



