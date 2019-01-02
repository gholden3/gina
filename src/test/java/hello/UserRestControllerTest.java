package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void indexShouldReturnAListOfAllUsers() throws Exception {
        userRepository.save(new User("@user1"));
        userRepository.save(new User("@user2"));

        String resultText = restTemplate.getForObject("http://localhost:" + port + "/users", String.class);
        assertThat(resultText)
                .contains("User name: @user1")
                .contains("User name: @user2");
    }

    @Test
    public void newShouldReturnAForm() throws Exception {
        String resultText = restTemplate.getForObject("http://localhost:" + port + "/users/new", String.class);
        assertThat(resultText)
                .contains("Add A New User");
    }

    @Test
    public void createUserSavesUser() {
        HttpEntity<User> httpEntity = new HttpEntity<>(new User("@gina"));

        restTemplate.postForEntity("http://localhost:" + port + "/users", httpEntity, String.class);
        assertThat(userRepository.findByName("@gina").size()).isGreaterThan(0);
    }
}