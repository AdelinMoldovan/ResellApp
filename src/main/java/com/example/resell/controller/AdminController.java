package com.example.resell.controller;


import com.example.resell.model.Admin;
import com.example.resell.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{id}")
    public ResponseEntity findAdminById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findById(id));
    }


    @GetMapping("/username/{username}")
    public ResponseEntity findAdminByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findByUsername(username));
    }

    @GetMapping()
    public ResponseEntity findAllAdmins() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity addAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.addAdmin(admin));
    }

    @PutMapping("/update")
    public ResponseEntity updateAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateAdmin(admin));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable long id) {
        adminService.deleteById(id);
    }
}
