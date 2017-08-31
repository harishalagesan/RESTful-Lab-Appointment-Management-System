Client - Project_3_Client

1) Run the client application with the following URL - Load the client to your localhost with same URL
or change the URL in page_start.php to your local host address.

http://localhost:8012/client/ - This opens the index.php page.

2) Home - index.php - http://localhost:8012/client/index.php contains all the options to choose to perform various operations.
   This is also the Home page - Home button can be clicked to come back to this page from any other page.   

3) Services - services.php - http://localhost:8012/client/services.php - This page contains the WADL link

4) View Appointments - allAppointments.php - http://localhost:8012/client/allAppointment.php
   This page contains a calender view of all appointments present currently in the database.
   Each new appointment added or edited is updated here also.
   The calender months start from te first appointment avialbale on the database according to date.
   The calender can be traversed 3 months at a time - a bit of a problem but still eorks fine.
   Clicking on each appointment leads to the Server XML page of the respective appointment ( CLick on the name )

5) Add Appointments - setAppt.php - http://localhost:8012/client/setAppt.php 
   Displays a form that allows you to add an appointment by selecting the options provided pn each field.
   Datepicker option for selecting the appointment date.
   TimePicker option for selecting the time - in 12 hour format with AM and PM specifications.
   All other fields are populated from existing options in the database for each field. Its a dropdown select.

6) Edit Appointments - editAppointment.php - http://localhost:8012/client/editAppointment.php
   Displays the list of all appointments added with the data and time of appointment.
   Click on each appointment will populate a form with the details of the appointment clicked.
   Changes can be made to the form for editing the appoitment and updated.
   Changes will be reflected back on the same page in the form.

Server - REST_IST756

1) Run the glassfish server on the following URL - Load the server files to your localhost with same URL
   http://localhost:8080/Rest_IST756/AptService

2) Home - http://localhost:8080/Rest_IST756/AptService - will display a message to show if the server is up and running.

3) services - http://localhost:8080/Rest_IST756/AptService/Services - will provide the xml for the wadl link.

4) All Appointments - http://localhost:8080/Rest_IST756/AptService/Appointments - will provide the list of all appointments present.

5) Specific Appointment - http://localhost:8080/Rest_IST756/AptService/Appointments/(provide appointmet ID)
   example - http://localhost:8080/Rest_IST756/AptService/Appointments/793 - will give all details of appointment ID 793

6)Adding a new Appointment - Provide http://localhost:8080/Rest_IST756/AptService/Appointments - this url
  in Postman and select the POST method with the xml in the body of the message the respective result will display 
   that a new appointment was added where the URI is hown.
 
   IF there was an error then the error message displays in xml format.

7) Editing an appointment - Provide http://localhost:8080/Rest_IST756/AptService/Appointments - this url
  in Postman and select the PUT method with the xml in the body of the message the respective result will display 
   that the appointment was edited where the URI is shown.
 
   IF there was an error then the error message displays in xml format.

