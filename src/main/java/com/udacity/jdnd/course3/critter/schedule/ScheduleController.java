package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;
    private PetService petService;
    private EmployeeService employeeService;
    private CustomerService customerService;

    public ScheduleController(ScheduleService scheduleService,
                              PetService petService,
                              EmployeeService employeeService,
                              CustomerService customerService) {
        this.scheduleService = scheduleService;
        this.petService = petService;
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.save(convertScheduleDTOToSchedule(scheduleDTO));
        return convertScheduleToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return convertScheduleListToScheduleDTOList(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = petService.getPetById(petId).getSchedules();
        return convertScheduleListToScheduleDTOList(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = employeeService.getEmployeeById(employeeId).getSchedules();
        return convertScheduleListToScheduleDTOList(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = customerService.getScheduleForCustomer(customerId);
        return convertScheduleListToScheduleDTOList(schedules);
    }

    private List<ScheduleDTO> convertScheduleListToScheduleDTOList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    private List<Schedule> convertScheduleDTOListToScheduleList(List<ScheduleDTO> scheduleDTOs) {
        return scheduleDTOs.stream()
                .map(this::convertScheduleDTOToSchedule)
                .collect(Collectors.toList());
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> employeeIds = schedule.getEmployees()
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        scheduleDTO.setEmployeeIds(employeeIds);
        List<Long> petIds = schedule.getPets()
                .stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
        scheduleDTO.setPetIds(petIds);

        return scheduleDTO;
    }

    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Pet> pets = petService.getPetsByIds(scheduleDTO.getPetIds());
        schedule.setPets(pets);
        List<Employee> employees = employeeService.getEmployeesByIds(scheduleDTO.getEmployeeIds());
        schedule.setEmployees(employees);

        return schedule;
    }

}
