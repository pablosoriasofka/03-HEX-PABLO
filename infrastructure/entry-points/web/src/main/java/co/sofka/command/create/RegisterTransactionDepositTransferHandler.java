package co.sofka.command.create;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.TransactionAccountDetail;
import co.sofka.command.dto.BankTransactionDepositSucursal;
import co.sofka.command.dto.BankTransactionDepositTransfer;
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
public class RegisterTransactionDepositTransferHandler {

    private static final Logger logger = LoggerFactory.getLogger(RegisterTransactionDepositTransferHandler.class);

    private final IGetAccountByNumberService service;

    private final ISaveTransactionService saveTransactionService;

    private final ISaveAccountService saveAccountService;

    private final ITransactionAccountDetailRepository transactionAccountDetailRepository;

   private final Utils utils;

    public Transaction apply(BankTransactionDepositTransfer item) {

        logger.info("Buscando Account por numero");
        String decode = utils.decode(item.getAccountNumberSender());
        logger.info("Buscando Account por numero: "+decode);

        Account accountSend = service.findByNumber(decode);

        if (accountSend==null){
            throw new AccountNotExistException("La cuenta Origen no existe");
        }

        String decodeReciver = utils.decode(item.getAccountNumberReceiver());
        logger.info("Buscando Account por numero: "+decodeReciver);
        Account accountReciver = service.findByNumber(decodeReciver);

        if (accountReciver==null){
            throw new AccountNotExistException("La cuenta Destino no existe");
        }

        if (accountSend.getAmount().subtract(item.getAmount().add(new BigDecimal(1.5))).intValue() < 0) {
            throw new AccountNotHaveBalanceException("No tiene saldo suficiente.");
        }

        logger.info("Guardando Transaccion {}",accountReciver.getCustomer().getId());

        Transaction transaction = new Transaction();
        transaction.setTransactionCost(new BigDecimal(1.5));
        transaction.setAmountTransaction(item.getAmount());
        transaction.setTimeStamp(LocalDateTime.now());
        transaction.setTypeTransaction("Transferencia");

        //Transaction save = saveTransactionService.save(transaction);


        TransactionAccountDetail transactionAccountDetail = new TransactionAccountDetail();
        transactionAccountDetail.setAccount(accountSend);
        transactionAccountDetail.setTransaction(transaction);
        transactionAccountDetail.setTransactionRole("Payroll");

        transactionAccountDetailRepository.save(transactionAccountDetail);


        TransactionAccountDetail transactionAccountDetailCredit = new TransactionAccountDetail();
        transactionAccountDetailCredit.setAccount(accountReciver);
        transactionAccountDetailCredit.setTransaction(transaction);
        transactionAccountDetailCredit.setTransactionRole("Supplier");

        transactionAccountDetailRepository.save(transactionAccountDetailCredit);

        accountReciver.setAmount(accountReciver.getAmount().add(item.getAmount()));
        saveAccountService.save(accountReciver);

        accountSend.setAmount(accountSend.getAmount().subtract(item.getAmount().add(new BigDecimal(1.5))));
        saveAccountService.save(accountSend);




        return transaction;
    }
}
