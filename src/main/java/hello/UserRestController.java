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

    @CrossOrigin
    @GetMapping
    public String getUsers(Model model) {
        Collection<User> users = this.userRepository.findAll();
        model.addAttribute("users", users);
        return "indexUsers";
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        User newUser = new User(user.getName());
        userRepository.save(newUser);
        return "newUser";
    }

    @GetMapping(value = "/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "newUser";
	}
}
