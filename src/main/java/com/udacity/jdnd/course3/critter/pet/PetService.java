package com.udacity.jdnd.course3.critter.pet;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(long id) {
        return petRepository.findById(id).get();
    }

}
