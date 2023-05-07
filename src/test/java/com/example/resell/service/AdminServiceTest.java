package com.example.resell.service;

import com.example.resell.exception.AdminNotFoundException;
import com.example.resell.exception.InvalidAdminException;
import com.example.resell.exception.WrongDetailsException;
import com.example.resell.model.Admin;
import com.example.resell.repository.AdminRepository;
import com.example.resell.validator.AdminDetailsValidator;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.TestInstances;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private AdminDetailsValidator adminDetailsValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    //@InjectMocks
    private AdminService adminService;

    @Before
    public void setUp(){
        initMocks(this);
        adminService = new AdminServiceImpl(adminRepository, adminDetailsValidator);
    }



    private Admin createValidAdmin(){
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("admin");
        return admin;
    }

    private Admin createNonExistingAdmin(){
        Admin admin = new Admin();
        admin.setId(400L);
        admin.setUsername("adelin");
        admin.setPassword("adelin");
        return admin;
    }

    @Test
    public void givenExistingAdminId_whenFindById_thenFindAdmin(){
        Admin admin = createValidAdmin();

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        Admin foundAdmin = adminService.findById(admin.getId());

        assertNotNull(foundAdmin);
        assertEquals(1L, foundAdmin.getId());
    }

    @Test
    public void givenNonExistingAdminId_whenFindById_thenThrowException(){
        Admin admin = createNonExistingAdmin();

        when(adminRepository.findById(400L)).thenReturn(Optional.empty());
        assertThrows(AdminNotFoundException.class, ()-> {
           adminService.findById(admin.getId());
        });
    }

    @Test
    public void givenExistingUsername_whenFindByUsername_thenFindAdmin(){
        Admin admin = createValidAdmin();

        when(adminRepository.findByUsername("admin")).thenReturn(Optional.of(admin));
        Admin foundAdmin = adminService.findByUsername("admin");

        assertNotNull(foundAdmin);
        assertEquals("admin", foundAdmin.getUsername());
    }

    @Test
    public void givenNonExistingUsername_whenFindByUsername_thenThrowException(){
        Admin admin = createNonExistingAdmin();

        when(adminRepository.findByUsername("adelin")).thenReturn(Optional.empty());

        assertThrows(AdminNotFoundException.class, ()->{
           adminService.findByUsername(admin.getUsername());
        });
    }

    @Test
    public void givenValidAdmin_whenSaveAdmin_thenReturnSavedAdmin(){
        //Admin admin = createValidAdmin();
        Admin admin = new Admin();
        admin.setId(400L);
        admin.setUsername("admin");
        admin.setPassword("admin");

        when(adminRepository.findByUsername(admin.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(admin.getPassword())).thenReturn("encodedPassword");

        Admin savedAdmin = adminService.addAdmin(admin);

        assertNotNull(savedAdmin);
        assertEquals(admin.getUsername(), savedAdmin.getUsername());
        assertEquals("encodedPassword", savedAdmin.getPassword());

        verify(adminRepository, times(1)).findByUsername(admin.getUsername());
        verify(adminRepository, times(1)).save(admin);
        verifyNoMoreInteractions(adminRepository);
        verify(passwordEncoder, times(1)).encode(admin.getPassword());
        verifyNoMoreInteractions(passwordEncoder);

    }

    @Test
    public void testAddAdminWithExistingUsername() {
        // Arrange
        Admin admin = new Admin();
        admin.setUsername("admin1");
        admin.setPassword("password1");

        when(adminRepository.findByUsername(admin.getUsername())).thenReturn(Optional.of(admin));

        // Act and assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> adminService.addAdmin(admin));
        assertEquals("Admin with this username already exists", exception.getMessage());

        verify(adminRepository, times(1)).findByUsername(admin.getUsername());
        verifyNoMoreInteractions(adminRepository);
        verifyNoInteractions(passwordEncoder);
    }

    @Test
    public void givenExistingAdmin_whenUpdateAdmin_thenReturnUpdatedAdmin(){

        Admin admin = createValidAdmin();
        Admin adminUpdate = new Admin();

        adminUpdate.setId(1L);
        adminUpdate.setUsername("adelin123");

        Admin adminToReturn = new Admin();
        adminToReturn.setId(1L);
        adminToReturn.setUsername(admin.getUsername());
        adminToReturn.setUsername(admin.getPassword());

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(adminUpdate)).thenReturn(adminToReturn);

        Admin updatedAdmin = adminService.updateAdmin(adminUpdate);

        assertNotNull(updatedAdmin);
        assertEquals("adelin123", updatedAdmin.getUsername());

    }

    @Test
    public void givenNotExistingAdmin_whenUpdateAdmin_thenThrowException(){
        Admin adminUpdate = new Admin();
        adminUpdate.setId(400L);

        when(adminRepository.findById(400L)).thenReturn(Optional.empty());
        assertThrows(AdminNotFoundException.class, ()-> {
           adminService.updateAdmin(adminUpdate);
        });
    }

    @Test
    public void givenExistingAdmin_whenDeleteAdmin_thenSuccess(){
        Admin admin = createValidAdmin();

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        doNothing().when(adminRepository).deleteById(admin.getId());

        adminService.deleteById(admin.getId());
        then(adminRepository).should().deleteById(1L);
    }

    @Test
    public void givenNonExistingAdmin_whenDeleteAdmin_thenThrowException(){
        Admin admin = createNonExistingAdmin();

        when(adminRepository.findById(400L)).thenReturn(Optional.empty());

        assertThrows(AdminNotFoundException.class, () -> {
            adminService.deleteById(admin.getId());
        });
    }

}
