package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private PetRepository petRepository;
    private CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Pet save(Pet pet, long ownerId) {
        Customer customer = customerRepository.findById(ownerId).get();
        pet.setCustomer(customer);
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(long id) {
        return petRepository.findById(id).get();
    }

}
