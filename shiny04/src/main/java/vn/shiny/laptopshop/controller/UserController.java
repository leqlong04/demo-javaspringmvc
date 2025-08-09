package vn.shiny.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.shiny.laptopshop.domain.User;
import vn.shiny.laptopshop.repository.UserRepository;
import vn.shiny.laptopshop.service.*;

@Controller
public class UserController {

    // DI
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("leq.long2012@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("lql", "test");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "/admin/user/table-user";
    }

    @RequestMapping("/admin/user/{userId}")
    public String getUserDetailPage(Model model, @PathVariable Long userId) {
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        return "/admin/user/user-detail";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User user) {

        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{userId}")
    public String updateUserPage(Model model, @PathVariable Long userId) {
        User currUser = this.userService.getUserById(userId);
        model.addAttribute("newUser", currUser);
        return "admin/user/user-update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User user) {
        User currUser = this.userService.getUserById(user.getId());
        if (currUser != null) {
            currUser.setFullName(user.getFullName());
            currUser.setPhone(user.getPhone());
            currUser.setAddress(user.getAddress());

            this.userService.handleSaveUser(currUser);
        }

        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{userId}")
    public String deleteUserPage(Model model, @PathVariable Long userId) {
        model.addAttribute("id", userId);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User user) {

        this.userService.deleteUserById(user.getId());
        return "redirect:/admin/user";
    }

}
