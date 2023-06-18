package com.uep.wap.service;

import com.uep.wap.model.Account;
import com.uep.wap.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> findByUsername(String username){
        return accountRepository.findByUsername(username);
    }
}
