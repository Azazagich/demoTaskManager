package org.example.demotaskmanager.controller;

import org.example.demotaskmanager.service.UserService;
import org.example.demotaskmanager.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public UserDTO getRole(@PathVariable Long id){
        return userService.getById(id);
    }


    @GetMapping
    public List<UserDTO> getAllRoles(){
        return userService.getAll();
    }


    @PostMapping
    public UserDTO saveRole(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }


    @PutMapping("/{id}")
    public boolean fullUpdateRole(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return userService.updateAll(id, userDTO);
    }


    @PatchMapping("/{id}")
    public boolean partialUpdateRole(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return userService.update(id, userDTO);
    }

    //TODO
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id){
        userService.deleteById(id);
    }
}
