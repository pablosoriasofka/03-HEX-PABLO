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

@Service
public class WithdrawFromATMUseCase {

    private static final Logger logger = LoggerFactory.getLogger(WithdrawFromATMUseCase.class);

    private final IBankTransactionRepository repository;

    private final IAccountRepository accountRepository;

    private final IBankRepository bankRepository;

    public WithdrawFromATMUseCase(IBankTransactionRepository repository, IAccountRepository accountRepository, IBankRepository bankRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }


    @Transactional
    public BankTransaction withdrawFromATM(BankTransactionWithdrawFromATM bankTransaction) {
        logger.info("Retiro de cajero: {} {}", bankTransaction.getAccountNumber(), bankTransaction.getPin());
        Account byNumber = accountRepository.findByNumberAndPing(bankTransaction.getAccountNumber(), bankTransaction.getPin());
        if (byNumber == null) {
            throw new AccountNotExistException("La cuenta no existe");
        }

        logger.info("Cuenta encontrada: {} {} {}", byNumber,byNumber.getBalance(),bankTransaction.getAmount()+1);

        if (byNumber.getBalance()-(bankTransaction.getAmount()+1) < 0) {
            throw new AccountNotHaveBalanceException("No tiene saldo suficiente.");
        }

        byNumber.setBalance(byNumber.getBalance() - (bankTransaction.getAmount()+1));
        accountRepository.save(byNumber);

        Bank bankById = bankRepository.findById(1L);
        bankById.setBalance(bankById.getBalance()+1);
        bankRepository.save(bankById);

        BankTransaction bankTransaction1 = new BankTransaction();
        bankTransaction1.setOriginAccount(byNumber);
        bankTransaction1.setDestinationAccount(byNumber);
        bankTransaction1.setAmount(bankTransaction.getAmount());

        TypeTransaction typeTransaction = new TypeTransaction();
        typeTransaction.setId("3");

        bankTransaction1.setTypeTransaction(typeTransaction);
        bankTransaction1.setCreatedAt(new Date(System.currentTimeMillis()));
        return repository.save(bankTransaction1);
    }


}
