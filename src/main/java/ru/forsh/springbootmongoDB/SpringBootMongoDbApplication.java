package ru.forsh.springbootmongoDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringBootMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(
            StudentRepository repository,
            MongoTemplate mongoTemplate) {
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

           // usingMongoTemplate(repository, mongoTemplate, email, student);
            repository.findStudentByEmail(email).ifPresentOrElse(s -> {
                System.out.println(student + " already exists");
            } , () -> {
                System.out.println("inserting " + student);
                repository.insert(student);
            });
        };
    }

    private void usingMongoTemplate(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.size() > 1) {
            throw new IllegalStateException("found many students with email " + email);
        }

        if (students.isEmpty()) {
            System.out.println("inserting " + student);
            repository.insert(student);
        } else {
            System.out.println(student + " already exists");
        }
    }

}
