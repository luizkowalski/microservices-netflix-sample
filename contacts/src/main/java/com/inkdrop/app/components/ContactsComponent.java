package com.inkdrop.app.components;

import com.inkdrop.app.models.Contact;
import com.inkdrop.app.repositories.ContactsRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactsComponent {

  @Autowired
  ContactsRepository repository;

  @HystrixCommand(fallbackMethod = "getContactsFallback")
  public Iterable<Contact> getContacts() throws IOException {
    return repository.findAll();
  }

  public Iterable<Contact> getContactsFallback() {
    return Arrays.asList(new Contact(0L, "Fallback user", "0000"));
  }
}
