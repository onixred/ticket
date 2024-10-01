package my.pet.ticket.server.adapter.persistence;

import java.time.LocalDateTime;
import java.util.Optional;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity_;
import my.pet.ticket.server.adapter.persistence.repository.ClientRepository;
import my.pet.ticket.server.application.port.persistence.ClientPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ClientAdapter implements ClientPort {

  private final static Specification<ClientEntity> NOT_DELETED_SPECIFICATION = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
      root.get(ClientEntity_.DELETED), false));

  private final ClientRepository clientRepository;

  public ClientAdapter(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public Optional<ClientEntity> get(Specification<ClientEntity> specification) {
    return this.clientRepository.findOne(NOT_DELETED_SPECIFICATION.and(specification));
  }

  @Override
  public Page<ClientEntity> getAll(Specification<ClientEntity> specification, Pageable pageable) {
    return this.clientRepository.findAll(NOT_DELETED_SPECIFICATION.and(specification), pageable);
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
      entity.setUpdatedAt(LocalDateTime.now());
      return this.clientRepository.save(entity);
    }
    throw new PersistenceAdapterException("Client not exist");
  }

}
