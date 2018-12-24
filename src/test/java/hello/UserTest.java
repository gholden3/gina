//import org.junit.runner.RunWith;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.junit.*;
//import org.junit.runner.*;
//import org.springframework.boot.test.autoconfigure.orm.jpa.*;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UserTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void userTest() {
//        User user = new User("@ginaeatsguac");
//        entityManager.persist(user);
//        entityManager.flush();
//
//        User found=userRepository.findByName(user.getName());
//
//        assertThat(found.getName())
//                .isEqualTo(user.getName());
//    }
//}