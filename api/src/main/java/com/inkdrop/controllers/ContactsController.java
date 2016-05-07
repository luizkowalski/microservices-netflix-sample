package com.inkdrop.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.inkdrop.components.ContactsComponent;

@RestController
@EnableAutoConfiguration
public class ContactsController {

	@Autowired
	ContactsComponent component;

	@RequestMapping(method=RequestMethod.POST, path="/contacts/new")
	public ResponseEntity<String> saveUser(@RequestBody String contact){
		return component.saveContact(contact);
	}

	@RequestMapping(method=RequestMethod.GET, path="/contacts")
	public ResponseEntity<String> getUsers() throws JsonParseException, JsonMappingException, IOException{
		return component.getContacts();
	}
}
