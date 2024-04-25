package kz.iitu.intercitybustransportation.service.impl;

import jakarta.transaction.Transactional;
import kz.iitu.intercitybustransportation.dto.SignupDTO;
import kz.iitu.intercitybustransportation.dto.UserDTO;
import kz.iitu.intercitybustransportation.exceptions.DuplicateException;
import kz.iitu.intercitybustransportation.exceptions.ResourceNotFoundException;
import kz.iitu.intercitybustransportation.mapper.UserMapper;
import kz.iitu.intercitybustransportation.model.Role;
import kz.iitu.intercitybustransportation.model.User;
import kz.iitu.intercitybustransportation.repository.UserRepository;
import kz.iitu.intercitybustransportation.service.RoleService;
import kz.iitu.intercitybustransportation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleService roleService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void signup(SignupDTO signupDTO) {
        String email = signupDTO.email();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new DuplicateException(String.format("User with the email address '%s' already exists.", email));
        }

        String hashedPassword = passwordEncoder.encode(signupDTO.password());
        User user = new User(signupDTO.name(), email, hashedPassword);
        userRepository.save(user);
    }

    @Override
    public UserDTO createBusOperator(UserDTO userDto) {
        User user = userMapper.toEntity(userDto);
        String email = userDto.getEmail();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new DuplicateException(String.format("User with the email address '%s' already exists.", email));
        }
        Role employeeRole = roleService.findByName("MANAGER");
        Role customerRole = roleService.findByName("USER");

        Set<Role> roleSet = new HashSet<>();
        if (employeeRole != null) {
            roleSet.add(employeeRole);
        }
        if (customerRole != null) {
            roleSet.add(customerRole);
        }
        user.setRoles(roleSet);

        user.setUsername(userDto.getUsername());
        user.setEmail(email);

        userRepository.save(user);

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public void giveAuthority(Long id) {

    }


    @Override
    public UserDTO getUser(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = userMapper.toEntity(userDto);
        String email = userDto.getEmail();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new DuplicateException(String.format("User with the email address '%s' already exists.", email));
        }

        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);


        // If email domain is admin.com, add ADMIN role
        if(user.getEmail().split("@")[1].equals("admin.com")){
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }

        user.setRoles(roleSet);

        user.setUsername(userDto.getUsername());
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDto.getUsername());
                    user.setEmail(userDto.getEmail());
                    user.setPhoneNumber(userDto.getPhoneNumber());
                    user.setPassword(userDto.getPassword());
                    // Update the fields of the user as per your requirements
                    User updatedUser = userRepository.save(user);
                    return userMapper.toDto(updatedUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new ResourceNotFoundException("User not found with id " + id);
                });
    }


}
