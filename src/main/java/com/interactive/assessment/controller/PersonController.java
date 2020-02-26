package com.interactive.assessment.controller;

import com.interactive.assessment.models.Person;
import com.interactive.assessment.service.PersonService;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class PersonController {

	@Autowired
	private PersonService service;

	@RequestMapping
	public String getAllPersons(Model model) {
		List<Person> list = service.getAllPersons();

		model.addAttribute("persons", list);
		return "list-persons";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editPersonById(Model model, @PathVariable("id") Optional<Long> id) throws NotFoundException {
		if (id.isPresent()) {
			Person entity = service.getPersonById(id.get());
			model.addAttribute("person", entity);
		} else {
			model.addAttribute("person", new Person());
		}
		return "add-edit-person";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deletePersonById(Model model, @PathVariable("id") Long id) throws NotFoundException
                            
    {
        service.deletePersonById(id);
        return "redirect:/";
    }
 
    @RequestMapping(path = "/createPerson", method = RequestMethod.POST)
    public String createOrUpdatePerson(Person person) 
    {
        service.createOrUpdatePerson(person);
        return "redirect:/";
    }
}