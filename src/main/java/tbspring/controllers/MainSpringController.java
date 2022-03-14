package tbspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tbspring.repository.UserRepo;


@Controller
public class MainSpringController {

    @Autowired
    UserRepo repository;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "name", required = true) String name,
                        @RequestParam(name = "password") String password,
                        Model model) {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String error(Model model) {
        return "main";
    }

}
