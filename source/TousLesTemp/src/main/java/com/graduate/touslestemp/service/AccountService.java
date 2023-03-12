package com.graduate.touslestemp.service;

import com.graduate.touslestemp.model.AccountRole;
import com.graduate.touslestemp.model.Account;

import java.util.Set;

public interface AccountService {

    public Account createAccount(Account user, Set<AccountRole> userRoles) throws Exception;
   Account findAccount(String username);
}
