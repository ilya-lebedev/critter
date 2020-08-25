package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).get();
    }

    public Customer getCustomerByPetId(long petId) {
        return customerRepository.findCustomerByPetsId(petId);
    }

    public void addPetToCustomer(Customer customer, Pet pet) {
        List<Pet> pets = customer.getPets();

        if (pets == null) {
            pets = new ArrayList<>();
        }

        pets.add(pet);
        customer.setPets(pets);
        customerRepository.save(customer);
    }

}
