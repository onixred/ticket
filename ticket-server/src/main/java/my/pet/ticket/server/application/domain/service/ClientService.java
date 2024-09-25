package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity_;
import my.pet.ticket.server.application.domain.model.Client;
import my.pet.ticket.server.application.port.persistence.ClientPort;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService implements DomainService<Client, ClientEntity> {

  private static final DomainServiceException CLIENT_NOT_FOUND = new DomainServiceException(
      "Client not found");

  private final ClientPort clientPort;

  private final PhoneNumberService phoneNumberService;

  private final ModelMapper modelMapper;

  public ClientService(ClientPort clientPort, PhoneNumberService phoneNumberService,
      ModelMapper modelMapper) {
    this.clientPort = clientPort;
    this.phoneNumberService = phoneNumberService;
    this.modelMapper = modelMapper;
  }

  @Override
  @Transactional
  @Cacheable(cacheNames = "client", key = "#id")
  public Client get(Long id) {
    ClientEntity clientEntity = this.clientPort.get(
        ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ClientEntity_.CLIENT_ID),
            id))).orElseThrow(() -> CLIENT_NOT_FOUND);
    String phoneNumber = this.phoneNumberService.getByClientId(clientEntity.getClientId());
    Client client = convertEntityToModel(clientEntity);
    client.setPhoneNumber(phoneNumber);
    return client;
  }

  @Override
  @Transactional
  public List<Client> getAll() {
    return DomainService.super.getAll();
  }

  @Override
  @Transactional
  public List<Client> getAll(Integer page, Integer pageSize) {
    return DomainService.super.getAll(page, pageSize);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    ClientEntity client = this.clientPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ClientEntity_.CLIENT_ID),
                id)))
        .orElseThrow(() -> CLIENT_NOT_FOUND);
    this.phoneNumberService.deleteByClientId(client.getClientId());
    this.clientPort.delete(client);
  }

  @Override
  @Transactional
  public List<Client> getAll(Pageable pageable) {
    return this.clientPort.getAll(((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()),
            pageable)
        .stream()
        .map(clientEntity -> {
          Client client = convertEntityToModel(clientEntity);
          String phoneNumber = this.phoneNumberService.getByClientId(clientEntity.getClientId());
          client.setPhoneNumber(phoneNumber);
          return client;
        })
        .toList();
  }

  @Override
  public Client convertEntityToModel(ClientEntity entity) {
    return this.modelMapper.map(entity, Client.class);
  }

}
