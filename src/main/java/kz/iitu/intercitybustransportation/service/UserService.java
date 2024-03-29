package kz.iitu.intercitybustransportation.service;


import kz.iitu.intercitybustransportation.dto.SignupDTO;
import kz.iitu.intercitybustransportation.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUser(Long id);

    List<UserDTO> getAllUsers();

    UserDTO createUser(UserDTO userDto);

    UserDTO updateUser(Long id, UserDTO userDto);

    void deleteUser(Long id);

    void signup(SignupDTO signupDTO);

    UserDTO createBusOperator(UserDTO userDto);
    void giveAuthority(Long id);


}
