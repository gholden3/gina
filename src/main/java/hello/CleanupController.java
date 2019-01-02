package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clean")
public class CleanupController {
    private final UserRepository userRepository;

    @Autowired
    CleanupController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String clean() {
        userRepository.deleteAll();
        return "success";
    }
}
