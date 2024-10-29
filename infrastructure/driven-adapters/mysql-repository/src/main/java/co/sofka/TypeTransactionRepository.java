package co.sofka;


import co.sofka.config.JpaTypeTransactionRepository;
import co.sofka.data.entity.TypeTransactionEntity;
import co.sofka.gateway.IGenericFuntion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TypeTransactionRepository implements IGenericFuntion<TypeTransaction> {

    private final JpaTypeTransactionRepository repository;


    @Override
    public TypeTransaction update(TypeTransaction item) {
        return null;
    }

    @Override
    public TypeTransaction save(TypeTransaction item) {
        return null;
    }

    @Override
    public TypeTransaction delete(TypeTransaction item) {
        return null;
    }

    @Override
    public TypeTransaction findById(Long id) {
        return null;
    }

    @Override
    public Long deleteByElementId(Long id) {
        return 0L;
    }

    @Override
    public List<TypeTransaction> getAll() {

        List<TypeTransactionEntity> all = repository.findAll();

        List<TypeTransaction> collect = all.stream().map(item -> {
            TypeTransaction typeTransaction = new TypeTransaction();
            typeTransaction.setId(item.getId().toString());
            typeTransaction.setName(item.getName());
            return typeTransaction;
        }).collect(Collectors.toList());

        return collect;
    }
}
