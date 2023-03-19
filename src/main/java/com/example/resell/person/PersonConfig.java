package com.example.resell.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
        return args -> {
            Person adelin =new Person(
                        "Adelin",
                        "adelin123",
                        "moldovanadelin111@gmail.com",
                        "adelin123",
                        AppPersonRole.USER
            );

            Person bogdan =new Person(
                    "Bogdan",
                    "bogdan123",
                    "bogdan123@gmail.com",
                    "bogdan123",
                    AppPersonRole.ADMIN
            );

            repository.saveAll(
                    List.of(adelin, bogdan)
            );
        };
    }
}
