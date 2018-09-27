package lt.bt.security.security.web;

import lt.bt.security.security.entity.User;
import lt.bt.security.security.entity.dto.UserDto;
import lt.bt.security.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/registration")
    public String registrationForm(UserDto user, Model model) {
        return "user/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createUser(@Valid UserDto user, BindingResult errors, Model model) {
        System.out.println(errors);

        if (errors.hasErrors()) {
            return "user/registration";
        }
        service.createUser(new User(user));


        return "user/created";
    }

    @RequestMapping("/profile")
    public String userProfile(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        model.addAttribute("name", name);
        model.addAttribute("authorities", authorities);

        return "user/profile";
    }
}
