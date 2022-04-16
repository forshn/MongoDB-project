package ru.forsh.springbootmongoDB;

import org.apache.naming.factory.SendMailFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class SpringBootMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository) {
        return args -> {
            var address = new Address(
                    "Russia",
                    "199106",
                    "Moscow"
            );

            String email = "akhmed@mail.ru";
            var student = new Student(
                    "Jamila",
                    "Akhmed",
                    email,
                    Gender.FEMALE,
                    address,
                    List.of("it", "music", "math", "literature"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );

            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            repository.insert(student);
        };
    }

}
