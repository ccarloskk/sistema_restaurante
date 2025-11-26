package com.manager_restaurant.restaurant_manager.service;


import com.manager_restaurant.restaurant_manager.model.Users;
import com.manager_restaurant.restaurant_manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> listUsers() {
        return userRepository.findAll();
    }

    public Users searchUser(Long id_user) {
        return userRepository.findById(Long.valueOf(id_user))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id_user) {
        userRepository.deleteById(Long.valueOf(id_user));
    }

    public Users updateUser(Long id_user , Users updatedUser) {
        Users existingUser = searchUser(id_user);
        existingUser.setUser_name(updatedUser.getUser_name());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());
        return userRepository.save(existingUser);
    }
}
