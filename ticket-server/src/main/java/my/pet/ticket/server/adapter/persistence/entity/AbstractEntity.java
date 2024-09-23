package my.pet.ticket.server.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractEntity {

  @Column(name = "created_at", nullable = false)
  protected LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  protected LocalDateTime updatedAt;

  @Column(name = "deleted", nullable = false)
  protected Boolean deleted;

}
