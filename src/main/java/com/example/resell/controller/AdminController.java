package com.example.resell.controller;


import com.example.resell.model.Admin;
import com.example.resell.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Returns an admin by id.
     * @param id
     * @return DataResponse(status, message, admin).
     */
    @GetMapping("/admin/id")
    public ResponseEntity findAdminById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findById(id));
    }

    /**
     * Returns an admin by username.
     * @param username
     * @return DataResponse(status, message, admin).
     */
    @GetMapping("/admin/username")
    public ResponseEntity findAdminByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findByUsername(username));
    }

    /**
     * Returns a list of all admins from DB.
     * @return DataResponse(status, message, list of admins).
     */
    @GetMapping("/admin/all")
    public ResponseEntity findAllAdmins() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
    }

    /**
     * Creates an admin and saves it in the DB.
     * @param admin
     * @return DataResponse (status, message).
     */
    @PostMapping("/admin/add")
    public ResponseEntity addAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.addAdmin(admin));
    }

    /**
     * Updates an existing admin from DB.
     * @param admin
     * @return DataResponse (status, message).
     */
    @PutMapping("/admin/update")
    public ResponseEntity updateAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateAdmin(admin));
    }

    /**
     *  Deletes an admin from DB.
     * @param id
     * @return DataResponse (status, message).
     */
    @DeleteMapping("/admin/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable long id) {
        adminService.deleteById(id);
    }
}
