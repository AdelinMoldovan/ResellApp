package com.example.resell.service;


import com.example.resell.exception.CustomerNotFoundException;
import com.example.resell.exception.InvalidCustomerException;
import com.example.resell.exception.WrongDetailsException;

import com.example.resell.model.Customer;
import com.example.resell.repository.CustomerRepository;
import com.example.resell.validator.CustomerDetailsValidator;
import org.hibernate.usertype.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerDetailsValidator customerDetailsValidator;


    private CustomerService customerService;

    @Before
    public void setUp(){
        initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerDetailsValidator);
    }

    private Customer createValidCustomer(){
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("Adelin");
        customer.setLastName("Moldovan");
        customer.setEmail("moldovanadelin111@gmail.com");
        customer.setPassword("password");
        customer.setCustomerPhone("0755538466");
        return customer;
    }

    private Customer createInvalidCustomer(){
        Customer customer = new Customer();
        customer.setId(400l);
        customer.setFirstName("Bogdan");
        customer.setLastName("Titu");
        customer.setEmail("bogdantitu@gmail.com");
        customer.setPassword("password");
        customer.setCustomerPhone("0755538466");
        return customer;
    }

    public Customer createNonExistingCustomer() {
        Customer customer = new Customer();
        customer.setId(400L);
        customer.setFirstName("andrei");
        customer.setLastName("cozan");
        customer.setEmail("andreicozan@gmail.ro");
        customer.setPassword("Password1");
        customer.setCustomerPhone("0745382312");
        return customer;
    }

    @Test
    public void givenExistingCustomerId_whenFindById_thenFindCustomer(){

        Customer customer = createValidCustomer();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer foundCustomer = customerService.findById(customer.getId());

        assertNotNull(foundCustomer);
        assertEquals(1L, foundCustomer.getId());
    }

    @Test
    public void givenNonExistingCustomerId_whenFindById_thenThrowException(){
        Customer customer = createNonExistingCustomer();

        when(customerRepository.findById(400L)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.findById(customer.getId());
        });
    }

    @Test
    public void givenExistingEmail_whenFindByEmail_thenFindCustomer(){
        Customer customer = createValidCustomer();

        when(customerRepository.findByEmail("moldovanadelin111@gmail.com")).thenReturn(Optional.of(customer));
        Customer foundCustomer = customerService.findByEmail(customer.getEmail());

        assertNotNull(foundCustomer);
        assertEquals("moldovanadelin111@gmail.com", foundCustomer.getEmail());
    }

    @Test
    public void givenNonExistingEmail_whenFindByEmail_thenThrowException(){
        Customer customer = createNonExistingCustomer();

        when(customerRepository.findByEmail("adelin@gmail.com")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.findByEmail(customer.getEmail());
        });
    }
    @Test
    public void givenExistingFirstNameAndLastName_whenFindByFirstNameAndLastName_thenFindCustomer() {
        Customer customer = createValidCustomer();

        when(customerRepository.findByFirstNameAndLastName("Adelin", "Moldovan")).thenReturn(Optional.of(customer));
        Customer foundCustomer = customerService.findByFirstNameAndLastName("Adelin", "Moldovan");

        assertNotNull(foundCustomer);
        assertEquals("Adelin", foundCustomer.getFirstName());
        assertEquals("Moldovan", foundCustomer.getLastName());
    }

    @Test
    public void givenNonExistingFirstNameAndLastName_whenFindByFirstNameAndLastName_thenFindCustomer() {
        Customer customer = createNonExistingCustomer();

        when(customerRepository.findByFirstNameAndLastName("John", "Snow")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.findByFirstNameAndLastName(customer.getFirstName(), customer.getLastName());
        });
    }

    @Test
    public void givenValidCustomer_whenSaveCustomer_thenReturnSavedCustomer() {
        Customer customer = createValidCustomer();

        when(customerRepository.save(customer)).thenReturn(customer);
        Customer savedCustomer = customerService.addCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals("moldovanadelin111@gmail.com", savedCustomer.getEmail());
    }

    @Test
    public void givenInvalidCustomer_whenSaveCustomer_thenThrowException() {
        Customer customer = createInvalidCustomer();

        willThrow(new WrongDetailsException()).given(customerDetailsValidator).validateCustomerDetails(customer);

        assertThrows(InvalidCustomerException.class, () -> {
            customerService.addCustomer(customer);
        });
    }

    @Test
    public void givenExistingCustomer_whenUpdateCustomer_thenReturnUpdatedCustomer(){
        //Customer customer = createValidCustomer();
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("Adelin");
        customer.setLastName("Moldovan");
        customer.setEmail("moldovanadelin111@gmail.com");
        customer.setPassword("password");
        customer.setCustomerPhone("0755538466");

        Customer customerUpdate = new Customer();
        customerUpdate.setId(1L);
        customerUpdate.setEmail("moldovanadelin123@gmail.com");

        Customer customerToReturn = new Customer();

        customerToReturn.setId(1L);
        customerToReturn.setFirstName(customer.getFirstName());
        customerToReturn.setLastName(customer.getLastName());
        customerToReturn.setEmail(customer.getEmail());
        customerToReturn.setPassword(customer.getPassword());
        customerToReturn.setCustomerPhone(customer.getCustomerPhone());

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customerUpdate)).thenReturn(customerToReturn);

        Customer updatedCustomer = customerService.updateCustomer(customerUpdate);

        assertNotNull(updatedCustomer);
        assertEquals("moldovanadelin123@gmail.com", updatedCustomer.getEmail());
    }

    @Test
    public void givenNonExistingCustomer_whenUpdateCustomer_thenThrowException(){
        Customer customerUpdate = new Customer();
        customerUpdate.setId(400L);

        when(customerRepository.findById(400L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.updateCustomer(customerUpdate);
        });
    }

    @Test
    public void givenExistingAdmin_whenDeleteAdmin_thenSuccess(){
        Customer customer = createValidCustomer();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).deleteById(customer.getId());

        customerRepository.deleteById(customer.getId());
        then(customerRepository).should().deleteById(1L);
    }

    @Test
    public void givenNonExistingAdmin_whenDeleteAdmin_thenThrowException() {
        Customer customer = createInvalidCustomer();

        when(customerRepository.findById(400L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.deleteById(customer.getId());
        });
    }

}
