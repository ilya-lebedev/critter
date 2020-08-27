# Critter Chronologer Project

This is my project from "Data Stores and Persistence" course of "Java Web Developer Nanodegree Program" in Udacity.

Critter Chronologer a Software as a Service application that provides a scheduling interface for a small business 
that takes care of animals. This Spring Boot project allows users to create pets, owners, and employees, 
and then schedule events for employees to provide services for pets.


## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Framework providing dependency injection, web framework, 
data binding, resource management, transaction management, and more.
* [Google Guava](https://github.com/google/guava) - A set of core libraries used in this project for their collections 
utilities.
* [H2 Database Engine](https://www.h2database.com/html/main.html) - An in-memory database used in this project to 
run unit tests.
* [MySQL Connector/J](https://www.mysql.com/products/connector/) - JDBC Drivers to allow Java to connect 
to MySQL Server


## Testing

### Tested Conditions

* `testCreateCustomer` - **UserController.saveCustomer** returns a saved customer matching the request
* `testCreateEmployee` - **UserController.saveEmployee** returns a saved employee matching the request
* `testAddPetsToCustomer` - **PetController.getPetsByOwner** returns a saved pet with the same id and name as the 
one saved with **UserController.savePet** for a given owner
* `testFindPetsByOwner` - **PetController.getPetsByOwner** returns all pets saved for that owner.
* `testFindOwnerByPet` - **UserController.getOwnerByPet** returns the saved owner used to create the pet.
* `testChangeEmployeeAvailability` - **UserController.getEmployee** returns an employee with the same availability 
as set for that employee by **UserControler.setAvailability**
* `testFindEmployeesByServiceAndTime` - **UserController.findEmployeesForService** returns all saved employees that 
have the requested availability and skills and none that do not
* `testSchedulePetsForServiceWithEmployee` - **ScheduleController.createSchedule** returns a saved schedule matching 
the requested activities, pets, employees, and date
* `testFindScheduleByEntities` - **ScheduleController.getScheduleForEmployee** returns all saved schedules containing 
that employee. **ScheduleController.getScheduleForPet** returns all saved schedules for that pet. 
**ScheduleController.getScheduleForCustomer** returns all saved schedules for any pets belonging to that owner.

### Postman
In addition to the included unit tests, a Postman collection has been provided. 

1. Open Postman.
2. Select the `Import` button.
3. Import the file found in this repository under `src/main/resource/Udacity.postman_collection.json`
4. Expand the Udacity folder in postman.

Each entry in this collection contains information in its `Body` tab if necessary and all requests should function 
for a completed project. Depending on your key generation strategy, you may need to edit the specific ids in these 
requests for your particular project.


## License

This project is licensed under the MIT License - see the [LICENSE.md]()
