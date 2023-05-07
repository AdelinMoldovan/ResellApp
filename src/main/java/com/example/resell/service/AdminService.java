package com.example.resell.service;


import com.example.resell.exception.AdminNotFoundException;
import com.example.resell.model.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AdminService extends UserDetailsService {

    Admin findById(long id) throws AdminNotFoundException;

    Admin findByUsername(String username) throws AdminNotFoundException;

    List<Admin> findAll();

    Admin addAdmin(Admin admin);

    Admin updateAdmin(Admin admin) throws AdminNotFoundException;

    void deleteById(long adminId) throws AdminNotFoundException;
}
