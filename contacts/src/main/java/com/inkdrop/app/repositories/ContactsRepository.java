package com.inkdrop.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inkdrop.models.Contact;

public interface ContactsRepository extends CrudRepository<Contact, Long>{}
