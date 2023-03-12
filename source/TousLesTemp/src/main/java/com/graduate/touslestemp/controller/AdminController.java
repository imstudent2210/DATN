package com.graduate.touslestemp.controller;
import com.graduate.touslestemp.exception.NotFoundAdminException;
import com.graduate.touslestemp.model.Admin;
import com.graduate.touslestemp.model.Role;
import com.graduate.touslestemp.model.AccountRole;
import com.graduate.touslestemp.repository.AdminRepository;
import com.graduate.touslestemp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@RestController
@RequestMapping("/account")
public class AdminController {
    @Autowired
    private AdminService adminService;

        private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/admin")
    public Admin createAdmin(@RequestBody Admin admin) throws Exception{
        Set<AccountRole> roles = new HashSet<>();

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Role role = new Role();
        role.setRoleID(1L);
        role.setRoleName("Admin");

        AccountRole adminRole = new AccountRole();
        adminRole.setRole(role);
        adminRole.setAdmin(admin);

        roles.add(adminRole);

        return this.adminService.createAdmin(admin,roles);
    }

    @GetMapping("/get")
    List<Admin> getUser(){
        return adminRepository.findAll() ;
    }
    @GetMapping("/get/{username}")
    Admin getUserByName(@PathVariable("username") String username){
        return this.adminService.findAdmin(username);

    }
//    @GetMapping("/get/{username}")
//    Admin getUserByName(@PathVariable("username") String username){
//        return this.adminService.findAdmin(username)
//                .orElseThrow(() -> new NotFoundAdminException(username));
//    }

//    @GetMapping("/get/{username}")
//    Admin getUserByName(@PathVariable("username") String username){
//        return adminRepository.findByUsername(username)
//                .orElseThrow(() -> new NotFoundAdminException(username));
//    }

}
