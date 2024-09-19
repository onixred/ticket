package my.pet.ticket.server.application.port.persistence;

import my.pet.ticket.server.adapter.persistence.entity.AbstractEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface PersistencePort<T extends AbstractEntity> {

    Optional<T> get(Specification<T> specification);

    List<T> getAll(Specification<T> specification, Pageable pageable);

    T create(T entity);

    T update(T entity);

    default void delete(T entity) {
        entity.setDeleted(true);
        update(entity);
    }

    default List<T> getAll(Specification<T> specification) {
        return getAll(specification, Pageable.unpaged());
    }

}
