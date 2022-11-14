# Hire-House-Cleaner-Mobile-Application


### We still need to schedule appointments with people to fix issues that relate to our everyday lives, such as house cleaning, and mechanical, electrical, and electrical problems, as well as plumbing-related issues.
### It is not certain that we will have the appointment of the service provider at the time because numerous issues might arise, such as the service provider being busy someplace else or not being present at his office when we arrive there, or demanding a high cost to solve the problem, etc.The limitations of the existing system must be identified before the introduction of a new system.
     - The existing system is offline.
     - Complicated record management.
     - There is no time limit for service to be provided.
     - No service guarantees.
     - Difficult to find a proper service provider.

### We will create an Android application where users may receive the right results to solve this kind of issue.
This Android application is straightforward and dynamic. Anyone can easily use the Android application's interface because it is so simple.


## Users
### HireHouseCleaners apps consist of three users:
    - Admin user
    - House cleaner
    - Customer
    
 ### Below are the characteristics of each user.
 
 ## Admin
### Set Payment: in this section, the admin sets the prices for the order sent by the user by observing the requirement and the details of the house.

## House cleaner
### Sign up/sign in: Register to the service and add the locations where they can provide their services.
Manage requests: this section is for getting notifications about requests. The service provider gets all the requests that match with provided locations he can deliver the service. And he can check that request and compare it with the payment and accept. If one house cleaner has accepted the request, then the notification will be removed from the other house cleaners’ dashboards.
Review: The cleaner can put their idea about the job.

## Customer
### Register/login: customers register to the app by providing the location of their house.
Adding House Details: in this section, customers should provide details about their house. And the customer can add images of the house.
Requesting Service: Customers can send a request to clean their house by clicking on the ‘send order’ button. The request will go to the admin and then the admin will decide on the cost. Then the request will be shown to the cleaners.
Rate and review: this section is for rating and reviewing the cleaners’ work.

## Technologies
### Technologies used to build the HireHouseCleaners mobile app.
              - IDE – Android Studio
              - Programming Language – Kotlin
              - Database Management – Firebase, SQLite

# Testing
### Validation, practical use, and navigation of the system were tested.
1. User must log in to the system using a unique login name and password for validation testing. All required fields must be filled out by the user. If the user missed any field, a warning message is shown.
2. Functional testing: The system's components were divided into subsystems. User data is added to or updated in the database.
3. Navigational Testing: The system was tested to determine that each page and its corresponding links are properly accessible.
We have conducted the following testing to identify the system errors:

## input verification:
### Tested the validation process during this phase. The correct input format is checked when users enter data in the corresponding text box or grids. If entering numerical data is necessary, the user is restricted from doing so. The user must only enter text data if the data is text (alphanumeric), and check for null values.
## Condition Testing:
### Condition testing is a technique that tests the software module's logical condition. Each relationship proposition was evaluated and placed to the test individually. For testing, extreme case values are provided.
## Output testing: 
### The first phase of testing is determining how user-friendly it is. After that, its correctness is examined to see if it can report missing data, provide all relevant data, etc.


Screen Shots of the Application
Main Interfaces

Log In &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	Sign Up

![image](https://user-images.githubusercontent.com/73273550/201736026-45f7ff4d-c075-42ae-958f-b0ad6342140f.png)    ![image](https://user-images.githubusercontent.com/73273550/201736067-46aedde1-0b4a-42c2-9a0d-dae0e9ecfb14.png)





Customer Home Page				                                                          		Update House Details

![image](https://user-images.githubusercontent.com/73273550/201736448-36409a9e-ed45-4ef2-b5a6-00972a356bd4.png)    ![image](https://user-images.githubusercontent.com/73273550/201736513-9644fda0-0cb1-4c58-8dbd-863ae53e2398.png)





Add Home Images	                  View Home Images


![image](https://user-images.githubusercontent.com/73273550/201736586-5ac43174-480c-4553-b2e3-e3bbf0b002ee.png)    ![image](https://user-images.githubusercontent.com/73273550/201736615-084d1c7e-7441-4d07-8a7e-cd298d20491c.png)



Admin Home Page	                   Add Payment by Admin

![image](https://user-images.githubusercontent.com/73273550/201736704-2d5fe7d0-6819-4974-867c-54a6cbe4f338.png)     ![image](https://user-images.githubusercontent.com/73273550/201736747-eba5f89b-07ba-4c5e-8ee4-ad4d2367b5b8.png)





House Cleaner Users’ Home Page	After Accept a order

![image](https://user-images.githubusercontent.com/73273550/201736825-653e99e2-2c6c-4430-949b-d8d2f262566c.png)      ![image](https://user-images.githubusercontent.com/73273550/201736850-27111d55-f4a1-4b66-b46e-5834e051bbdd.png)




## Database
User Table (Customer)
![image](https://user-images.githubusercontent.com/73273550/201736942-53eab73e-8a79-4eb1-80b8-bb35e1439635.png)


User Table(Cleaner)
![image](https://user-images.githubusercontent.com/73273550/201736964-905cbf68-ef87-47c9-bcfd-0c689651d000.png)


Order Table (Before a Order Get Accepted)
![image](https://user-images.githubusercontent.com/73273550/201736993-ed4bc166-2074-4e98-ae98-499bd35b9b1b.png)


Order Table (After Order Accepted)
![image](https://user-images.githubusercontent.com/73273550/201737031-9b261ceb-f291-4b77-a5d8-f6f10ae01b13.png)



Feedback Table
![image](https://user-images.githubusercontent.com/73273550/201737062-87e4fc12-146b-4b8e-a55b-786c1a6fe8b6.png)


Customer Home Images in Storage 

![image](https://user-images.githubusercontent.com/73273550/201737120-cfc35101-133e-4b1d-ba4e-2ad6436787eb.png)






## Test Cases(User data validation)
Log In Without Passwords	                 Incorrect Password

![image](https://user-images.githubusercontent.com/73273550/201737262-b9db9269-e60b-4395-b285-f9a5833c6f56.png)    ![image](https://user-images.githubusercontent.com/73273550/201737298-47790ba0-cedb-4b0a-862e-b84fab3ae9dd.png)




Leaving empty Fields	Select User Role

![image](https://user-images.githubusercontent.com/73273550/201737343-78822045-bf02-4ae6-bd79-cb466f1de7fe.png)     ![image](https://user-images.githubusercontent.com/73273550/201737372-ea5d7ef6-bc5f-47e0-a1dc-a5cde5c9af6b.png)


Passwords Don’t Match	                     Order Send Test


![image](https://user-images.githubusercontent.com/73273550/201737416-a65d67f9-3fec-4908-9adb-d2858da7451c.png)      ![image](https://user-images.githubusercontent.com/73273550/201737428-ec01f8ec-d487-4886-b19d-c356bd5bf920.png)


























