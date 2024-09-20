package my.pet.ticket.server.application.port.persistence;

import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class UserPortTest {

    @Autowired
    UserPort userPort;

    @Test
    void createTest () {
        UserEntity userEntity = UserEntity.builder()
                                          .roleId(2L)
                                          .firstName("Ilya")
                                          .lastName("Gavrilovch")
                                          .surName("Ivanovich")
                                          .fullName("Gavrilovch Ilya Ivanovich")
                                          .login("testlogin")
                                          .password("passwordhash")
                                          .active(true)
                                          .suspended(false)
                                          .createdAt(LocalDateTime.now())
                                          .updatedAt(LocalDateTime.now())
                                          .deleted(false)
                                          .build();
        UserEntity createdUserEntity = this.userPort.create(userEntity);
        Assertions.assertNotNull(createdUserEntity.getUserId());
    }

    @Test
    void getTestNotThrows () {
        Assertions.assertDoesNotThrow(() -> {
            this.userPort.get((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                    root.get(UserEntity_.USER_ID),
                    1001
            )).orElseThrow(() -> new PersistenceAdapterException("User not found"));
        });
    }

    @Test
    void getTestThrows () {
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.userPort.get((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                    root.get(UserEntity_.USER_ID),
                    999
            )).orElseThrow(() -> new PersistenceAdapterException("User not found"));
        });
    }

    @Test
    void getAllTest () {
        List<UserEntity> userEntities = this.userPort.getAll(((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()));
        assertFalse(userEntities.isEmpty());
    }

    @Test
    void updateTestNotThrow () {
        UserEntity userEntity = this.userPort.get((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                UserEntity_.USER_ID), 1001)).orElseThrow(() -> new PersistenceAdapterException("User not found"));
        userEntity.setLogin("newlogin");
        UserEntity updatedUserEntity = this.userPort.update(userEntity);
        Assertions.assertEquals("newlogin", updatedUserEntity.getLogin());
    }

    @Test
    void updateTestThrow () {
        UserEntity userEntity = this.userPort.get((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                UserEntity_.USER_ID), 1001)).orElseThrow(() -> new PersistenceAdapterException("User not found"));
        userEntity.setUserId(999L);
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.userPort.update(userEntity);
        });
    }

    @Test
    void deleteTestNotThrows () {
        UserEntity userEntity = this.userPort.get((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                UserEntity_.USER_ID), 1001)).orElseThrow(() -> new PersistenceAdapterException("User not found"));
        this.userPort.delete(userEntity);
        UserEntity deletedUserEntity = this.userPort.get((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                UserEntity_.USER_ID), 1001)).orElseThrow(() -> new PersistenceAdapterException("User not found"));
        assertTrue(deletedUserEntity.getDeleted());
    }

    @Test
    void deleteTestThrows () {
        UserEntity userEntity = this.userPort.get((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                UserEntity_.USER_ID), 1001)).orElseThrow(() -> new PersistenceAdapterException("User not found"));
        userEntity.setUserId(999L);
        Assertions.assertThrows(PersistenceAdapterException.class, () -> {
            this.userPort.delete(userEntity);
        });
    }

}