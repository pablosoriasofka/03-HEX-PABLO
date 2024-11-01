package co.sofka.command.create;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.TransactionAccountDetail;
import co.sofka.command.dto.BankTransactionDepositSucursal;
import co.sofka.command.dto.BankTransactionWithdrawFromATM;
import co.sofka.crypto.Utils;
import co.sofka.gateway.ITransactionAccountDetailRepository;
import co.sofka.middleware.AccountNotExistException;
import co.sofka.middleware.AccountNotHaveBalanceException;
import co.sofka.usecase.IGetAccountByNumberService;
import co.sofka.usecase.ISaveAccountService;
import co.sofka.usecase.ISaveTransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class RegisterTransactionDepositSucursalHandler {

    private static final Logger logger = LoggerFactory.getLogger(RegisterTransactionDepositSucursalHandler.class);

    private final IGetAccountByNumberService service;

    private final ISaveTransactionService saveTransactionService;

    private final ISaveAccountService saveAccountService;

    private final ITransactionAccountDetailRepository transactionAccountDetailRepository;

   private final Utils utils;

    public Transaction apply(BankTransactionDepositSucursal bankTransactionWithdrawFromATM) {

        logger.info("Buscando Account por numero");
        String decode = utils.decode(bankTransactionWithdrawFromATM.getAccountNumberClient());
        logger.info("Buscando Account por numero: "+decode);

        Account account = service.findByNumber(decode);

        if (account==null){
            throw new AccountNotExistException("La cuenta no existe");
        }


        Transaction transaction = new Transaction();
        transaction.setTransactionCost(new BigDecimal(0));
        transaction.setAmountTransaction(bankTransactionWithdrawFromATM.getAmount());
        transaction.setTimeStamp(LocalDateTime.now());
        transaction.setTypeTransaction("Deposito Sucursal");

        Transaction save = saveTransactionService.save(transaction);


        TransactionAccountDetail transactionAccountDetail = new TransactionAccountDetail();
        transactionAccountDetail.setAccount(account);
        transactionAccountDetail.setTransaction(save);
        transactionAccountDetail.setTransactionRole("CREDIT");

        transactionAccountDetailRepository.save(transactionAccountDetail);


        account.setAmount(account.getAmount().add(bankTransactionWithdrawFromATM.getAmount()));
        saveAccountService.save(account);


        return save;
    }
}
