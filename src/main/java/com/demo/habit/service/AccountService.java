package com.demo.habit.service;

import com.demo.habit.model.Account;
import com.demo.habit.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<String> createAccount(Account account) {
        Optional<Account> accountOptional = accountRepository.findByEmail(account.getEmail());
        String accountId = UUID.randomUUID().toString();

        return accountOptional.map(
                    acc -> {
                        log.debug("Cannot create account {} as it already exist.", acc.getEmail());

                        return ResponseEntity.badRequest().body(
                                String.format("Account %s already exists.", acc.getId())
                        );
                    }
                ).orElseGet(
                    () -> {
                        log.debug("Account {} created successfully", account.getEmail());
                        account.setId(accountId);
                        accountRepository.save(account);

                        return ResponseEntity.ok("Account created successfully");
                }
        );
    }

    public ResponseEntity<Account> getAccount(String email) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);

        return accountOptional.map(ResponseEntity::ok).orElseGet(
                () -> {
                    log.debug("Cannot fetch account {} as it does not exist.", email);

                    return ResponseEntity.notFound().build();
                });
    }

    public ResponseEntity<String> updateAccount(String email) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);

        return accountOptional.map(account -> ResponseEntity.ok("Account updated successfully")).orElseGet(
                () -> {
                    log.debug("Cannot update account {} as it does not exist.", email);

                    return ResponseEntity.badRequest().body(String.format("Account email %s does not exist.", email));
                }
        );
    }

    public ResponseEntity<String> deleteAccount(String email) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);

        return accountOptional.map(
                    account -> {
                        log.debug("Account {} deleted successfully", email);
                        accountRepository.delete(account);

                        return ResponseEntity.ok("Account deleted successfully");
                }).orElseGet(
                    () -> {
                        log.debug("Cannot delete account {} as it does not exist.", email);

                        return ResponseEntity.badRequest().body(String.format("Account %s does not exist.", email));
                }
        );
    }

}
