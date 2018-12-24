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
    public String createUser(@ModelAttribute User user) {
        User newUser = new User(user.getName());
        userRepository.save(newUser);
        return "newUser";
    }

//	@PutMapping
//	public ItemEntity updateItem(@ModelAttribute ItemEntity item) {
//		ItemEntity i = userRepository.findOne(item.getId());
//		i.setName(item.getName());
//		i.setDescription(item.getDescription());
//		itemsRepository.save(i);
//		return i;
//	}

//	@GetMapping( value = "/{itemId}")
//	public ItemEntity readItem(@PathVariable Long itemId, Model model) {
//		this.validateItem(itemId);
//		ItemEntity item = this.itemsRepository.findOne(itemId);
//		return item;
//	}

    @GetMapping(value = "/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "newUser";
	}

//	@GetMapping(value = "/edit/{itemId}")
//	public String editItem(@PathVariable Long itemId, Model model) {
//		ItemEntity item = itemsRepository.findOne(itemId);
//		model.addAttribute("item", item);
//		return "editItem";
//	}

//	@PostMapping(value = "/delete/{itemId}")
//	public String deleteItem(@PathVariable Long itemId) {
//		itemsRepository.delete(itemId);
//		return "redirect:/items";
//	}

//    private void validateItem(Long itemId) {
//        ItemEntity item = itemsRepository.findOne(itemId);
//        if(item == null) throw new ItemNotFoundException(itemId);
//    }
}
// end::code[]
