package my.pet.ticket.dataLayer.dao.interfaces;

import my.pet.ticket.dataLayer.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {
    // Методы поиска задач
    List<Ticket> findAll();
    Optional<Ticket> findById(Long id);

    // Методы сохранения и удаления задач
    Ticket save(Ticket ticket);
    void delete(Ticket ticket);
}
