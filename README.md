# BasicAuthenticationAPIsSystem

Copy to readme file - BaiscSecurityAPIsSystem task:

Author: Elran Manor

Description:
In this task I was required to implement a basic security APIs  that support registration , login , userData back by token. all of that without using the Spring security functionality.

Currently I used in-memory solution (keep values in local HashMap).
There is not real DB connection implemented for this task yet. 

############################################################
# IMPORTANT NOTE: 												#
# For testing proposes , the expiration date was set to be with +2 minutes Since the minute it was created.if needed, you can change it in code and change it for more or less than that code relevant line: (BasicAuthenticationUtil.jwtCreation()).  setExpiration(Date.from(time.plus(2, ChronoUnit.MINUTES)))
############################################################

List of 3rd party jar dependencies used for that purpose:
———————————————————————————
<!--JJWT dependencies-->
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt-api</artifactId>
	<version>0.10.7</version>
</dependency>
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt-impl</artifactId>
	<version>0.10.7</version>
	<scope>runtime</scope>
</dependency>
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt-jackson</artifactId>
	<version>0.10.7</version>
	<scope>runtime</scope>
</dependency>
<!--GSON dependency-->
<dependency>
	<groupId>com.google.code.gson</groupId>
	<artifactId>gson</artifactId>
	<version>2.8.6</version>
</dependency>
<!--ENCRYPTION/DECRYPTION 3rd party jar-->
<dependency>
	<groupId>commons-codec</groupId>
	<artifactId>commons-codec</artifactId>
	<version>1.10</version>
</dependency>


Valid Scenarios covered in this task:
—————————————————
1. Registration API

a. Success Request  - registration completed successfully. 
————————-
Request mode: POST
URL: http://localhost:8080/registration
Input: (not exist on DB)
{
    "fullName" : "foo",
    "emailAddress" : "foo@fish.com",
    "password" : "passworddd"
}

Response:

Status code: 200

Response body:
{
    "statusCode": "200 OK",
    "statusDescription": "Registration step passed successfully"
}


b. Failure Request - registration failed due to already exist user in DB
———————-
Request mode: POST
URL: http://localhost:8080/registration
Input: (exist on DB after 1st run)
{
    "fullName" : "foo",
    "emailAddress" : "foo@fish.com",
    "password" : "passworddd"
}

Response:

Status code: 400

Response body:
   can't register , email address foo@fish.com is already exist

########################

2. Login API

a. Success Request  - Login completed successfully and sent back token 
————————-
Request mode: POST
URL: http://localhost:8080/login
Input: 
{
    "userName" : "foo@fish.com",
    "password" : "passworddd"
}


Response:

Status code: 200

Response body:
{
    "statusCode": "200",
    "statusDescription": "Authentication of user: foo@fish.com passed successfully",
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdXRoZW50aWNhdGUgVXNlciIsInBhc3N3b3JkIjoiZWVlZTBlYmM0MzM3NzUzM2NjMzE5YmM0YWIzNzE0NTciLCJ1c2VyTmFtZSI6ImZvb0BmaXNoLmNvbSIsImlhdCI6MTYyMzUyMjUyMCwiZXhwIjoxNjIzNTIyNjQwfQ.aJIGpEYAYnFUOF5C70jd4_hoNajpgnnm0M_kXdeqCTA"
}



b. Failure Request - registration failed due to previous login already been made.
———————-
Request mode: POST
URL: http://localhost:8080/login
Input: (exist on DB after 1st run with token and secret)
{
    "userName" : "foo@fish.com",
    "password" : "passworddd"
}


Response:

Status code: 400

Response body:
   user foo@fish.com is already logged in system.



c. Failure Request - trying to login with a username that doesn’t exist in the system.
———————-
Request mode: POST
URL: http://localhost:8080/login
Input: (username not exist in the DB)
{
    "userName" : "foo@fish.org.uk",
    "password" : "passworddd"
}


Response:

Status code: 400

Response body:
  user foo@fish.org.uk not exist. need to be registered first! 


#####################

3. UserData API

a. Success Request  - send token and receiving fullname back in response
————————-
Request mode: POST
URL: http://localhost:8080/userdata
Input: 
{
    "jwt" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdXRoZW50aWNhdGUgVXNlciIsInBhc3N3b3JkIjoiZWVlZTBlYmM0MzM3NzUzM2NjMzE5YmM0YWIzNzE0NTciLCJ1c2VyTmFtZSI6ImZvb0BmaXNoLmNvbSIsImlhdCI6MTYyMzUyMzUwNywiZXhwIjoxNjIzNTIzNjI3fQ.IFf4xKHS4VMh5Wt_4iwk4n1zuFStf30ygtbn5CI8ml4"
}



Response:

Status code: 200

Response body:
{
    "statusCode": "200",
    "statusDescription": "Full Name of user foo@fish.com is: foo",
    "fullName": "foo"
}


b. Failure Request - JWT token has been expired
———————-
Request mode: POST
URL: http://localhost:8080/userdata
Input: (JWT expiration time was ended)
{
    "jwt" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdXRoZW50aWNhdGUgVXNlciIsInBhc3N3b3JkIjoiZWVlZTBlYmM0MzM3NzUzM2NjMzE5YmM0YWIzNzE0NTciLCJ1c2VyTmFtZSI6ImZvb0BmaXNoLmNvbSIsImlhdCI6MTYyMzUyMzUwNywiZXhwIjoxNjIzNTIzNjI3fQ.IFf4xKHS4VMh5Wt_4iwk4n1zuFStf30ygtbn5CI8ml4"
}



Response:

Status code: 400

Response body:
  failed to find a matching data for your token key.


