package co.sofka.command.create;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.TransactionAccountDetail;
import co.sofka.command.dto.BankTransactionWithdrawFromATM;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.request.TokenInicilizer;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.config.TokenByDinHeaders;
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
public class TokenGenerateHandler {

    private static final Logger logger = LoggerFactory.getLogger(TokenGenerateHandler.class);


   private final TokenByDinHeaders utils;

    public ResponseMs<String> apply(RequestMs<TokenInicilizer> request) {

        ResponseMs<String> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();
        responseMs.setDinError(error);

        String encode = utils.encode(request.getDinBody().getTokenValue());

        responseMs.setDinBody(encode);

        return responseMs;
    }
}
