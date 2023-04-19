package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.config.authenticate.Account;
import com.graduate.touslestemp.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByUsername(username);
        if (account == null) {
            throw new RequestException("Not found this account!");
        }
        return account;
    }
}
