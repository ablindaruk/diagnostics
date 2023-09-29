Technical challenge
===================
Health & Help, a charity organization operating medical clinics in rural areas of Guatemala and Nicaragua gave you
a task to develop a backend API for their new practice management web application.
It is required to use Spring Boot and JDK 21 to build HATEOAS API (REST level 3 in terms of Richardson Maturity Model).
Build a web application that supports two users.
To use HAL+JSON as the API data format.
Successful login at /login will return an access token for other endpoints.
Entry point to the API will be the endpoint /start. Depending on presence of access token, this endpoint will either
return the link to /login, or the link to the other endpoints, depending on the user role.

Available endpoints
===================
Role DOCTOR:
* Resource "stock" with the list of drugs and medical appliances available in stock. Individual items from this list
should be also accessible via "self" links.
* Resource "patients" with the list of patients treated in the clinic. Doctors can add new patients via POST request
to this resource.

Role MANAGER:
* Resource "stock" (see above for role Doctor)
* Resource "food" with the list of the food remaining in the storage of the clinic (can be hardcoded). Individual items
from this list should be accessible via "self" links. Manager can add new items to this resource via POST request.

FOR YOUR GUIDANCE:
=================
For prompt familiarization first you may run tests in /test folder to fill in DB tables with required data.

Principal credentials:
user: "doctor@gmail.com", password: "guatemala", role: ROLE_DOCTOR;
user: "manager@gmail.com", password: "nicaragua", role: ROLE_MANAGER;

MySQL DB details in application.properties:
url=jdbc:mysql://localhost:3306/hospitaldb
username=root
password=root

Endpoints (GET, POST requets depends on roles applied):
START = "http://localhost:8080/start";
LOGIN = "http://localhost:8080/login";
STOCK = "http://localhost:8080/stock";
STOCK = "http://localhost:8080/stock/{number}";
PATIENTS = "http://localhost:8080/patients";
PATIENTS = "http://localhost:8080/patients/{number}";
FOOD = "http://localhost:8080/food";
FOOD = "http://localhost:8080/food/{number}";