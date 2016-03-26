package com.inkdrop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inkdrop.services.ApiEntryPointService;

@RestController
@EnableAutoConfiguration
public class ContactsController {

	@Autowired
	ApiEntryPointService service;

	@RequestMapping(method=RequestMethod.POST, path="/create")
	public ResponseEntity<String> saveUser(@RequestBody String user){
		return service.saveUser(user);
	}
}
