package tbspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tbspring.models.User;
import tbspring.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class MainSpringController {
    @Autowired
    UserRepository repository;

    @GetMapping("/login")
    public String login(Model model) {
        return ("login");
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "name", required = true) String name,
                           @RequestParam(name = "password") String password,
                           Model model){
        return "redirect:/main";
    }
    @GetMapping("/main")
    public String main(Model model) {
        return ("main");
    }

}
