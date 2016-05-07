package com.inkdrop.components;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BasicComponent {

	protected RestTemplate rest = new RestTemplate();

	protected HttpEntity<String> createEntity(String entity){
		return new HttpEntity<String>(entity, getDefaultHeader());
	}

	private HttpHeaders getDefaultHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	protected ResponseEntity<String> post(String url, String endpoint, HttpEntity<String> entity){
		return rest.postForEntity(url.concat(endpoint), entity, String.class);
	}

	protected ResponseEntity<String> get(String url, String endpoint){
		return rest.getForEntity(url.concat(endpoint), String.class);
	}



}
