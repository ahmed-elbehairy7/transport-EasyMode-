# Transportation Company System project

## System Requirements:

-   ~~Implement a system that has a username and password authentication.~~

-   ~~A System has a group of users that are either Passenger or Employee [drivers, managers],~~ List of vehicles (bus, minibus, limousine) and List of trips (internal, external).

-   ~~A user can login or register [Passenger or Employee].~~

-   ~~Each Passenger has a name, ID, TicketType (one-way, round trip) and TripDetails (information about the reserved trip) and does some actions like select trips, booking tickets, reviewing/canceling tickets, and displaying the passenger profile.~~

-   ~~Each Employee has a name, ID,Type (driver, manager) and does some actions like view assigned trips (for drivers), managing (add/cancel) trips,AddVehicle, AddEmployee, GenerateReport: [Generates a report containing information about vehicles, employees, and trips] (for manager), and accessing basic information (for drivers).~~

-   ~~Each trip includes attributes such as Type (internal, external), price, source, destination, one-way/round-trip, number of stops, available seats, and price and adding/removing trips and assigning drivers (for manager).~~

-   ~~Each Vehicle has a type (bus, minibus, limousine), Capacity, LicensePlate and does some action like setType, setCapacity, DisplayInfo.~~

-   ~~You must work with files to store the trips, passengers, employees, vehicles information and store information about reserved tickets.~~

-   ~~Passengers, Employees registered in the system must be kept every time the program reruns i.e. you have to keep a database in files.~~

## Scenario/Final Product:

Your program should follow the following scenario:

### Global:

-   ~~A user will start the program with either registering a new account or logging in and then select which kind of user is using the program a passenger or an employee.~~

### Passenger:

-   ~~If a passenger is selected, he then is asked to enter a username and a password for authentication and then he opens his profile.~~

-   ~~From the passenger profile he is able to select the trip he wants to make (source,destination,one-way,round-trip,number of stops … etc) from a list of available trips.~~

-   ~~When the passenger books a ticket (if there are available seats) he is shown a price for the selected ticket(s) and then proceeds to buy them.~~

-   ~~The passenger is able to review and cancel his tickets from his profile.~~

### Employees:

-   ~~If an employee is selected,that employee can be a manager or a driver~~

### Driver:

-   ~~If you log in with a driver credentials you are directed to the drivers profile with some basic information about the driver and the trips that are assigned to him by the manager.~~

### Manager:

-   ~~If you log in as a manager you are able to review all trips in the system,you are able to add/remove trips and assign drivers to the trips in the system.~~
