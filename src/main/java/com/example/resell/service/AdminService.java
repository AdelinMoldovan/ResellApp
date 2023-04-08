package com.example.resell.service;


import com.example.resell.model.Admin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminService {

    Admin findById(int id); // throws AdminNotFoundException;
    Admin findByEmail(String email); //throws AdminNotFoundException;
    Admin findByFirstNameAndLastName(String firstName, String lastName); // throws AdminNotFoundException;
    Admin findByEmailAndPassword(String email, String password); //throws AdminNotFoundException;

    List<Admin> findAll();

    Admin addAdmin(Admin admin); // throws InvalidAdminException;
    Admin updateAdmin(Admin admin); // throws InvalidAdminException, AdminNotFoundException;
    Admin updateProductsList(Admin admin); //throws AdminNotFoundException;

    void deleteById(long adminId); // throws AdminNotFoundException;



}
