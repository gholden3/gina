package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
class UserApiController {

    private final UserRepository userRepository;

    @Autowired
    UserApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

//    @PostMapping("/demo")
//    public ResponseEntity<User> otherExample(@RequestBody User user) {
//        userRepository.save(user);
//
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String> handleError(Exception e) {
//        return new ResponseEntity<String>("Error: asdasda", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
