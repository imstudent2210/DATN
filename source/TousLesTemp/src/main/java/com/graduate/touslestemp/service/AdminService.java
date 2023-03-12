package com.graduate.touslestemp.service;

import com.graduate.touslestemp.model.AccountRole;
import com.graduate.touslestemp.model.Admin;

import java.util.Optional;
import java.util.Set;

public interface AdminService {

    public Admin createAdmin(Admin user, Set<AccountRole> userRoles) throws Exception;
   Admin findAdmin(String username);
}
