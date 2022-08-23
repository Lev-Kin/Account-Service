package account.controller;

import account.business.data.User;
import account.business.data.UserRoles;
import account.business.service.AuthService;
import account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserRepository users;
    @Autowired
    PasswordEncoder encoder;
    private AuthService authService;

    @PostMapping("/signup")
    public UserRoles signup(@Valid @RequestBody User user) {
        return authService.signup(user);
    }

    @PostMapping("/changepass")
    public Changepass changepass(@RequestBody NewPassword passwordBody, @AuthenticationPrincipal UserDetails details) {
        return authService.changepass(passwordBody, details);
    }
}

