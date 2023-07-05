package ru.batorov.springmvc.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.batorov.springmvc.models.Person;
import ru.batorov.springmvc.repositories.PeopleRepository;



@Component
public class PersonValidator implements Validator{

    private final PeopleRepository peopleRepository;
    
    @Autowired
    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    //показывает к какой сущности относится валидатор
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        //есть ли человек с таким же ФИО
        Optional<Person> opPerson = peopleRepository.findByFullName(person.getFullName()).stream().findAny();
        if (opPerson.isPresent() && person.getPersonId() != opPerson.get().getPersonId())
        {
            errors.rejectValue("fullName", "", "this full_name is already taken");
        }

    }  
}
