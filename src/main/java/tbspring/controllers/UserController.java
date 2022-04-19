package tbspring.controllers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tbspring.entities.Role;
import tbspring.entities.UserEntity;
import tbspring.models.User;
import tbspring.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/registration")
    public String registration(Model model) {
        List<Role> roles = Arrays.asList(Role.values());
        model.addAttribute("roles", roles);
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@RequestParam(value = "error", required = false) String error,
                           UserEntity user,
                           Model model) {
        try {
            userService.saveUser(user);
            return "redirect:/login";
        } catch (ConstraintViolationException e) {
            model.addAttribute("error", error != null);
            String cause = e.getCause().getMessage();
            model.addAttribute("cause", cause);
            return "registration";
        }
    }

    @GetMapping("/{username}")
    @PreAuthorize("#username == principal.username")
    public String showUser(@PathVariable String username,
                           Authentication auth,
                           Model model) {
        UserEntity ue = (UserEntity) auth.getPrincipal();
        model.addAttribute("user", ue);
        return ("user");
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users")
    public ResponseEntity<Collection<User>> showAllUsers() {
        Collection<User> response = new ArrayList<>();
        for (UserEntity e : userService.getAllUsers()) {
            response.add(User.toModel(e));
        }
        return ResponseEntity.ok(response);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
