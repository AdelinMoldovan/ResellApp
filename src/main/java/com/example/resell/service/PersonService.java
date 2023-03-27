package com.example.resell.service;

import com.example.resell.dataAccessObject.PersonRepository;
import com.example.resell.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * In aceasta metoda am implementat apelul de get care returneaza toate persoanele din baza
     * de date a proiectului
     *
     * @return list cu toate persoanele gasite
     */
    public List<Person> getPersons() {
//        return List.of(
//                new Person(
//                        1L,
//                        "Adelin",
//                        "adelin123",
//                        "moldovanadelin111@gmail.com",
//                        "adelin123",
//                        AppPersonRole.USER
//                )
//        );
        return personRepository.findAll();

    }

    /**
     * Metoda addNewPerson este metoda pe care am folosit-o pentru a face api call-ul de post,
     * care are rolul de a adauga o noua persoana in baza de date cu datele trimise in apelul
     * de post
     *
     * @param person persoana care va fi adaugata
     *               la final va salva persoana in baza de date
     */
    public void addNewPerson(Person person) {
        Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
        if (personOptional.isPresent()) {
            throw new IllegalStateException("This email is taken.");
        }
        personRepository.save(person);

//        Optional<Person> personOptionalUsername = personRepository.findPersonByUsername(person.getUsername());
//        if(personOptionalUsername.isPresent()){
//            throw new IllegalStateException("This username is taken.");
//        }
//        personRepository.save(person);
        //System.out.println(person);
    }

    /**
     * Metoda deletePerson este metoda pe care am folosit-o pentru a face api call-ul de delete,
     * care are rolul de a sterge o persoana din baza de date dupa id-ul trimis spre call
     *
     * @param personId id-ul personei care va fi stearsa
     *                 la final va sterge persoana din baza de date
     */
    public void deletePerson(Long personId) {
        boolean personExist = personRepository.existsById(personId);
        if (!personExist) {
            throw new IllegalStateException(
                    "Perosn with id " + personId + " does not exist.");
        }
        personRepository.deleteById(personId);
    }

    /**
     * Metoda updatePerson este metoda pe care am folosit-o pentru a face api call-ul de put,
     * care are rolul de a face update la o persoana deja existenta in baza de date.
     * Cu acest call se poate modifica username-ul, email-ul si password-ul, ale personale identificate
     * dupa id-ul dat.
     *
     * @param personId id-ul personei care va fi modificata
     *                 la final se va face update-ul
     */

    @Transactional
    public void updatePerson(long personId,
                             String username,
                             String email,
                             String password) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException(
                        "Person with id " + personId + " does not exist"));
        if (username != null &&
                username.length() > 0 &&
                !Objects.equals(person.getUsername(), username)) {
//            Optional<Person> personOptionalUsername = personRepository.findPersonByUsername(person.getUsername());
//            if(personOptionalUsername.isPresent()){
//                throw new IllegalStateException("This username is taken.");
//            }
            person.setUsername(username);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(person.getEmail(), email)) {
            Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
            if (personOptional.isPresent()) {
                throw new IllegalStateException("This email is taken.");
            }
            person.setEmail(email);
        }

        if (password != null &&
                email.length() > 0 &&
                !Objects.equals(person.getPassword(), password)) {
            person.setPassword(password);
        }


    }
}
