package my.pet.ticket.server.application.domain.service;

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
  void registerUser() {
    User user = this.userService.registerUser(RegisterUserRequest.builder()
        .login("testlogin")
        .password("passwordhash")
        .fullName("Eryomin Egor Konstantinovich")
        .build());
    Assertions.assertNotNull(user.getUserId());
  }

  @Test
  void activateUser() {
    User user = this.userService.activateUser(1001L);
    Assertions.assertTrue(user.getActive());
  }

}