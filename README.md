# Event Management System

A fully completed Events Management software that is composed of four 4 windows. Each window has it's fully Database-backed windows. Each Window is related to the other one in a way or another that I will explain shortly. The project was fully made in Java with JavaFX framework that can be ran on Intellij. The database can be loaded from a .sql file provided. The four windows are:

* Customer 
* Manager
* Admin
* Service Provider


## Customer Window
The Customer is capable of registering and making an account that is saved into a database. An Email is also sent with the account details. They can then proceed to login to access their account, which is verified through the database. The customer is capable of creating an event. An Email is sent of the event details. They are also capable of viewing their own event and edit their own personal information.


## Manager Window
The Manager is capable of logging into their own account. They can view the Events that are made by the Customer along with view its details and give an approval. Once an Approval has been given, an Email is sent to the customer of it's approval.

## Service Provider Window
The Service Provider is capable of viewing the approved events and confirming it's dates. Once the Event is confirmed, an email is sent to the customer with the confirmation.

## Admin Window
The Admin is capable of making changes to most of the viewable panels. The Admin can edit the available Carterers and Venues. They can edit the Employees registered in the system that can get access to the Manager Window. They can also edit their own personal information.

###### If you would like to run the program

This program was fully developed on Intellij. You can download it and import it into Intellij with JDK 15. Download MySQL as well and import the .sql file provided. 
