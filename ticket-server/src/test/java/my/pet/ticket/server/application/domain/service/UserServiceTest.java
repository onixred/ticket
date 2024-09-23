package my.pet.ticket.server.application.domain.service;

import java.util.List;
import my.pet.ticket.server.application.domain.model.User;
import my.pet.ticket.server.application.domain.model.payload.request.RegisterUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class UserServiceTest {

  @Autowired
  UserService userService;

  @Test
  void registerUserTest() {
    User user = this.userService.registerUser(RegisterUserRequest.builder()
        .login("testlogin1")
        .password("passwordhash")
        .fullName("Eryomin Egor Konstantinovich")
        .build());
    Assertions.assertNotNull(user.getUserId());
  }

  @Test
  void activateUserTest() {
    User user = this.userService.activateUser(1001L);
    Assertions.assertTrue(user.getActive());
  }

  @Test
  void getUserTest() {
    Assertions.assertDoesNotThrow(() -> {
      User user = this.userService.getUser(1001L);
      System.out.println("");
    });
  }

  @Test
  void getAllUsersTest() {
    List<User> users = this.userService.getAllUsers();
    Assertions.assertFalse(users.isEmpty());
  }

  @Test
  void getAllUsersPageableTest() {
    List<User> usersPage1 = this.userService.getAllUsers(0, 1);
    List<User> usersPage2 = this.userService.getAllUsers(1, 1);
    Assertions.assertNotEquals(usersPage1.get(0), usersPage2.get(0));
  }

  @Test
  void suspendUserTest() {
    User user = this.userService.suspendUser(1001L);
    Assertions.assertTrue(user.getSuspended());
  }

/*  @Test
  @Order(-1000)
  void deleteUser() {
    User user = this.userService.getUser(1001L);
    this.userService.deleteUser(user.getUserId());
    Assertions.assertThrows(PersistenceAdapterException.class, () -> {
      this.userService.getUser(1001L);
    });
  }*/

}