package co.sofka;



import co.sofka.config.JpaAccountRepository;
import co.sofka.data.entity.AccountEntity;
import co.sofka.gateway.IAccountRepository;
import co.sofka.gateway.IGenericFuntion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountRepository implements IAccountRepository {

    private final JpaAccountRepository repository;


    @Override
    public Account update(Account item) {

        AccountEntity account = new AccountEntity();
        account.setId(Long.parseLong(item.getId()));
        account.setNumber(item.getNumber());
        account.setPin(item.getPin());
        account.setBalance(item.getBalance());

        account.setCreatedAt(new Date(System.currentTimeMillis()));
        account.setCreateUserId("SRO");

        AccountEntity obj = (AccountEntity) repository.save(account);

        return item;
    }

    @Override
    public Account save(Account item) {
        AccountEntity account = new AccountEntity();
        account.setId(Long.parseLong(item.getId()));
        account.setNumber(item.getNumber());
        account.setPin(item.getPin());
        account.setBalance(item.getBalance());
        account.setCreatedAt(new Date(System.currentTimeMillis()));
        account.setCreateUserId("SRO");


        AccountEntity obj = (AccountEntity) repository.save(account);

        return item;
    }

    @Override
    public Account delete(Account item) {
        return null;
    }

    @Override
    public Account findById(String id) {
        Optional<AccountEntity> byId = repository.findById(Long.parseLong(id));
        if(byId.isPresent()){
            AccountEntity accountEntity = byId.get();
            Account account = new Account();
            account.setId(accountEntity.getId().toString());
            account.setNumber(accountEntity.getNumber());
            account.setPin(accountEntity.getPin());
            account.setBalance(accountEntity.getBalance());
            return account;
        }
        return null;
    }

    @Override
    public Long deleteByElementId(Long id) {
        return 0L;
    }

    @Override
    public List<Account> getAll() {

        List<AccountEntity> all = repository.findAll();

        List<Account> list = all.stream().map(accountEntity -> {
            Account account = new Account();
            account.setId(accountEntity.getId().toString());
            account.setNumber(accountEntity.getNumber());
            return account;
        }).toList();

        return list;
    }

    @Override
    public Account findByNumberAndPing(String accountNumber,String pin) {
        AccountEntity byNumberAndPin = repository.findByNumberAndPin(accountNumber, pin);
        if(byNumberAndPin != null){
            Account account = new Account();
            account.setId(byNumberAndPin.getId().toString());
            account.setNumber(byNumberAndPin.getNumber());
            account.setPin(byNumberAndPin.getPin());
            account.setBalance(byNumberAndPin.getBalance());
            return account;
        }
        return null;
    }

    @Override
    public Account findByNumber(String accountNumber) {
        AccountEntity byNumberAndPin = repository.findByNumber(accountNumber);
        if(byNumberAndPin != null){
            Account account = new Account();
            account.setId(byNumberAndPin.getId().toString());
            account.setNumber(byNumberAndPin.getNumber());
            account.setPin(byNumberAndPin.getPin());
            account.setBalance(byNumberAndPin.getBalance());
            return account;
        }
        return null;
    }
}
