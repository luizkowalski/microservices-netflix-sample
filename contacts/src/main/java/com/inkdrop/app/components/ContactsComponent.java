package com.inkdrop.app.components;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.inkdrop.app.models.Contact;
import com.inkdrop.app.repositories.ContactsRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class ContactsComponent extends BasicComponent {

	@Autowired
	ContactsRepository repository;

	@HystrixCommand(fallbackMethod="getUsersFallback")
	public Iterable<Contact> getContacts() throws IOException{
		return repository.findAll();
	}

	public ResponseEntity<String> saveUserFallback(String user){
		return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public Iterable<Contact> getUsersFallback(){
		return new ArrayList<>();
	}


}
