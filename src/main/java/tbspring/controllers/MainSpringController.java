package tbspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tbspring.config.AuthProvider;
import tbspring.entities.UserEntity;


@Controller
public class MainSpringController {
    @Autowired
    AuthProvider authProvider;


    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
            model.addAttribute("error", error != null);
            model.addAttribute("logout", logout != null);
            return "login";
    }

    @GetMapping("/")
    public String authorized(Authentication auth) {
        UserEntity ue = (UserEntity) auth.getPrincipal();
        return "redirect:/" + ue.getUsername();
    }

    @GetMapping("/main")
    public String main(Model model) {
        return "main";
    }

}
