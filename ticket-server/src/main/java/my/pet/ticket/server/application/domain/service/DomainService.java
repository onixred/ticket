package my.pet.ticket.server.application.domain.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @param <T> model
 * @param <S> entity
 */
public interface DomainService<T, S> {

  @Transactional
  T get(Long id);

  @Transactional
  default List<T> getAll() {
    return getAll(Pageable.unpaged());
  }

  @Transactional
  default List<T> getAll(Integer page, Integer pageSize) {
    return getAll(PageRequest.of(page != null ? page : 0,
        pageSize != null && pageSize <= 500 ? pageSize : 500));
  }

  @Transactional
  void delete(Long id);

  List<T> getAll(Pageable pageable);

  T convertEntityToModel(S entity);

}
