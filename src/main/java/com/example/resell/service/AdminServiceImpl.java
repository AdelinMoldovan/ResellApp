package com.example.resell.service;


import com.example.resell.exception.AdminNotFoundException;
import com.example.resell.exception.InvalidAdminException;
import com.example.resell.exception.WrongDetailsException;
import com.example.resell.model.Admin;
import com.example.resell.model.AppPersonRole;
import com.example.resell.model.Person;
import com.example.resell.repository.AdminRepository;
import com.example.resell.validator.AdminDetailsValidator;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminDetailsValidator adminDetailsValidator;

    @Autowired
    private PersonService personService;

    public AdminServiceImpl(AdminRepository adminRepository, AdminDetailsValidator adminDetailsValidator, PersonService personService) {
        this.adminRepository = adminRepository;
        this.adminDetailsValidator = adminDetailsValidator;
        this.personService = personService;
    }

    @Override
    public Admin findById(long id) throws AdminNotFoundException{
        Optional<Admin> admin = adminRepository.findById(id);
        if (!admin.isPresent()) {
            throw new AdminNotFoundException("Admin with id " + id + " not found");
        }
        return admin.get();
    }

    @Override
    public Admin findByEmail(String email) throws AdminNotFoundException{
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (!admin.isPresent()) {
            throw new AdminNotFoundException("Admin with email " + email + " not found");
        }
        return admin.get();
    }

    @Override
    public Admin findByFirstNameAndLastName(String firstName, String lastName) throws  AdminNotFoundException{
        Optional<Admin> admin = adminRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!admin.isPresent()) {
            throw new AdminNotFoundException("Admin with first name " + lastName + " and last name " + lastName + " not found");
        }
        return admin.get();
    }

    @Override
    public Admin findByEmailAndPassword(String email, String password) throws AdminNotFoundException{
        Optional<Admin> admin = adminRepository.findByEmailAndPassword(email, password);
        if (!admin.isPresent()) {
            throw new AdminNotFoundException("Admin with email " + email + " and password " + password + " not found");
        }
        return admin.get();
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin addAdmin(Admin admin) throws InvalidAdminException{
        try {
            adminDetailsValidator.validateAdminDetails(admin);
            Optional<Admin> foundAdmin = adminRepository.findByEmail(admin.getEmail());
            if (!foundAdmin.isPresent()) {
                Person person = new Person(admin.getId(), admin.getEmail(), admin.getPassword(), AppPersonRole.ADMIN);
                personService.addPerson(person);
                admin.setId(person.getId());
                adminRepository.save(admin);
            } else {
                throw new InvalidAdminException("Admin with this email already exists");
            }
        } catch (WrongDetailsException exp) {
            throw new InvalidAdminException(exp.getMessage());
        }
        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        Optional<Admin> adminToUpdate = adminRepository.findById(admin.getId());
        if (adminToUpdate.isPresent()) {
            Person userToUpdate = new Person(adminToUpdate.get().getId(), adminToUpdate.get().getEmail(),
                    adminToUpdate.get().getPassword(), AppPersonRole.ADMIN);
            Person finalPerson = createPerson(admin, userToUpdate);
            Admin finalAdmin = createAdmin(admin, adminToUpdate.get());
            try {
                adminDetailsValidator.validateAdminDetails(finalAdmin);
            } catch (WrongDetailsException exp) {
                throw new InvalidAdminException(exp.getMessage());
            }
            adminRepository.save(finalAdmin);
            personService.addPerson(finalPerson);
        } else {
            throw new AdminNotFoundException("Admin to update not found");
        }
        return adminToUpdate.get();
    }


    @Override
    public Admin updateProductsList(Admin admin) throws AdminNotFoundException{
        if (!adminRepository.findById(admin.getId()).isPresent()) {
            throw new AdminNotFoundException("Admin with id " + admin.getId() + " not found");
        }
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public void deleteById(long adminId) throws AdminNotFoundException{
        Optional<Admin> adminToDelete = adminRepository.findById(adminId);
        if (adminToDelete.isPresent()) {
            personService.deleteById(adminId);
            adminRepository.deleteById(adminId);
        } else {
            throw new AdminNotFoundException("Admin to delete not found");
        }
    }

    private Person createPerson(Admin admin, Person personToUpdate) {
        if (admin.getEmail() != null) {
            personToUpdate.setEmail(admin.getEmail());
        }
        if (admin.getPassword() != null) {
            personToUpdate.setPassword(admin.getPassword());
        }
        return personToUpdate;
    }

    private Admin createAdmin(Admin admin, Admin adminToUpdate) {
        if (admin.getFirstName() != null) {
            adminToUpdate.setFirstName(admin.getFirstName());
        }
        if (admin.getLastName() != null) {
            adminToUpdate.setLastName(admin.getLastName());
        }
        if (admin.getEmail() != null) {
            adminToUpdate.setEmail(admin.getEmail());
        }
        if (admin.getPassword() != null) {
            adminToUpdate.setPassword(admin.getPassword());
        }
        if (admin.getPhoneNumber() != null) {
            adminToUpdate.setPhoneNumber(admin.getPhoneNumber());
        }
        return adminToUpdate;
    }
}
