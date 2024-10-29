package co.sofka;



import co.sofka.config.JpaBankRepository;
import co.sofka.data.entity.BankEntity;
import co.sofka.gateway.IBankRepository;
import co.sofka.gateway.IGenericFuntion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class BankRepository implements IBankRepository {

    private final JpaBankRepository repository;


    @Override
    public Bank update(Bank item) {
        BankEntity bank = new BankEntity();
        bank.setId(Long.parseLong(item.getId()));
        bank.setName(item.getName());
        bank.setIsActive(true);

        bank.setCreatedAt(new Date(System.currentTimeMillis()));
        bank.setCreateUserId("SRO");

        repository.save(bank);

        return item;
    }

    @Override
    public Bank save(Bank item) {
        BankEntity bank = new BankEntity();
        bank.setId(Long.parseLong(item.getId()));
        bank.setName(item.getName());
        bank.setIsActive(true);

        bank.setCreatedAt(new Date(System.currentTimeMillis()));
        bank.setCreateUserId("SRO");

        repository.save(bank);

        return item;
    }

    @Override
    public Bank delete(Bank item) {
        BankEntity bank = new BankEntity();
        bank.setId(Long.parseLong(item.getId()));
        bank.setName(item.getName());
        bank.setIsActive(false);
        repository.save(bank);
        return item;
    }

    @Override
    public Bank findById(Long id) {
        BankEntity bankEntity = repository.findById(id).orElse(null);
        if(bankEntity != null){
            Bank bank = new Bank();
            bank.setId(bankEntity.getId().toString());
            bank.setName(bankEntity.getName());
            bank.setBalance(bankEntity.getBalance());
            return bank;
        }
        return null;
    }

    @Override
    public Long deleteByElementId(Long id) {
        repository.deleteById(id);
        return id;
    }

    @Override
    public List<Bank> getAll() {

        List<BankEntity> all = repository.findAll();

        List<Bank> list = all.stream().map(bankEntity -> {
            Bank bank = new Bank();
            bank.setId(bankEntity.getId().toString());
            bank.setName(bankEntity.getName());
            return bank;
        }).toList();

        return list;
    }
}
