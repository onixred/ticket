package my.pet.ticket.server.application.domain.service;

import java.time.LocalDateTime;
import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity;
import my.pet.ticket.server.adapter.persistence.entity.RoleEntity_;
import my.pet.ticket.server.adapter.persistence.entity.Roles;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity_;
import my.pet.ticket.server.application.domain.model.Role;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.model.payload.request.RegisterUserRequest;
import my.pet.ticket.server.application.port.persistence.RolePort;
import my.pet.ticket.server.application.port.persistence.UserPort;
import my.pet.ticket.server.common.utils.NameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserPort userPort;

  private final RolePort rolePort;

  private final ModelMapper modelMapper;

  public UserService(UserPort userPort, RolePort rolePort, ModelMapper modelMapper) {
    this.userPort = userPort;
    this.rolePort = rolePort;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public User registerUser(RegisterUserRequest request) {
    String[] names = NameUtils.parseFullName(request.getFullName());
    RoleEntity roleEntity = this.rolePort.get(
            (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                RoleEntity_.ROLE_ID), Roles.GUEST.getRoleId()))
        .orElseThrow(() -> new PersistenceAdapterException("Role not found"));
    UserEntity userEntity = UserEntity.builder()
        .roleId(roleEntity.getRoleId())
        .firstName(names[1])
        .lastName(names[0])
        .surName(names[2])
        .fullName(request.getFullName())
        .login(request.getLogin())
        .password(request.getPassword())
        .active(false)
        .suspended(false)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .deleted(false)
        .build();
    userEntity = this.userPort.create(userEntity);
    return convertUserEntityToUser(userEntity, roleEntity);
  }

  public User activateUser(Long userId) {
    UserEntity userEntity = this.userPort.get(
            (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                UserEntity_.USER_ID), userId))
        .orElseThrow(() -> new PersistenceAdapterException("USer not found"));
    RoleEntity roleEntity = this.rolePort.get(
            (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                RoleEntity_.ROLE_ID), Roles.MANAGER.getRoleId()))
        .orElseThrow(() -> new PersistenceAdapterException("Role not found"));
    userEntity.setActive(true);
    userEntity.setRoleId(roleEntity.getRoleId());
    userEntity = this.userPort.update(userEntity);
    return convertUserEntityToUser(userEntity, roleEntity);
  }

  private User convertUserEntityToUser(UserEntity userEntity, RoleEntity roleEntity) {
    return User.builder()
        .userId(userEntity.getUserId())
        .role(modelMapper.map(roleEntity, Role.class))
        .fullName(userEntity.getFullName())
        .login(userEntity.getLogin())
        .active(userEntity.getActive())
        .suspended(userEntity.getSuspended())
        .build();
  }

}
