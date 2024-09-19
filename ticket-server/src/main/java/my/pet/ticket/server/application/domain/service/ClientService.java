package my.pet.ticket.server.application.domain.service;

import my.pet.ticket.server.adapter.persistence.entity.*;
import my.pet.ticket.server.application.domain.model.Client;
import my.pet.ticket.server.application.domain.model.Filter;
import my.pet.ticket.server.application.port.persistence.ClientPort;
import my.pet.ticket.server.application.port.persistence.PhoneNumberPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientPort clientPort;

    private final PhoneNumberPort phoneNumberPort;

    public ClientService(ClientPort clientPort, PhoneNumberPort phoneNumberPort) {
        this.clientPort = clientPort;
        this.phoneNumberPort = phoneNumberPort;
    }

    @Transactional
    public Client getClient(Filter filter) {
        ClientEntity client = this.clientPort.get(((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(ClientEntity_.clientId), filter.getClientId()
                ))).orElseThrow(RuntimeException::new); //TODO: Custom exception
        PhoneNumberEntity phoneNumber = this.phoneNumberPort.get((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(PhoneNumberEntity_.id).get(PhoneNumberIdEntity_.clientId), client.getClientId()))
                .orElseThrow(RuntimeException::new); //TODO: Custom exception
        return Client.builder()
                .clientId(client.getClientId())
                .fullName(client.getFullName())
                .email(client.getEmail())
                .phoneNumber(phoneNumber.getFullNumber())
                .build();
    }

}
