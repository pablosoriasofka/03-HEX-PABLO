package co.sofka.usecase;

import co.sofka.Account;
import co.sofka.gateway.IAccountRepository;

public class SaveAccountUseCase {

    private final IAccountRepository repository;

    public SaveAccountUseCase(IAccountRepository accountRepository) {
        this.repository = accountRepository;
    }

    public Account apply(Account account) {
        return repository.save(account);
    }
}
