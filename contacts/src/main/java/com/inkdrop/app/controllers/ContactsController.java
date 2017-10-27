package com.inkdrop.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inkdrop.app.components.ContactsComponent;
import com.inkdrop.app.models.Contact;
import com.inkdrop.app.repositories.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ContactsController {

	@Autowired
	ContactsRepository repository;

	@Autowired
	ContactsComponent component;

	@RequestMapping(method=RequestMethod.POST, path="/contacts/new")
	public ResponseEntity<String> saveUser(@RequestBody String user) throws Exception {
		try{
			ObjectMapper objectMapper = new ObjectMapper();
			Contact contact = objectMapper.readValue(user, Contact.class);
			contact = repository.save(contact);
			return new ResponseEntity<String>(objectMapper.writeValueAsString(contact), HttpStatus.CREATED);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

  @RequestMapping(method = RequestMethod.GET, path = "/contacts")
  public ResponseEntity<Iterable<Contact>> getUsers() throws Exception {
    return new ResponseEntity<>(component.getContacts(), HttpStatus.OK);
  }
}
