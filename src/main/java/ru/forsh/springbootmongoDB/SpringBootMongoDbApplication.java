package ru.forsh.springbootmongoDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
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

            var student = new Student(
                    "Jamila",
                    "Akhmed",
                    "akhmed@mail.ru",
                    Gender.FEMALE,
                    address,
                    List.of("it", "music", "math"),
                    BigDecimal.TEN,
                    ZonedDateTime.now()
            );
        };
    }

}
