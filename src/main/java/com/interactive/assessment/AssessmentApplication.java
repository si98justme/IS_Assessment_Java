package com.interactive.assessment;

import com.interactive.assessment.models.Person;
import com.interactive.assessment.service.PersonService;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;








@SpringBootApplication
public class AssessmentApplication {

	private PersonService personService;

	public AssessmentApplication(PersonService personService){
		this.personService = personService;
	}
	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}
	
	@PostConstruct
    public void loadDummyData() {
		Person person = new Person();
		person.setFirstName("Justin");
		person.setLastName("White");
		person.setEmail("justin@test.com");
		personService.createOrUpdatePerson(person);
	}
	
	@PostConstruct
    public void getDummyData() {
		System.out.println(personService.getAllPersons().stream().map((p) -> p.getFirstName()).collect(Collectors.toList()));
    }
}
