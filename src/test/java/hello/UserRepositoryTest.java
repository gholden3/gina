package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void canSaveAUser() {
        User user = new User("@ginaeatsguac");
        user = userRepository.save(user);

        List<User> found = userRepository.findAll();

        assertThat(found).contains(user);
    }

    @Test
    public void canFindAUserByName() {
        User user = new User("@ginaeatsguac");
        user = userRepository.save(user);

        List<User> found = userRepository.findByName(user.getName());

        assertThat(found).contains(user);
    }
}