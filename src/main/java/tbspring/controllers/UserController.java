package tbspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbspring.entities.UserEntity;
import tbspring.models.User;
import tbspring.services.UserService;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> showUser(@RequestParam long id) {
        try {
            return ResponseEntity.ok(User.toModel(userService.getUser(id)));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Collection<User>> showAllUsers() {
        Collection<User> response = new ArrayList<>();
        for (UserEntity e : userService.getAllUsers()) {
            response.add(User.toModel(e));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserEntity user) {
        try {
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
