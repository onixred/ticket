package my.pet.ticket.server.application.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @param <T> model
 * @param <S> entity
 */
public interface DomainService<T, S> {

  T get(Long id);

  default Page<T> getAll() {
    return getAll(Pageable.unpaged());
  }

  default Page<T> getAll(Integer page, Integer pageSize) {
    return getAll(PageRequest.of(page != null ? page : 0,
        pageSize != null && pageSize <= 500 && pageSize > 0 ? pageSize : 500));
  }

  void delete(Long id);

  Page<T> getAll(Pageable pageable);

  T convertEntityToModel(S entity);

}
