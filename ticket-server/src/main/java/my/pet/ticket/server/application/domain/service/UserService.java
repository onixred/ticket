package my.pet.ticket.server.application.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import my.pet.ticket.server.adapter.persistence.entity.Roles;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity;
import my.pet.ticket.server.adapter.persistence.entity.UserEntity_;
import my.pet.ticket.server.application.domain.model.Role;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.model.payload.request.RegisterUserRequest;
import my.pet.ticket.server.application.port.persistence.UserPort;
import my.pet.ticket.server.common.utils.NameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements DomainService<User, UserEntity> {

  private static final DomainServiceException USER_NOT_FOUND = new DomainServiceException(
      "User not found");

  private final UserPort userPort;

  private final RoleService roleService;

  private final ModelMapper modelMapper;

  public UserService(UserPort userPort, RoleService roleService, ModelMapper modelMapper) {
    this.userPort = userPort;
    this.roleService = roleService;
    this.modelMapper = modelMapper;
  }

  @Transactional
  @Caching(
      put = @CachePut(cacheNames = "user", key = "#result.userId"),
      evict = @CacheEvict(cacheNames = "users", allEntries = true)
  )
  public User register(
      Function<RegisterUserRequest.RegisterUserRequestBuilder, RegisterUserRequest> requestFunction) {
    return register(requestFunction.apply(RegisterUserRequest.builder()));
  }

  @Transactional
  @Caching(
      put = @CachePut(cacheNames = "user", key = "#result.userId"),
      evict = @CacheEvict(cacheNames = "users", allEntries = true)
  )
  public User register(RegisterUserRequest request) {
    String[] names = NameUtils.parseFullName(request.getFullName());
    Role role = this.roleService.get(Roles.GUEST.getRoleId());
    UserEntity userEntity = UserEntity.builder().roleId(role.getRoleId()).firstName(names[1])
        .lastName(names[0]).surName(names[2]).fullName(request.getFullName())
        .login(request.getLogin()).password(request.getPassword())
        .active(false).suspended(false).createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now()).deleted(false).build();
    userEntity = this.userPort.create(userEntity);
    User user = convertEntityToModel(userEntity);
    user.setRole(role);
    return user;
  }

  @Transactional
  @Caching(
      put = @CachePut(cacheNames = "user", key = "#result.userId"),
      evict = @CacheEvict(cacheNames = "users", allEntries = true)
  )
  public User activateUser(Long userId) {
    UserEntity userEntity = this.userPort.get(
        (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(UserEntity_.USER_ID),
            userId)).orElseThrow(() -> USER_NOT_FOUND);
    Role role = this.roleService.get(Roles.MANAGER.getRoleId());
    userEntity.setActive(true);
    userEntity.setRoleId(role.getRoleId());
    userEntity = this.userPort.update(userEntity);
    User user = convertEntityToModel(userEntity);
    user.setRole(role);
    return user;
  }

  @Transactional
  @Caching(
      put = @CachePut(cacheNames = "user", key = "#result.userId"),
      evict = @CacheEvict(cacheNames = "users", allEntries = true)
  )
  public User suspend(Long userId) {
    UserEntity userEntity = this.userPort.get(
        ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(UserEntity_.USER_ID),
            userId))).orElseThrow(() -> USER_NOT_FOUND);
    Long roleId = userEntity.getRoleId();
    Role role = this.roleService.get(roleId);
    userEntity.setSuspended(true);
    userEntity = this.userPort.update(userEntity);
    User user = convertEntityToModel(userEntity);
    user.setRole(role);
    return user;
  }

  @Override
  @Transactional
  @Cacheable(cacheNames = "user", key = "#id")
  public User get(Long id) {
    UserEntity userEntity = this.userPort.get(
        ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(UserEntity_.USER_ID),
            id))).orElseThrow(() -> USER_NOT_FOUND);
    Long roleId = userEntity.getRoleId();
    Role role = this.roleService.get(roleId);
    User user = convertEntityToModel(userEntity);
    user.setRole(role);
    return user;
  }

  @Transactional
  @Cacheable(cacheNames = "user_login", key = "#login")
  public User getByLogin(String login) {
    UserEntity userEntity = this.userPort.get(
            ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(UserEntity_.LOGIN),
                login)))
        .orElseThrow(() -> USER_NOT_FOUND);
    Long roleId = userEntity.getRoleId();
    Role role = this.roleService.get(roleId);
    User user = convertEntityToModel(userEntity);
    user.setRole(role);
    return user;
  }

  @Override
  @Transactional
  public List<User> getAll() {
    return DomainService.super.getAll();
  }

  @Override
  @Transactional
  public List<User> getAll(Integer page, Integer pageSize) {
    return DomainService.super.getAll(page, pageSize);
  }

  @Override
  @Transactional
  @Caching(
      evict = {
          @CacheEvict(cacheNames = "user", key = "#id"),
          @CacheEvict(cacheNames = "users", allEntries = true)
      }
  )
  public void delete(Long id) {
    UserEntity userEntity = this.userPort.get(
        ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(UserEntity_.USER_ID),
            id))).orElseThrow(() -> USER_NOT_FOUND);
    this.userPort.delete(userEntity);
  }

  @Override
  @Transactional
  public List<User> getAll(Pageable pageable) {
    List<UserEntity> userEntities = this.userPort.getAll(
        ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()), pageable);
    List<Role> roles = this.roleService.getAll();
    return userEntities.stream().map(currentUser -> {
      Role role = roles.stream()
          .filter(currentRole -> currentRole.getRoleId().equals(currentUser.getRoleId()))
          .findFirst().orElseThrow(() -> USER_NOT_FOUND);
      User user = convertEntityToModel(currentUser);
      user.setRole(role);
      return user;
    }).toList();
  }

  @Override
  public User convertEntityToModel(UserEntity entity) {
    return this.modelMapper.map(entity, User.class);
  }

}
