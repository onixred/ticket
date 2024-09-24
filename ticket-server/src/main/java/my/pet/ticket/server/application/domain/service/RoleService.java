package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity_;
import my.pet.ticket.server.application.domain.model.Role;
import my.pet.ticket.server.application.port.persistence.RolePort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService implements DomainService<Role, RoleEntity> {

  private static final DomainServiceException ROLE_NOT_FOUND = new DomainServiceException(
      "Role not found");

  private final RolePort rolePort;

  private final ModelMapper modelMapper;

  public RoleService(RolePort rolePort, ModelMapper modelMapper) {
    this.rolePort = rolePort;
    this.modelMapper = modelMapper;
  }

  @Override
  @Transactional
  public Role get(Long id) {
    RoleEntity roleEntity = this.rolePort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get(RoleEntity_.ROLE_ID), id),
                criteriaBuilder.equal(root.get(RoleEntity_.ACTIVE), true))))
        .orElseThrow(() -> ROLE_NOT_FOUND);
    return convertEntityToModel(roleEntity);
  }

  @Override
  @Transactional
  public List<Role> getAll() {
    return DomainService.super.getAll();
  }

  @Override
  @Transactional
  public List<Role> getAll(Integer page, Integer pageSize) {
    return DomainService.super.getAll(page, pageSize);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    RoleEntity roleEntity = this.rolePort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get(RoleEntity_.ROLE_ID), id),
                criteriaBuilder.equal(root.get(RoleEntity_.ACTIVE), true))))
        .orElseThrow(() -> ROLE_NOT_FOUND);
    this.rolePort.delete(roleEntity);
  }

  @Override
  @Transactional
  public List<Role> getAll(Pageable pageable) {
    return this.rolePort.getAll(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(RoleEntity_.ACTIVE),
                true)), pageable).stream()
        .map(this::convertEntityToModel).toList();
  }

  @Override
  @Transactional
  public Role convertEntityToModel(RoleEntity entity) {
    return this.modelMapper.map(entity, Role.class);
  }

}
