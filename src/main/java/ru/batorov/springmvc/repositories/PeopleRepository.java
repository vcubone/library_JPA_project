package ru.batorov.springmvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.batorov.springmvc.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{
    List<Person> findByFullName(String name);
}
