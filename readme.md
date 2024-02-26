# This Document Describes The Mini Bank Task Solution For The Equadis Senior Java Developer Task
Assumptions
-----------
Creating New Customer Assumes Customer Creation Process Has Existing Customer Identifier CustID from Client
Creating New Bank Account Associated With A Customer Assumes Account Number From Client(Via Service)
Being Supplied During Account Creation
Logging Even For JPA Objects Could Be Implemented to track changes to Object Persistent state

## Building The Project:
* The Application Service relies on Docker Compose Platform. Ensure you have a running Docker daemon running.
*  From the project root folder execute ``docker-compose up`` command
* API Endpoints defined below would be available at http://localhost:9000 and PostGres SQL Interface Would also be
* reachable from a Bash Instance connected to it's container instance. User Connection details can be read from
* docker-compose environment section


### This Project Relies On Maven for it's build, so you'd have to run it locally with Maven Wrapper Script
# as decribed: 
* ./mvnw clean spring-boot:run
* 

# The Following REST API Endpoints Are Exposed And Can Be Accessed as described below with a compatible REST Client Tool
* /equadis-bank/create-account
*  content-type : application/json  HTTP Method  POST
* JSON Body Schema { "accountNo":xxx, "balance":434.2 }  Integral Type For accountNo, Double For balance Parameter


* /equadis-bank/withdraw
* content-type: application/json HTTP Method POST
* Body Schema {}


** 
* /get-account/{accountNo} HTTP Method GET
* Purpose: Retrieves the specified {accountNo} parameter. Returns the JSON representation of Account number specified in the following 
* Schema {} or returns HTTP 404 Message