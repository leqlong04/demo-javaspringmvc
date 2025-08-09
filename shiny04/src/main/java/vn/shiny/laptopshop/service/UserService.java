package vn.shiny.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.shiny.laptopshop.domain.User;
import vn.shiny.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {

        return this.userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user) {

        return this.userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id) {

        this.userRepository.deleteById(id);

    }
}
