package my.pet.ticket.controllers;

import my.pet.ticket.dataLayer.dao.interfaces.UserRepository;
import my.pet.ticket.dataLayer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // Метод для получения всех пользователей
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Метод для создания нового пользователя
    @PostMapping
    public User createUser(@RequestBody User user) {
        // Валидация и сохранение пользователя
        return userRepository.save(user);
    }
}
