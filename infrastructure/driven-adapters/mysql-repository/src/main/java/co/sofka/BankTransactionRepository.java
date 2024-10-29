package co.sofka;



import co.sofka.config.JpaBankTransactionRepository;
import co.sofka.data.entity.BankTransactionEntity;
import co.sofka.gateway.IBankTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class BankTransactionRepository implements IBankTransactionRepository {

    private final JpaBankTransactionRepository repository;


    @Override
    public BankTransaction update(BankTransaction item) {
        return null;
    }

    @Override
    public BankTransaction save(BankTransaction item) {
        return null;
    }

    @Override
    public BankTransaction delete(BankTransaction item) {
        return null;
    }

    @Override
    public BankTransaction findById(Long id) {
        return null;
    }

    @Override
    public Long deleteByElementId(Long id) {
        return id;
    }

    @Override
    public List<BankTransaction> getAll() {
        List<BankTransactionEntity> all = repository.findAll();
        List<BankTransaction> collect = all.stream().map(item -> {
            BankTransaction bankTransaction = new BankTransaction();
            bankTransaction.setId(item.getId().toString());
            bankTransaction.setAmount(item.getAmount());

            TypeTransaction transactionType = new TypeTransaction();
            transactionType.setId(item.getTypeTransaction().getId().toString());

            bankTransaction.setTypeTransaction(transactionType);
            bankTransaction.setCreatedAt(item.getCreatedAt());
            return bankTransaction;
        }).collect(Collectors.toList());
        return collect;
    }
}
