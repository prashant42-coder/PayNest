package com.example.PayNest.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PayNest.model.Account;
import com.example.PayNest.model.Transaction;
import com.example.PayNest.repository.AccountRepository;
import com.example.PayNest.repository.TransactionRepository;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account findAccountByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Account not found!"));
    }

    public Account registerAccount(String username, String password) {
        if (accountRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public void deposit(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction(
            "Deposit",
            amount,
            LocalDateTime.now(),
            account
        );
        transactionRepository.save(transaction);
    }

    public void withdraw(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction(
            "Withdrawal",
            amount,
            LocalDateTime.now(),
            account
        );
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionHistory(Account account) {
        return transactionRepository.findByAccountId(account.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = findAccountByUsername(username);
        return new org.springframework.security.core.userdetails.User(
            account.getUsername(),
            account.getPassword(),
            authorities()
        );
    }

    public Collection<? extends GrantedAuthority> authorities() {
        return java.util.Arrays.asList(new SimpleGrantedAuthority("User"));

    }
    public void transferAmount(Account fromAccount, String toUsername,BigDecimal amount){
        if(fromAccount.getBalance().compareTo(amount)<0){
         throw new RuntimeException("Insufficient funds");
        }
        Account toAccount = accountRepository.findByUsername(toUsername)
        .orElseThrow(() -> new RuntimeException("Recipient account not found "));

    
    // deduct 
    fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
    accountRepository.save(fromAccount);
    // Add
    toAccount.setBalance(toAccount.getBalance().add(amount));
    accountRepository.save(toAccount);

    //craete transction records
    Transaction debitTransaction = new Transaction(
        "Transfer Out to " + toAccount.getUsername(),
        amount,
        LocalDateTime.now(),
        fromAccount
    );
    transactionRepository.save(debitTransaction);


    //craete transction records
    Transaction creditTransaction = new Transaction(
        "Transfer Out to " + fromAccount.getUsername(),
        amount,
        LocalDateTime.now(),
        fromAccount
    );
    transactionRepository.save(creditTransaction);
}

}