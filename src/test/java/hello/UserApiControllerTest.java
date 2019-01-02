package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void getUsers() throws Exception {
        userRepository.save(new User("user1"));
        userRepository.save(new User("user2"));

        mockMvc.perform(get("/api/users"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name").value("user1"))
                .andExpect(jsonPath("$[1].name").value("user2"));
    }

    @Test
    public void createuser() throws Exception {
        //language=JSON
        String json = "{\n" +
                "  \"name\":  \"newname\"\n" +
                "}";

        mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty());

        assertThat(userRepository.findByName("newname").size()).isGreaterThan(0);
    }
}