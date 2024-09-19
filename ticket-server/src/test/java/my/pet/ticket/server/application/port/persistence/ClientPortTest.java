package my.pet.ticket.server.application.port.persistence;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity;
import my.pet.ticket.server.adapter.persistence.entity.ClientEntity_;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientPortTest {

    @Autowired
    ClientPort clientPort;

    @Test
    void getTest() {
        ClientEntity client = this.clientPort.get((Specification<ClientEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ClientEntity_.CLIENT_ID), 1000))
                .orElseThrow(() -> new RuntimeException());
    }

}