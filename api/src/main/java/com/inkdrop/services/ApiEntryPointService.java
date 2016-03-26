package com.inkdrop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ApiEntryPointService {

	@LoadBalanced
	private RestTemplate rest = new RestTemplate();

	 @Autowired
    private LoadBalancerClient loadBalancer;

	@HystrixCommand(fallbackMethod="saveUserFallback")
	public ResponseEntity<String> saveUser(String userJson){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(userJson, headers);
		System.err.println(entity);
		ResponseEntity<String> resp = rest.postForEntity(getUrl()+ "/create", entity, String.class);
		return new ResponseEntity<>(resp.getBody(), resp.getStatusCode());
	}

	public ResponseEntity<String> saveUserFallback(String user){
		return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private String getUrl(){
		return loadBalancer.choose("contacts-service").getUri().toString();
	}

}
