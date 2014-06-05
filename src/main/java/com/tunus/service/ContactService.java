package com.tunus.service;

import java.util.List;

import com.tunus.domain.Contact;

public interface ContactService {
	
	public Contact findById( Integer id );
    public List<Contact> findAll();
    public void save( Contact contact );
    public void update( Contact contact );
    public void delete( Contact contact );
    public void shutdown();
	public void removeContact(Integer contactId);

}
