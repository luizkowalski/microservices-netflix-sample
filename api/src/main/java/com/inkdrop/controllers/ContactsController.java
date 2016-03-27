package com.inkdrop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inkdrop.components.ContactsComponent;

@RestController
@EnableAutoConfiguration
public class ContactsController {

	@Autowired
	ContactsComponent service;

	@RequestMapping(method=RequestMethod.POST, path="/contacts/new")
	public ResponseEntity<String> saveUser(@RequestBody String user){
		return service.saveUser(user);
	}

	@RequestMapping(method=RequestMethod.GET, path="/contacts")
	public ResponseEntity<String> getUsers(){
		return service.getUsers();
	}
}
