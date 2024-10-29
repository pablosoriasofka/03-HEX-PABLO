package co.sofka.usecase;



import co.sofka.BankTransaction;
import co.sofka.gateway.IAccountRepository;
import co.sofka.gateway.IBankRepository;
import co.sofka.gateway.IBankTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllBankTransactionUseCase {

    private static final Logger logger = LoggerFactory.getLogger(GetAllBankTransactionUseCase.class);

    private final IBankTransactionRepository repository;

    private final IAccountRepository accountRepository;

    private final IBankRepository bankRepository;

    public GetAllBankTransactionUseCase(IBankTransactionRepository repository, IAccountRepository accountRepository, IBankRepository bankRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    public List<BankTransaction> getAll() {
        return repository.getAll();
    }


}
