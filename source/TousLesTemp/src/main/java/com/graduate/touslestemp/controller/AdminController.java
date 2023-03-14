package com.graduate.touslestemp.controller;
import com.graduate.touslestemp.exception.NotFoundAdminException;
import com.graduate.touslestemp.model.Role;
import com.graduate.touslestemp.model.AccountRole;
import com.graduate.touslestemp.model.Account;
import com.graduate.touslestemp.repository.AccountRepository;
import com.graduate.touslestemp.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/register")
public class AdminController {
    @Autowired
    private AccountService accountService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/admin")
    public Account createAdmin(@RequestBody @Valid Account admin) throws Exception {
        Set<AccountRole> roles = new HashSet<>();

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Role role = new Role();
        role.setId(1L);
        role.setName("Admin");

        AccountRole adminRole = new AccountRole();
        adminRole.setRole(role);
        adminRole.setAccount(admin);

        roles.add(adminRole);

        return this.accountService.createAccount(admin, roles);
    }

    @PostMapping("/manager")
    public Account createManager(@RequestBody @Valid Account manager) throws Exception {
        Set<AccountRole> roles = new HashSet<>();

        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        Role role = new Role();
        role.setId(2L);
        role.setName("Manager");

        AccountRole managerRole = new AccountRole();
        managerRole.setRole(role);
        managerRole.setAccount(manager);

        roles.add(managerRole);

        return this.accountService.createAccount(manager, roles);
    }

    @GetMapping("/get")
    List<Account> getUser() {
        return accountRepository.findAll();
    }

    //    @GetMapping("/get/{username}")
//    Admin getUserByName(@PathVariable("username") String username){
//        return this.adminService.findAdmin(username);
//
//    }
//    @GetMapping("/get/{username}")
//    Account getUserByName(@PathVariable("username") String username) {
//
//             if(accountRepository.findByUsername(username)!=null){
//                 return accountRepository.findByUsername(username);
//             }else{
//                 throw new NotFoundAdminException(username);
//             }
//    }
//

}
