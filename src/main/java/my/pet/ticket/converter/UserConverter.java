package my.pet.ticket.converter;

import my.pet.ticket.dataLayer.model.User;
import my.pet.ticket.dataLayer.model.dto.UserDTO;

public class UserConverter {
    // Преобразование User в UserDTO
    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }

    // Преобразование UserDTO в User
    public static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        return user;
    }
}
