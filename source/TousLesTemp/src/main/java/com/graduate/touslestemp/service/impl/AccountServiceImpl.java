package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.exception.AdminAlreadyExistsException;
import com.graduate.touslestemp.model.AccountRole;
import com.graduate.touslestemp.model.Account;
import com.graduate.touslestemp.repository.AccountRepository;

import com.graduate.touslestemp.repository.RoleRepository;
import com.graduate.touslestemp.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Account createAccount(Account admin, Set<AccountRole> userRoles) throws Exception {
        Account local = this.accountRepository.findByUsername(admin.getUsername());
        if(local !=null){
            System.out.println("Admin has already");
            throw new AdminAlreadyExistsException(local.getUsername());
        }else{
            for(AccountRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            admin.getUserRole().addAll(userRoles);
            local = this.accountRepository.save(admin);

        }
        return local;
    }

    @Override
    public Account findAccount(String username) {
        return this.accountRepository.findByUsername(username);
    }
}
