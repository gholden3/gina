package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/users")
class UserRestController {

    private final UserRepository userRepository;

    @Autowired
    UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getUsers(Model model) {
        Collection<User> users = this.userRepository.findAll();
        model.addAttribute("users", users);
        return "indexUsers";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        User newUser = new User(user.getName());
        userRepository.save(newUser);
        return "redirect:/users";
    }

    @GetMapping(value = "/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "newUser";
	}
}
