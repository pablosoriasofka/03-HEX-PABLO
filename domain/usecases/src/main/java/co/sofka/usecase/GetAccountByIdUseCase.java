package co.sofka.usecase;

import co.sofka.Account;
import co.sofka.gateway.IAccountRepository;

public class GetAccountByIdUseCase {

    private final IAccountRepository repository;

    public GetAccountByIdUseCase(IAccountRepository repository) {
        this.repository = repository;
    }

    public Account apply(String id) {
        return repository.findById(id);
    }

}
