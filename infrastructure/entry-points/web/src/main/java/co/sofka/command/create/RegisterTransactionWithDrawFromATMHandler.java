package co.sofka.command.create;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.TransactionAccountDetail;
import co.sofka.command.dto.BankTransactionWithdrawFromATM;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.crypto.Utils;
import co.sofka.gateway.ITransactionAccountDetailRepository;
import co.sofka.middleware.AccountNotExistException;
import co.sofka.middleware.AccountNotHaveBalanceException;
import co.sofka.middleware.ErrorDecryptingDataException;
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
public class RegisterTransactionWithDrawFromATMHandler {

    private static final Logger logger = LoggerFactory.getLogger(RegisterTransactionWithDrawFromATMHandler.class);

    private final IGetAccountByNumberService service;

    private final ISaveTransactionService saveTransactionService;

    private final ISaveAccountService saveAccountService;

    private final ITransactionAccountDetailRepository transactionAccountDetailRepository;

   private final Utils utils;

    public ResponseMs<Transaction> withdrawFromATM(RequestMs<BankTransactionWithdrawFromATM> bankTransactionWithdrawFromATM) {

        ResponseMs<Transaction> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(bankTransactionWithdrawFromATM.getDinHeader());
        DinError error = new DinError();

        logger.info("Buscando Account por numero");
        String decode = "";
        try {
            decode = utils.decode(bankTransactionWithdrawFromATM.getDinBody().getAccountNumber());
        } catch (Exception e) {
            throw new ErrorDecryptingDataException("Error al desencriptar el numero de cuenta.", bankTransactionWithdrawFromATM.getDinHeader(),1001);
        }
        logger.info("Buscando Account por numero: "+decode);

        Account account = service.findByNumber(decode);

        if (account==null){
            throw new AccountNotExistException("La cuenta no existe", bankTransactionWithdrawFromATM.getDinHeader(),1002);
        }


        if (account.getAmount().subtract(bankTransactionWithdrawFromATM.getDinBody().getAmount().add(new BigDecimal(1))).intValue() < 0) {
            throw new AccountNotHaveBalanceException("No tiene saldo suficiente.", bankTransactionWithdrawFromATM.getDinHeader(),1003);
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionCost(new BigDecimal(1));
        transaction.setAmountTransaction(bankTransactionWithdrawFromATM.getDinBody().getAmount());
        transaction.setTimeStamp(LocalDateTime.now());
        transaction.setTypeTransaction("ATM");

        //Transaction save = saveTransactionService.save(transaction);


        TransactionAccountDetail transactionAccountDetail = new TransactionAccountDetail();
        transactionAccountDetail.setAccount(account);
        transactionAccountDetail.setTransaction(transaction);
        transactionAccountDetail.setTransactionRole("Supplier");

        transactionAccountDetailRepository.save(transactionAccountDetail);


        account.setAmount(account.getAmount().subtract(bankTransactionWithdrawFromATM.getDinBody().getAmount().add(new BigDecimal(1))));
        saveAccountService.save(account);

        responseMs.setDinBody(transaction);

        return responseMs;
    }
}
