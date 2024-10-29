package co.sofka;



import co.sofka.config.JpaClientRepository;
import co.sofka.data.entity.ClientEntity;
import co.sofka.gateway.IGenericFuntion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ClientRepository implements IGenericFuntion<Client> {

    private final JpaClientRepository repository;


    @Override
    public Client update(Client item) {
        return null;
    }

    @Override
    public Client save(Client item) {
        return null;
    }

    @Override
    public Client delete(Client item) {
        return null;
    }

    @Override
    public Client findById(Long id) {
        return null;
    }

    @Override
    public Long deleteByElementId(Long id) {
        return 0L;
    }

    @Override
    public List<Client> getAll() {

        List<ClientEntity> all = repository.findAll();

        List<Client> collect = all.stream().map(item -> {
            Client client = new Client();
            client.setId(item.getId().toString());
            client.setName(item.getName());
            client.setAddress(item.getAddress());
            client.setUsuario(item.getUsuario());
            client.setCode(item.getCode());
            return client;
        }).collect(Collectors.toList());

        return collect;
    }
}
