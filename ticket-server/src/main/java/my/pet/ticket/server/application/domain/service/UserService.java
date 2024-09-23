package my.pet.ticket.server.application.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.adapter.persistence.entity.Roles;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity_;
import my.pet.ticket.server.application.domain.model.Role;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.model.payload.request.RegisterUserRequest;
import my.pet.ticket.server.application.port.persistence.UserPort;
import my.pet.ticket.server.common.utils.NameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserPort userPort;

  private final RoleService roleService;

  private final ModelMapper modelMapper;

  public UserService(UserPort userPort, RoleService roleService, ModelMapper modelMapper) {
    this.userPort = userPort;
    this.roleService = roleService;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public User registerUser(RegisterUserRequest request) {
    String[] names = NameUtils.parseFullName(request.getFullName());
    Role role = this.roleService.getRole(Roles.GUEST.getRoleId());
    UserEntity userEntity = UserEntity.builder().roleId(role.getRoleId()).firstName(names[1])
        .lastName(names[0]).surName(names[2]).fullName(request.getFullName())
        .login(request.getLogin()).password(request.getPassword()).active(false).suspended(false)
        .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).deleted(false).build();
    userEntity = this.userPort.create(userEntity);
    return convertUserEntityToUser(userEntity, role);
  }

  @Transactional
  public User activateUser(Long userId) {
    UserEntity userEntity = this.userPort.get(
        (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(UserEntity_.USER_ID),
            userId)).orElseThrow(() -> new PersistenceAdapterException("User not found"));
    Role role = this.roleService.getRole(Roles.MANAGER.getRoleId());
    userEntity.setActive(true);
    userEntity.setRoleId(role.getRoleId());
    userEntity = this.userPort.update(userEntity);
    return convertUserEntityToUser(userEntity, role);
  }

  @Transactional
  public User getUser(Long userId) {
    UserEntity userEntity = this.userPort.get(
        ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(UserEntity_.USER_ID),
            userId))).orElseThrow(() -> new PersistenceAdapterException("User not found"));
    Long roleId = userEntity.getRoleId();
    Role role = this.roleService.getRole(roleId);
    return convertUserEntityToUser(userEntity, role);
  }

  @Transactional
  public List<User> getAllUsers() {
    return getAllUsers(Pageable.unpaged());
  }

  @Transactional
  public List<User> getAllUsers(Integer page, Integer pageSize) {
    return getAllUsers(PageRequest.of(page, pageSize <= 500 ? pageSize : 500));
  }

  @Transactional
  public User suspendUser(Long userId) {
    UserEntity userEntity = this.userPort.get(
        ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(UserEntity_.USER_ID),
            userId))).orElseThrow(() -> new PersistenceAdapterException("User not found"));
    Long roleId = userEntity.getRoleId();
    Role role = this.roleService.getRole(roleId);
    userEntity.setSuspended(true);
    userEntity = this.userPort.update(userEntity);
    return convertUserEntityToUser(userEntity, role);
  }

  @Transactional
  public void deleteUser(Long userId) {
    UserEntity userEntity = this.userPort.get(
        ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(UserEntity_.USER_ID),
            userId))).orElseThrow(() -> new PersistenceAdapterException("User not found"));
    this.userPort.delete(userEntity);
  }

  private List<User> getAllUsers(Pageable pageable) {
    List<UserEntity> userEntities = this.userPort.getAll(
        ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()), pageable);
    List<Role> roles = this.roleService.getAllRoles();
    return userEntities.stream().map(currentUser -> {
      Role role = roles.stream()
          .filter(currentRole -> currentRole.getRoleId().equals(currentUser.getRoleId()))
          .findFirst().orElseThrow(() -> new PersistenceAdapterException("Role not found"));
      return convertUserEntityToUser(currentUser, role);
    }).toList();
  }

  private User convertUserEntityToUser(UserEntity userEntity, Role role) {
    User user = this.modelMapper.map(userEntity, User.class);
    user.setRole(role);
    return user;
  }

}
