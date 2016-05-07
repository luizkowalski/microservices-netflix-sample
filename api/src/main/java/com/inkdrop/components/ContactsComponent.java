package com.inkdrop.components;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class ContactsComponent extends BasicComponent {

	 @Autowired
	 private LoadBalancerClient loadBalancer;

	@HystrixCommand(fallbackMethod="saveUserFallback")
	public ResponseEntity<String> saveContact(String contact){
		HttpEntity<String> entity = createEntity(contact);
		ResponseEntity<String> resp = post(getUrl(), "/contacts/new", entity);
		return new ResponseEntity<String>(resp.getBody(), resp.getStatusCode());
	}

	@HystrixCommand(fallbackMethod="getUsersFallback")
	public ResponseEntity<String> getContacts() throws IOException{
		ResponseEntity<String> resp = get(getUrl(), "/contacts");
		return new ResponseEntity<String>(resp.getBody(), resp.getStatusCode());
	}

	public ResponseEntity<String> saveUserFallback(String user){
		return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> getUsersFallback(){
		return new ResponseEntity<String>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private String getUrl(){
		return loadBalancer.choose("contacts-service").getUri().toString();
	}

}
