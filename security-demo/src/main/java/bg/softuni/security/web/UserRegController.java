package bg.softuni.security.web;

import bg.softuni.security.model.dto.UserRegDTO;
import bg.softuni.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserRegController {
    private final UserService userService;
    private  final SecurityContextRepository contextRepository;

    public UserRegController(UserService userService, SecurityContextRepository contextRepository) {
        this.userService = userService;
        this.contextRepository = contextRepository;
    }

    @GetMapping("/users/register")
    public String register(){
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String registerNewUser(UserRegDTO regDTO,
                                  HttpServletRequest request,
                                  HttpServletResponse response){

        userService.registerUser(regDTO, authentication -> {
            //populating security context
            SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();
            SecurityContext context = strategy.createEmptyContext();
            context.setAuthentication(authentication);
            strategy.setContext(context);
            contextRepository.saveContext(context, request, response);
        });

        return "redirect:/users/login";
    }
}
