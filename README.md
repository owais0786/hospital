
**Hospital Management System.**

--------------------------------------------------------------------------

Requirement

*Java -8 

*IDE-Intellij

*Xampp  with Apache,MySql,Tomcat Service 

If it does not run try to match as per the screen shot but first try to run with above specification which should work fine

![image](https://github.com/owais0786/hospital/assets/42345643/d6c9e09f-c89d-4032-b8d7-6b8ab1023d67)

![image](https://github.com/owais0786/hospital/assets/42345643/a300dff4-776a-4fba-8f64-a68ce517db75)


--------------------------------------------------------------------------

**FUNCITONALITY**


Hospital Staff can signUp and then login .

After login JWt auth will be generated and using this in Authorisation with Bearer {jwtToken} we can access other API's Mentioned below.

1 userMST/signUp/ for signing up the staff 

2 userMST/login/ for hospital staff login

3 patientMST/create/ for creating patient details

4 patienDoctorRelationshipMST/create/ for creating patient and the doctor relation (say Patient 'Anuj' id-1(PatientMST table) has come to see Doctor 'Batra' id-12(HospitalStaffMST table))  

5 patienDoctorRelationshipMST/patienLatestAdmitDetail/{PatientMSTId) to get any patient latest details


6 patienDoctorRelationshipMST/allPatientByStatus/{AdmitStatus}/{page number}/{page size}  to get all patient based on the  AdmitStatus which can be Discharged or Admitted (here i have used Pageable)

7 patienDoctorRelationshipMST/updatePatientStatus/{patientId}/{AdmitStatus} to update the patient Status where AdmitStatus which can be Discharged or Admitted




# hospital
-----------------------------------------------------------------------------------
1//no authentication required
userMST/signUp/
{
    "userName":"batra@gmail.com",
    "password":"123",
    "staffDesignation":"DOCTOR",
    "contactNumber":"7860456450",
    "userRoleMSTId":1,
    "staffName":"Batra"
}
-----------------------------------------------------------------------------------
2//no authentication required
userMST/login/

{
    "userName":"batra@gmail.com",
    "password":"123",
    "system":"APP"
}

-----------------------------------------------------------------------------------

3//authentication required with jwt token generated at the userMST/login/ API

patientMST/create/
{

"patientName":"Owais Siddiqui",
"address":"Gorakhpur",
"phoneNumber":"8318235969",
"dob":"2000-07-17"
}
-----------------------------------------------------------------------------------
4//authentication required with jwt token generated at the userMST/login/ API

hospitalStaffMST/byStaffDesignation/{Staff_designation}/{page}/{size}

-----------------------------------------------------------------------------------
5//authentication required with jwt token generated at the userMST/login/ API
patientMST/byPhoneNumber/{phonenumber}
-----------------------------------------------------------------------------------
6//authentication required with jwt token generated at the userMST/login/ API

//patientMSTId and hospitalStaffMSTId can be obtained from above API's no-4,no-5

patienDoctorRelationshipMST/create/

{

"roomNo":"001",
"hospitalStaffMSTId":1,
"patientMSTId":1,
"admitStatus":"Admitted",
"admitDate":"2023-01-21",
"expenses":"54"
}

-------------------------------------------------------------------------------------
7//authentication required with jwt token generated at the userMST/login/ API

patienDoctorRelationshipMST/allPatientByStatus/{AdmitStatus}/{page}/{size}

-------------------------------------------------------------------------------------

8//authentication required with jwt token generated at the userMST/login/ API


patienDoctorRelationshipMST/patienLatestAdmitDetail/{PatientMSTId)


--------------------------------------------------------------------------------------
9//authentication required with jwt token generated at the userMST/login/ API

patienDoctorRelationshipMST/updatePatientStatus/{patientId}/{AdmitStatus}
