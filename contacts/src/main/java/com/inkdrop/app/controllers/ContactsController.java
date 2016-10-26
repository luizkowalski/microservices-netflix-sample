package com.inkdrop.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inkdrop.app.components.ContactsComponent;
import com.inkdrop.app.models.Contact;
import com.inkdrop.app.repositories.ContactsRepository;

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

	@RequestMapping(method=RequestMethod.GET, path="/contacts")
	public ResponseEntity<String> getUsers() throws Exception {
		String users = mapper().writeValueAsString(component.getContacts());
		return new ResponseEntity<String>(users, HttpStatus.OK);
	}

	public ResponseEntity<String> getUsersFallback(){
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ObjectMapper mapper(){
		return new ObjectMapper();
	}
}
