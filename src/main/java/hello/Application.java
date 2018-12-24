package hello;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            repository.save(new User("Jack"));
            repository.save(new User("Chloe"));
            repository.save(new User("Kim"));
            repository.save(new User("David"));
            repository.save(new User("Michelle"));

            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual user by ID
            repository.findById(1L)
                    .ifPresent(user -> {
                        log.info("User found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(user.toString());
                        log.info("");
                    });

            // fetch users by  name
            log.info("User found with findByName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
             for (User bauer : repository.findByName("Bauer")) {
             	log.info(bauer.toString());
             }
            log.info("");
        };
    }
}

