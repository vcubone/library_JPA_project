package ru.batorov.springmvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.batorov.springmvc.models.Person;
import ru.batorov.springmvc.repositories.PeopleRepository;


@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    
    public List<Person> all()
    {
        return peopleRepository.findAll();
    }
    
    public Person show(int person_id)
    {
        return peopleRepository.findById(person_id).orElse(null);
    }
    
    @Transactional
    public void save(Person person)
    {
        peopleRepository.save(person);
    }
    
    @Transactional
    public void update(int person_id, Person person)
    {
        person.setPersonId(person_id);
        peopleRepository.save(person);
    }
    
    @Transactional
    public void delete(int person_id)
    {
        peopleRepository.deleteById(person_id);
    }
}
