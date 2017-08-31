RESTful Lab Appointment Management System

Implement a REST service written in Java that returns XML for the description and use case below (has Business Layer rules), plus the REST resources for these paths (Sample output is included at the end):
/Services – GET: A link to the wadl
/Appointments – GET: A list of all appointments and related information
/Appointments/{appointment} – GET: A specific appointment and related information
/Appointments – POST: Create a new appointment providing the required information in XML and receiving XML with a link to the newly created appointment or error message
/Appointments/{appointment} – PUT: Update a new appointment providing the required information in XML and receiving XML with a link to the newly created appointment or error message

Appointment Process

A patient will call to schedule an appointment, stating his or her laboratory appointment needs. Patients may request a specific phlebotomist. The appointment specialist will check the availability of the phlebotomist for specified date and time, and will book the appointment. Details of the appointment are confirmed with an appointment card that is mailed to the patient.

Four different events could occur following the original scheduling of the appointment:

1)The patient arrives at the appropriate service center at the scheduled appointment time and a specimen is collected for testing.
2)The patient cancels the appointment.
3)The appointment requirements change (new location, new time, etc) requiring a subsequent confirmation.
4)The patient does not show up for the appointment

The system must capture the necessary patient, appointment, testing, and billing information at the time the appointment is scheduled.
Appointments can be made by placing a telephone call to a Patient Service Center, the Medical Laboratory office, or directly via a web-based interface. When the requested date and time cannot be satisfied, the new system will have the ability to offer an appointment with the requested phlebotomist and patient service centers at an alternate date and time.
Each patient service center has at least one appointment specialist and they are the only type of employee authorized to 'book' appointments for patients. The system should retain patient information, in addition to appointment and test information history.
While the system will not be required to process patient billing, the system must be capable of interfacing to the existing billing information system.

Use Case Name:

Set an Appointment
Primary Actor:

Patient
Secondary Actor:

Appointment Specialist
Description:

A patient schedules an appointment for a laboratory test ordered by an attending physician.

Pre-Conditions:

The patient is registered with Cellular One, i.e., exists in the system. 
The patient's physician is valid and exists in the system. 
The ordered lab test is valid and exists in the system. 
The requested phlebotomist is valid and exists in the system. 
Post-Condition:

The appointment has been registered in the system.

Normal Course of Action:

The Patient requests a laboratory appointment. 
The Appointment Specialist requests: patient first & last name, date of birth, mailing address, tests ordered (by test number), desired phlebotomist, ICD-9 diagnosis code, desired patient service center, requested appointment date and time.
The Patient provides information requested. 
The Appointment Specialist selects the correct Patient from the system [patient's first & last name, date of birth]
The System displays patient information [patient identifier, patient's first & last name, date of birth, insurance (y/n), physician name].
The Appointment Specialist confirms correct patient has been selected.
The Appointment Specialist requests appointment [patient requested: date, time, phlebotomist, patient service center].
The System checks availability based upon appointment requirements. 
The System creates a unique appointment number (not the data component).
The System determines the cost of each test and calculates the total cost (the cost of each test is stored by the data component).
The System reserves the appointment [appointment number, date, time, phlebotomist, patient service center, patient id, test number].
The System displays the appointment number, date, time, phlebotomist, tests ordered, and total cost of testing, and requests confirmation. 
The Appointment Specialist commits appointment.
The Appointment Specialist confirms appointment with patient and provides the appointment number. 
Extensions:

The System displays "phlebotomist, and/or patient service center not available at that time" conflict message.
The System determines the next available date and time for the phlebotomist and patient service center requested. 
The System displays next available [date, time] for the requested phlebotomist and patient service center.
The Appointment Specialist requests that the patient accept the available date, time.
If Patient accepts, then return to step 10 in the normal course, otherwise terminate use case.
Assumptions:

The duration for each appointment is 15 minutes. 
Appointments can be made from 8am to 5pm.
It takes a phlebotomist 30 minutes to get from one PSC to another and be ready for an appointment after the end of another appointment. The phlebotomist can be at any PSC at 8am, however, if his next appointment is at another PSC, he needs 30 minutes from 8:15am to get to that next PSC. The phlebotomist will remain at the PSC of his appointment unless he is requested at another PSC for his next appointment (within 30 minutes).


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
