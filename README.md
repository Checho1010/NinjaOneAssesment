# NinjaOne Backend Interview Project

This project contains [Instructions](INSTRUCTIONS.md) that must be read in order to perform NinjaOne's code assessment.
Also the project is configured to use an in-memory H2 database that is volatile. If you wish to make it maintain data on
application shut down, you can change the spring.database.jdbc-url to point at a file like `jdbc:h2:file:/{your file path here}`

## Starting the Application

Run the `BackendInterviewProjectApplication` class

Go to:
* http://localhost:8080/rmm/v1/device/all

You should see results for both of these. The application is working and connected to the H2 database. 

## H2 Console 

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```

You should be able to see a db console now that has the Sample Repository in it.

Type:

```sql
SELECT * FROM SAMPLE;
````

Click `Run`, you should see two rows, for ids `1` and `2`



# Resolution Instructions

# REST API for Monitor and Manage Devices 

REST API created with spring boot and java, with gradle repository, the API use H2 Database.

The entity relation diagram is as follows:

![Alt text](EntityRelation.jpg?raw=true "Title")


# Coverage Test

![Alt text](TestCoverage.jpg?raw=true "Title")


## How to use the API?

The Endpoints are distributed in 5 Controllers which can be accesible through Postman:
![Alt text](PostmanClient.jpg?raw=true "Title")

The Endpoint URLs are as follows:
###DEVICES:

#####Get one (GET METHOD):

http://localhost:8080/rmm/v1/device/2

#####Get all (GET METHOD):

http://localhost:8080/rmm/v1/device/all

#####Delete (DELETE METHOD):

http://localhost:8080/rmm/v1/device/delete?deviceId=1

#####Create (POST METHOD):

http://localhost:8080/rmm/v1/device/create

Json:
{
    "systemName": "Ipad3",
    "type": "Android"
}

###SERVICES:

#####Get one (GET METHOD):

http://localhost:8080/rmm/v1/service/9

#####Get all (GET METHOD):

http://localhost:8080/rmm/v1/service/all

#####Delete (DELETE METHOD):

http://localhost:8080/rmm/v1/service/delete?serviceId=3

#####Create (POST METHOD):

http://localhost:8080/rmm/v1/service/create

Json:

```
{
    "description": "Antivirus for Windows2",
    "type": "Windows",
    "cost": 11
}
```

###CUSTOMERS:

#####Get one (GET METHOD):

http://localhost:8080/rmm/v1/customer/1

#####Get all (GET METHOD):

http://localhost:8080/rmm/v1/customer/all

#####Delete (DELETE METHOD):

http://localhost:8080/rmm/v1/customer/delete?customerId=1

#####Create (POST METHOD):
http://localhost:8080/rmm/v1/customer/create

Json:

```
{
    "name": "Juani",
    "numberOfDevices": 3
}
```

###SERVICE ASSIGNATION:

#####Get on (GET METHOD)e:

http://localhost:8080/rmm/v1/serviceAssignation/1

#####Get all (GET METHOD):

http://localhost:8080/rmm/v1/serviceAssignation/all

#####Get all by customer id (GET METHOD):

http://localhost:8080/rmm/v1/serviceAssignation/customer/1

#####Delete (DELETE METHOD):

http://localhost:8080/rmm/v1/serviceAssignation/delete?serviceAssignationId=1

#####Create (POST METHOD):

http://localhost:8080/rmm/v1/serviceAssignation/create

Json:

```
{
    "quantity": 9,
    "serviceId": "7",
    "deviceId": "4",
    "customerId": "1"
}
```

###COST CALCULATION:

#####By Customer (GET METHOD):
http://localhost:8080/rmm/v1/costCalculation/customer/1

# What would I do if I had more time to implement the solution:
- Make Swagger works, it's a useful tool to understand the structure of the REST API.
- Load the value per device from a configuration file.
- Validate that the number of services of a certain type, doesn't exceed from the number of devices that the customer have, for example if the customer has 10 devices, it can't have assigned more than 10 antiviruses or  more than 10 backups or more than 10 screen shares. I may do this by adding a table "TYPE" in the Data Base so i can relate it to the other tables (DEVICE, SERVICE and CUSTOMER).
- On the unit test, cover the case on the creation of devices or services, when the type doesn't match with the codes established in the TypeEnum.
- On the cost calculation method, I would like to detail the cost per type of service and type of device, i'm actually showing the total cost of all the services.
- Properly finish the CostCalculationServiceTest methods.
