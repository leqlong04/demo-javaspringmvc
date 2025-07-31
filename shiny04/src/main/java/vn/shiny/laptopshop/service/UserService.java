package vn.shiny.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.shiny.laptopshop.domain.User;
import vn.shiny.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }
}
