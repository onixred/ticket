package my.pet.ticket.server.dao;

import jakarta.persistence.Column;

import java.time.ZonedDateTime;

public abstract class AbstractDao {

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

}