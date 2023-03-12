package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.exception.AdminAlreadyExistsException;
import com.graduate.touslestemp.model.AccountRole;
import com.graduate.touslestemp.model.Admin;
import com.graduate.touslestemp.repository.AdminRepository;
import com.graduate.touslestemp.repository.RoleRepository;
import com.graduate.touslestemp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin createAdmin(Admin admin, Set<AccountRole> userRoles) throws Exception {
        Admin local = this.adminRepository.findByUsername(admin.getUsername());
        if(local !=null){
            System.out.println("Admin has already");
            throw new AdminAlreadyExistsException(local.getUsername());
        }else{
            for(AccountRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            admin.getUserRole().addAll(userRoles);
            local = this.adminRepository.save(admin);

        }
        return local;
    }

    @Override
    public Admin findAdmin(String username) {
        return this.adminRepository.findByUsername(username);
    }
}
