package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {

    private PetRepository petRepository;
    private CustomerService customerService;

    public PetService(PetRepository petRepository, CustomerService customerService) {
        this.petRepository = petRepository;
        this.customerService = customerService;
    }

    public Pet save(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        customerService.addPetToCustomer(savedPet.getCustomer(), savedPet);
        return savedPet;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(long id) {
        return petRepository.findById(id).get();
    }

    public List<Pet> getPetsByIds(List<Long> petIds) {
        return petRepository.findAllById(petIds);
    }

    public List<Pet> getPetsByOwnerId(long ownerId) {
        return petRepository.findPetByCustomerId(ownerId);
    }

    public void addScheduleToPet(Pet pet, Schedule schedule) {
        List<Schedule> schedules = pet.getSchedules();

        if (schedules == null) {
            schedules = new ArrayList<>();
        }

        schedules.add(schedule);
        pet.setSchedules(schedules);
        petRepository.save(pet);
    }

}
