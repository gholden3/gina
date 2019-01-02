package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("name", "@gina");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        restTemplate.postForEntity("http://localhost:" + port + "/users", request, String.class);
        assertThat(userRepository.findByName("@gina").size()).isGreaterThan(0);
    }
}