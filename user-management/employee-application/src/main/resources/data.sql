DROP TABLE IF EXISTS EMPLOYEE;

CREATE TABLE EMPLOYEE (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NAME VARCHAR(250) ,
  SURNAME VARCHAR(250) ,
  GRADE INT,
  SALARY INT
);
