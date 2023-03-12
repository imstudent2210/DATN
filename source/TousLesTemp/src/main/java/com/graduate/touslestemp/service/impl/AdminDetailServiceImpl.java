package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.exception.NotFoundAdminException;
import com.graduate.touslestemp.model.Admin;
import com.graduate.touslestemp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = this.adminRepository.findByUsername(username);
        if(admin == null){
            throw new NotFoundAdminException(admin.getUsername());
        }
    return  admin;
    }
}
