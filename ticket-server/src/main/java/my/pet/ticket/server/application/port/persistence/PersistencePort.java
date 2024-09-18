package my.pet.ticket.server.application.port.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface PersistencePort<T> {

    Optional<T> get(Specification<T> specification);

    List<T> getAll(Specification<T> specification, Pageable pageable);

    T create(T entity);

    T update(T entity);

    void delete(T entity);

}
