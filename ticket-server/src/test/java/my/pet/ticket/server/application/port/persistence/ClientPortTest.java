package my.pet.ticket.server.application.port.persistence;

import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientPortTest {

    @Autowired
    ClientPort clientPort;

    @Test
    void createTest () {
        ClientEntity clientEntity = ClientEntity.builder()
                                                .firstName("Valeriy")
                                                .lastName("Garmoshkin")
                                                .surName("Pavlovich")
                                                .fullName("Garmoshkin Valeriy Pavlovich")
                                                .email("valerygp@mail.ru")
                                                .createdAt(LocalDateTime.now())
                                                .updatedAt(LocalDateTime.now())
                                                .deleted(false)
                                                .build();
        ClientEntity client = this.clientPort.create(clientEntity);
        assertNotNull(client.getClientId());
    }

    @Test
    void getTestNotThrow () {
        Assertions.assertDoesNotThrow(() -> {
            this.clientPort.get((Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                    root.get(ClientEntity_.CLIENT_ID),
                    1001
            )).orElseThrow(() -> new PersistenceAdapterException("Client not found"));
        });
    }

    @Test
    void getTestThrow () {
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.clientPort.get((Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                    root.get(ClientEntity_.CLIENT_ID),
                    999
            )).orElseThrow(() -> new PersistenceAdapterException("Client not found"));
        });
    }

    @Test
    void getAllTest () {
        List<ClientEntity> clientEntities = this.clientPort.getAll((Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        assertFalse(clientEntities.isEmpty());
    }

    @Test
    void updateNotThrow () {
        ClientEntity client = this.clientPort.get((Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ClientEntity_.CLIENT_ID),
                1001
        )).orElseThrow(() -> new PersistenceAdapterException("Client not found"));
        Assertions.assertNotEquals("newemail@mail.ru", client.getEmail());
        client.setEmail("newemail@mail.ru");
        ClientEntity updatedClient = this.clientPort.update(client);
        Assertions.assertEquals("newemail@mail.ru", updatedClient.getEmail());
    }

    @Test
    void updateThrow () {
        ClientEntity client = this.clientPort.get((Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ClientEntity_.CLIENT_ID),
                1001
        )).orElseThrow(() -> new PersistenceAdapterException("Client not found"));
        client.setClientId(999L);
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.clientPort.update(client);
        });
    }

    @Test
    void deleteNotThrow () {
        ClientEntity client = this.clientPort.get((Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ClientEntity_.CLIENT_ID),
                1001
        )).orElseThrow(() -> new PersistenceAdapterException("Client not found"));
        this.clientPort.delete(client);
        ClientEntity deletedClient = this.clientPort.get((Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ClientEntity_.CLIENT_ID),
                1001
        )).orElseThrow(() -> new PersistenceAdapterException("Client not found"));
        assertTrue(deletedClient.getDeleted());
    }

    @Test
    void deleteThrow () {
        ClientEntity client = this.clientPort.get((Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ClientEntity_.CLIENT_ID),
                1001
        )).orElseThrow(() -> new PersistenceAdapterException("Client not found"));
        client.setClientId(999L);
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.clientPort.delete(client);
        });
    }

}