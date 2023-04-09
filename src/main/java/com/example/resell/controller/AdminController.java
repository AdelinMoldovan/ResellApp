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

    @GetMapping("/findById")
    public ResponseEntity findAdminById(@RequestParam long id){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findById(id));

    }

    @GetMapping("/findByEmail")
    public ResponseEntity findAdminByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findByEmail(email));
    }

    @GetMapping("/findByFirstNameAndLastName")
    public ResponseEntity findAdminByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findByFirstNameAndLastName(firstName, lastName));
    }

    @GetMapping("/findByEmailAndPassword")
    public ResponseEntity findAdminByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findByEmailAndPassword(email, password));
    }

    @GetMapping()
    public ResponseEntity findAllAdmins() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity addAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.addAdmin(admin));
    }

    @PutMapping("/update")
    public ResponseEntity updateAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateAdmin(admin));
    }

    @DeleteMapping("/delete")
    public void deleteAdmin(@RequestParam long id) {
        adminService.deleteById(id);
    }
}
