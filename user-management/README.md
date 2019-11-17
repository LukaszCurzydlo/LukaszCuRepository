
# employee-managament
REST API for employees management.

# h2 Console
http://localhost:8080/h2
JDBC URL  =>  jdbc:h2:mem:testdb

# REST API Url examples

 * Get  employee
    * GET http://localhost:8080/employees/find/{id}
    
    
  * Insert employee
    * POST http://localhost:8080/employees/insert
      * Request body     
        {
            "name": "Lukasz",
            "surname": "Nowak",
            "grade": 5,
            "salary": 1000
        }
        
  * modify employee
    * PUT http://localhost:8080/employees/modify        
        
      * Request body     
        {
            "id":1,
            "name": "Lukasz",
            "surname": "Nowak",
            "grade": 5,
            "salary": 1000
        }
        
  * Delete employee
    DELETE http://localhost:8080/employees/delete/{id}
    
  * Get employees sending changeable number of parameters
    GET  http://localhost:8080/employees/findBySearchPhrases?searchPhrases=lukasz,2000
    
# Exception handling
  * 200 - OK
  * 400 - Bad Request
  * 404 - No data found
  * 500 - Internal Server Error

# INTEGRATION TESTS

  MODULE => employee-application
  CLASS  =>  DatabaseIntegrationTest