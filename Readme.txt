FOR YOUR GUIDANCE:

For prompt familiarization you may run tests in /test folder.
After all tests run DB tables will be filled in with required data automatically.

Principal credentials:
user: "doctor@gmail.com", password: "guatemala", role: ROLE_DOCTOR;
user: "manager@gmail.com", password: "nicaragua", role: ROLE_MANAGER;

MySQL DB details in application.properties:
url=jdbc:mysql://localhost:3306/hospitaldb
username=root
password=root

URLs:
START = "http://localhost:8080/start";
LOGIN = "http://localhost:8080/login";
STOCK = "http://localhost:8080/stock";
STOCK = "http://localhost:8080/stock/{number}";
PATIENTS = "http://localhost:8080/patients";
PATIENTS = "http://localhost:8080/patients";
FOOD = "http://localhost:8080/food";
FOOD = "http://localhost:8080/food/{number}";