package kz.iitu.intercitybustransportation.mapper;

import kz.iitu.intercitybustransportation.dto.SignupDTO;
import kz.iitu.intercitybustransportation.dto.UserDTO;
import kz.iitu.intercitybustransportation.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPassword(user.getPassword());
        // map other fields
        return userDto;
    }

    public User toEntity(UserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        // map other fields
        return user;
    }

    public User toEntity(SignupDTO userDto) {
        User user = new User();
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        // map other fields
        return user;
    }
}