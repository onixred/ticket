package my.pet.ticket.dataLayer.dao.interfaces;

import my.pet.ticket.dataLayer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Методы поиска пользователей
    List<User> findAll();
    Optional<User> findById(Long id);
    User findByLogin(String login);

    // Методы сохранения и удаления пользователей
    User save(User user);
    void delete(User user);
}
