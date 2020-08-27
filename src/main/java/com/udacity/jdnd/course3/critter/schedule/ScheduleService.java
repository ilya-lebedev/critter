package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private PetService petService;
    private EmployeeService employeeService;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           PetService petService,
                           EmployeeService employeeService) {
        this.scheduleRepository = scheduleRepository;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    public Schedule save(Schedule schedule) {
        Schedule savedSchedule = scheduleRepository.save(schedule);
        List<Employee> employees = savedSchedule.getEmployees();
        employees.forEach(employee -> employeeService.addScheduleToEmployee(employee, schedule));
        List<Pet> pets = savedSchedule.getPets();
        pets.forEach(pet -> petService.addScheduleToPet(pet, schedule));

        return savedSchedule;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

}
