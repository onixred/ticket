package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity_;
import my.pet.ticket.server.application.domain.model.Role;
import my.pet.ticket.server.application.port.persistence.RolePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

  private static final DomainServiceException ROLE_NOT_FOUND = new DomainServiceException(
      "Role not found");

  private final RolePort rolePort;

  private final ModelMapper modelMapper;

  public RoleService(RolePort rolePort, ModelMapper modelMapper) {
    this.rolePort = rolePort;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public Role getRole(Long roleId) {
    RoleEntity roleEntity = getRoleEntity(roleId);
    return this.modelMapper.map(roleEntity, Role.class);
  }

  @Transactional
  public List<Role> getAllRoles() {
    return this.rolePort.getAll(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(RoleEntity_.ACTIVE),
                true))).stream().map(roleEntity -> this.modelMapper.map(roleEntity, Role.class))
        .toList();
  }

  @Transactional
  public void deleteRole(Long roleId) {
    RoleEntity roleEntity = getRoleEntity(roleId);
    this.rolePort.delete(roleEntity);
  }

  private RoleEntity getRoleEntity(Long roleId) {
    return this.rolePort.get(((root, query, criteriaBuilder) -> criteriaBuilder.and(
            criteriaBuilder.equal(root.get(RoleEntity_.ROLE_ID), roleId),
            criteriaBuilder.equal(root.get(RoleEntity_.ACTIVE), true))))
        .orElseThrow(() -> ROLE_NOT_FOUND);
  }

}
