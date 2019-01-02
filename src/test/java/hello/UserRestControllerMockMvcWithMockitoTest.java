package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserRestControllerMockMvcWithMockitoTest {
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    private UserRestController userRestController;

    @Before
    public void setUp() throws Exception {
        userRestController = new UserRestController(userRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("name=controllerTest"));

        verify(userRepository).save(eq(new User("controllerTest")));
    }
}
