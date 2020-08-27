package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Schedule> getScheduleForCustomer(@PathVariable long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        List<Pet> pets = customer.getPets();

        return pets.stream()
                .map(Pet::getSchedules)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
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
