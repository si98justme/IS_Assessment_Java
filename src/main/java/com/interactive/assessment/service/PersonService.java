package com.interactive.assessment.service;

import com.interactive.assessment.models.Person;
import com.interactive.assessment.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
     
    @Autowired
    PersonRepository repository;
     
    public List<Person> getAllPersons()
    {
        List<Person> result = repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Person>();
        }
    }
     
    public Person getPersonById(Long id) throws NotFoundException
    {
        Optional<Person> person = repository.findById(id);
         
        if(person.isPresent()) {
            return person.get();
        } else {
            throw new NotFoundException("No Person record exist for given id");
        }
    }
     
    public Person createOrUpdatePerson(Person entity) 
    {
        if(entity.getId()  == null) 
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<Person> person = repository.findById(entity.getId());
             
            if(person.isPresent()) 
            {
                Person newEntity = person.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deletePersonById(Long id) throws NotFoundException 
    {
        Optional<Person> Person = repository.findById(id);
         
        if(Person.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("No Person record exist for given id");
        }
    } 
}