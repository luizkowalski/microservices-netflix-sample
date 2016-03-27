package com.inkdrop.components;

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
	public ResponseEntity<String> saveUser(String userJson){
		HttpEntity<String> entity = createEntity(userJson);
		ResponseEntity<String> resp = post(getUrl(), "/contacts/new", entity);
		return new ResponseEntity<String>(resp.getBody(), resp.getStatusCode());
	}

	@HystrixCommand(fallbackMethod="getUsersFallback")
	public ResponseEntity<String> getUsers(){
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
