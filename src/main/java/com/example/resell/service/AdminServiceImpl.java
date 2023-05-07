package com.example.resell.service;


import com.example.resell.exception.AdminNotFoundException;
import com.example.resell.model.Admin;
import com.example.resell.repository.AdminRepository;
import com.example.resell.validator.AdminDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements UserDetailsService, AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, AdminDetailsValidator adminDetailsValidator) {
        this.adminRepository = adminRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = null;
        try {
            admin = findByUsername(username);
        } catch (AdminNotFoundException e) {
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }
        return (UserDetails) admin;
    }

    @Override
    public Admin findById(long id) throws AdminNotFoundException {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty()) {
            throw new AdminNotFoundException("Admin with id " + id + " not found");
        }
        return admin.get();
    }

    @Override
    public Admin findByUsername(String username) throws AdminNotFoundException {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isEmpty()) {
            throw new AdminNotFoundException("Admin with username " + username + " not found");
        }
        return admin.get();
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin addAdmin(Admin admin) {
        Optional<Admin> foundAdmin = adminRepository.findByUsername(admin.getUsername());
        if (foundAdmin.isEmpty()) {
            admin.setPassword(passwordEncoder.encode(admin.getPassword())); // Encode the password
            adminRepository.save(admin);
        } else {
            throw new IllegalArgumentException("Admin with this username already exists");
        }
        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
        Optional<Admin> adminToUpdate = adminRepository.findById(admin.getId());
        if (adminToUpdate.isPresent()) {
            adminToUpdate.get().setUsername(admin.getUsername());
            adminToUpdate.get().setPassword(admin.getPassword());
            adminRepository.save(adminToUpdate.get());
        } else {
            throw new AdminNotFoundException("Admin to update not found");
        }
        return adminToUpdate.get();
    }

    @Override
    public void deleteById(long adminId) throws AdminNotFoundException {
        Optional<Admin> adminToDelete = adminRepository.findById(adminId);
        if (adminToDelete.isPresent()) {
            adminRepository.deleteById(adminId);
        } else {
            throw new AdminNotFoundException("Admin to delete not found");
        }
    }


}
