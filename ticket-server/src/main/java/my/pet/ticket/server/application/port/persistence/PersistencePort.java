package my.pet.ticket.server.application.port.persistence;

import java.util.Optional;
import my.pet.ticket.server.adapter.persistence.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PersistencePort<T extends AbstractEntity> {

  Optional<T> get(Specification<T> specification);

  Page<T> getAll(Specification<T> specification, Pageable pageable);

  T create(T entity);

  T update(T entity);

  default void delete(T entity) {
    entity.setDeleted(true);
    update(entity);
  }

  default Page<T> getAll(Specification<T> specification) {
    return getAll(specification, Pageable.unpaged());
  }

}
