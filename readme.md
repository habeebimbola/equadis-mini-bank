# This Document Describes The Mini Bank Task Solution For The Equadis Senior Java Developer Task
Assumptions
-----------
Creating new customer assumes customer creation process has existing customer Identifier CustID from client
creating new bank account associated with a customer assumes account number from client(Via Service)
being supplied during account creation
logging even eor JPA objects could be implemented to track changes to Object Persistent state

## Building The Project:
* The Application Service relies on Docker Compose Platform. Ensure you have a running Docker daemon running.
*  From the project root folder execute ``docker-compose up`` command
* API Endpoints defined below would be available at http://localhost:9000 and PostGres SQL Interface Would also be reachable from a Bash Instance connected to it's container instance. User Connection details can be read from docker-compose environment section


### This Project Relies On Maven for it's build, so you'd have to run it locally with Maven Wrapper Script as described: 
* ./mvnw clean spring-boot:run


# The Following REST API Endpoints Are Exposed And Can Be Accessed as described below with a compatible REST Client Tool
* /equadis-bank/create-account/{custID}
*  content-type : application/json  HTTP Method  POST
* JSON Body Schema { "accountNo":xxx, "balance":434.2 }  Integral Type For accountNo, Double For balance Parameter
* API Response : Http Status Code 201, Location: /equadis-bank/create-account/bankAccountNumber


* /equadis-bank/withdraw
*  Content-Type:application/json HTTP Method POST
* JSON Body Schema {amount: double , accountNo:integer , transId: integer }
  parameter         | validation rules
  transactionAmount     Mandatory. Positive values only
  accountNo             Mandatory. Not null.
* API Response JSON {"Successful"} HttpStatus Code 200, HttpStatus Code 400 for Failure


* /equadis-bank/deposit
* Content-Type:application/json HTTP Method POST
* JSON Body Schema {amount: double , accountNo:integer , transId: integer }
  parameter         | validation rules
  transactionAmount     Mandatory. Positive values only
  accountNo             Mandatory. Not null.
* API Response JSON {"Successful"} HttpStatus Code 200, HttpStatus Code 400 for Failure
*
** 
* /get-account/{accountNo}
* HTTP Method GET
* Response Format JSON
* Purpose: Retrieves the specified {accountNo} parameter. Returns the JSON representation of Account number specified in the following JSON Schema
* JSON Response Schema {id: Integer, accountNumber: Integer, balance: Double, createdDate: DateTime, accountOwner :{id:Integer, custID:Integer, name: String, creationDate: DateTime, modifiedDate:DateTime} } or returns HTTP 404 Message
* API Response : HTTP Status Code 200 for successful, Http Status Code 404 for account not found

* /equadis-bank/create-customer
* Purpose: Create a new bank customer. 
* HTTP Content-Type: Application/json HTTP method: POST 
* JSON Body Schema {"custID":1234,"name":"John Doe"}
* JSON Data type: { custID: Integer, name: String }
*  API Response: Http Status Code 201 for successful account creation | HTTP Status Code 400 for Bad Request 

* /equadis-bank/customer/{id}
* content-type:application/json : HTTP Method POST 
* {id} : path parameter customer ID
* Path parameter id : Integer 
* API Response: HTTP Status Code 200 for success JSON Schema body {name: String, custID: Integer}

* /equadis-bank/transactions-type/{accountNo}
* content-type: application/json HTTP Method GET
* JSON Body Schema {"transType": CREDIT | DEBIT }
* API Response: HTTP Status Code 200 : JSON Response Schema [{"amount":double, "accountNo": Integer, "transactionId":Integer,"transactionTime":DateTime}]

* /equadis-bank/transactions-by-id/{accountNo}
* JSON Body Schema {"transId": Integer }
* content-type: application/json HTTP Method GET
* API Response: HTTP Status Code 200 : JSON Response Schema [{"amount":double, "accountNo": Integer, "transactionId":Integer,"transactionTime":DateTime}]