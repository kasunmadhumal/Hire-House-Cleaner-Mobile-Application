# Hire-House-Cleaner-Mobile-Application


###We still need to schedule appointments with people to fix issues that relate to our everyday lives, such as house cleaning, and mechanical, electrical, and electrical problems, as well as plumbing-related issues.
It is not certain that we will have the appointment of the service provider at the time because numerous issues might arise, such as the service provider being busy someplace else or not being present at his office when we arrive there, or demanding a high cost to solve the problem, etc.
The limitations of the existing system must be identified before the introduction of a new system.
    * The existing system is offline.
    * Complicated record management.
    * There is no time limit for service to be provided.
    * No service guarantees.
    * Difficult to find a proper service provider.

###We will create an Android application where users may receive the right results to solve this kind of issue.
This Android application is straightforward and dynamic. Anyone can easily use the Android application's interface because it is so simple.


##Users
###HireHouseCleaners apps consist of three users:
    * Admin user
    * House cleaner
    * Customer
    
 ###Below are the characteristics of each user.
 
 ##Admin
###Set Payment: in this section, the admin sets the prices for the order sent by the user by observing the requirement and the details of the house.

##House cleaner
###Sign up/sign in: Register to the service and add the locations where they can provide their services.
Manage requests: this section is for getting notifications about requests. The service provider gets all the requests that match with provided locations he can deliver the service. And he can check that request and compare it with the payment and accept. If one house cleaner has accepted the request, then the notification will be removed from the other house cleaners’ dashboards.
Review: The cleaner can put their idea about the job.

##Customer
###Register/login: customers register to the app by providing the location of their house.
Adding House Details: in this section, customers should provide details about their house. And the customer can add images of the house.
Requesting Service: Customers can send a request to clean their house by clicking on the ‘send order’ button. The request will go to the admin and then the admin will decide on the cost. Then the request will be shown to the cleaners.
Rate and review: this section is for rating and reviewing the cleaners’ work.

##Technologies
###Technologies used to build the HireHouseCleaners mobile app.
              - IDE – Android Studio
              - Programming Language – Kotlin
              - Database Management – Firebase, SQLite

#Testing
###Validation, practical use, and navigation of the system were tested.
1. User must log in to the system using a unique login name and password for validation testing. All required fields must be filled out by the user. If the user missed any field, a warning message is shown.
2. Functional testing: The system's components were divided into subsystems. User data is added to or updated in the database.
3. Navigational Testing: The system was tested to determine that each page and its corresponding links are properly accessible.
We have conducted the following testing to identify the system errors:

##input verification:
###Tested the validation process during this phase. The correct input format is checked when users enter data in the corresponding text box or grids. If entering numerical data is necessary, the user is restricted from doing so. The user must only enter text data if the data is text (alphanumeric), and check for null values.
##Condition Testing:
###Condition testing is a technique that tests the software module's logical condition. Each relationship proposition was evaluated and placed to the test individually. For testing, extreme case values are provided.
##Output testing: 
###The first phase of testing is determining how user-friendly it is. After that, its correctness is examined to see if it can report missing data, provide all relevant data, etc.


Screen Shots of the Application
Main Interfaces

Log In								Sign Up

![image](https://user-images.githubusercontent.com/73273550/201736026-45f7ff4d-c075-42ae-958f-b0ad6342140f.png)    ![image](https://user-images.githubusercontent.com/73273550/201736067-46aedde1-0b4a-42c2-9a0d-dae0e9ecfb14.png)





Customer Home Page						Update House Details




























Add Home Images	View Home Images



























Admin Home Page	Add Payment by Admin



























House Cleaner Users’ Home Page	After Accept a order



























Database
User Table (Customer)

User Table(Cleaner)

Order Table (Before a Order Get Accepted)

Order Table (After Order Accepted)


Feedback Table

Customer Home Images in Storage 

















Test Cases
Log In Without Passwords	Incorrect Password











Leaving empty Fields	Select User Role









Passwords Don’t Match	Order Send Test
























