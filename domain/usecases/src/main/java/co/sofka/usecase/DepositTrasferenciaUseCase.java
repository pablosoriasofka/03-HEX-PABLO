package co.sofka.usecase;



import co.sofka.Account;
import co.sofka.Bank;
import co.sofka.BankTransaction;
import co.sofka.TypeTransaction;
import co.sofka.dto.*;
import co.sofka.gateway.IAccountRepository;
import co.sofka.gateway.IBankRepository;
import co.sofka.gateway.IBankTransactionRepository;
import co.sofka.middleware.AccountNotExistException;
import co.sofka.middleware.AccountNotHaveBalanceException;
import co.sofka.middleware.TypeOfBuysNotExistException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public class DepositTrasferenciaUseCase {

    private static final Logger logger = LoggerFactory.getLogger(DepositTrasferenciaUseCase.class);

    private final IBankTransactionRepository repository;

    private final IAccountRepository accountRepository;

    private final IBankRepository bankRepository;

    public DepositTrasferenciaUseCase(IBankTransactionRepository repository, IAccountRepository accountRepository, IBankRepository bankRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    @Transactional
    public BankTransaction depositTrasferencia(BankTransactionDepositTransfer item) {
        Account client = accountRepository.findByNumberAndPing(item.getAccountNumberSender(), item.getPin());
        if (client == null) {
            throw new AccountNotExistException("La cuenta del cliente no es valida.");
        }

        Account store = accountRepository.findByNumber(item.getAccountNumberReceiver());
        if (client == null) {
            throw new AccountNotExistException("La cuenta del cliente no existe.");
        }

        if (client.getBalance()-(item.getAmount()) < 0) {
            throw new AccountNotHaveBalanceException("No tiene saldo suficiente.");
        }

        client.setBalance(client.getBalance() - item.getAmount());
        accountRepository.save(client);

        store.setBalance(client.getBalance() + (item.getAmount()-1.5));
        accountRepository.save(store);

        Bank bankById = bankRepository.findById(1L);
        bankById.setBalance(bankById.getBalance()+1.5);
        bankRepository.save(bankById);

        BankTransaction bankTransaction1 = new BankTransaction();
        bankTransaction1.setOriginAccount(client);
        bankTransaction1.setDestinationAccount(store);
        bankTransaction1.setAmount(item.getAmount());

        TypeTransaction typeTransaction = new TypeTransaction();
        typeTransaction.setId("1");

        bankTransaction1.setTypeTransaction(typeTransaction);
        bankTransaction1.setCreatedAt(new Date(System.currentTimeMillis()));
        return repository.save(bankTransaction1);
    }
}
