package org.example.demotaskmanager.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.demotaskmanager.domain.Role;
import org.example.demotaskmanager.domain.User;
import org.example.demotaskmanager.repository.UserRepository;
import org.example.demotaskmanager.service.dto.UserDTO;
import org.example.demotaskmanager.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO getById(Long id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow());
    }

    public List<UserDTO> getAll() {
        return userMapper.toDTOS(userRepository.findAll());
    }

    public UserDTO save(UserDTO userDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    public boolean updateAll(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow();
        userMapper.fullUpdate(userDTO, user);
        userRepository.save(user);
        return true;
    }

    public boolean update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow();
        userMapper.partialUpdate(userDTO, user);
        userRepository.save(user);
        return true;
    }

    public void deleteById(Long id) {
//        User user = userRepository.findById(id).orElseThrow();
//        Role role = user.getRole();
//        role.setUsers(null);
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }
}
