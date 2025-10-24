package com.manager_restaurant.restaurant_manager.controller;


import com.manager_restaurant.restaurant_manager.model.Users;
import com.manager_restaurant.restaurant_manager.repository.UserRepository;
import com.manager_restaurant.restaurant_manager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( "*")
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private UserRepository userRepository;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity listUsers() {
        List<Users> users = userService.listUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody Users user){
        this.userRepository.save(user);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/{id_user}")
    public Users updateuser(@PathVariable String id_user, @RequestBody Users user){
        return userService.updateUser(id_user, user);
    }

    @DeleteMapping("/{id_user}")
    public void deleteUser (@PathVariable String id_user){
        userService.deleteUser(id_user);
    }
}
