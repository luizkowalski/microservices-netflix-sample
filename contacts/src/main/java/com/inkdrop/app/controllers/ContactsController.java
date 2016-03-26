package com.inkdrop.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inkdrop.app.repositories.ContactsRepository;

@RestController
@EnableAutoConfiguration
public class ContactsController {

	@Autowired
	ContactsRepository repository;

	@RequestMapping(method=RequestMethod.POST, path="/create")
	public ResponseEntity<String> saveUser(@RequestBody String user){
		// TODO
		System.err.println("Calling: "+Thread.currentThread().getId());
		return new ResponseEntity<String>("{ 'created': true }", HttpStatus.CREATED);
	}
}
