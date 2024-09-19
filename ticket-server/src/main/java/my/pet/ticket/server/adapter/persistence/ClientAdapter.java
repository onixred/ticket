package my.pet.ticket.server.adapter.persistence;

import my.pet.ticket.server.adapter.persistence.entity.ClientEntity;
import my.pet.ticket.server.adapter.persistence.repository.ClientRepository;
import my.pet.ticket.server.application.port.persistence.ClientPort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientAdapter implements ClientPort {

    private final ClientRepository clientRepository;

    public ClientAdapter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<ClientEntity> get(Specification<ClientEntity> specification) {
        return this.clientRepository.findOne(specification);
    }

    @Override
    public List<ClientEntity> getAll(Specification<ClientEntity> specification, Pageable pageable) {
        return this.clientRepository.findAll(specification, pageable).stream().toList();
    }

    @Override
    public ClientEntity create(ClientEntity entity) {
        if (entity.getClientId() == null) {
            return this.clientRepository.save(entity);
        }
        throw new PersistenceAdapterException("Client shouldn't have id when creating");
    }

    @Override
    public ClientEntity update(ClientEntity entity) {
        if (this.clientRepository.existsById(entity.getClientId())) {
            return this.clientRepository.save(entity);
        }
        throw new PersistenceAdapterException("Client not exist");
    }

    @Override
    public void delete(ClientEntity entity) {
        if (this.clientRepository.existsById(entity.getClientId())) {
            entity.setDeleted(true);
            this.clientRepository.save(entity);
            return;
        }
        throw new PersistenceAdapterException("Client not exist");
    }

}
