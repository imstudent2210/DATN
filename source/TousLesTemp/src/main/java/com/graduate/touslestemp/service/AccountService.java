package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.entity.AccountRole;
import com.graduate.touslestemp.domain.entity.Account;

import java.util.Set;

public interface AccountService {

    public Account createAccount(Account account, Set<AccountRole> userRoles) throws Exception;
   Account findAccount(String username) throws Exception;
}
