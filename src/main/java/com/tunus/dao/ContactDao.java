package com.tunus.dao;

import java.util.List;

import com.tunus.domain.Contact;

public interface ContactDao {
	    public Contact findById( Integer id );
	    public List<Contact> findAll();
	    public void save( Contact customer );
	    public void update( Contact customer );
	    public void delete( Contact customer );
	    public void removeContact(Integer id);
	    public void shutdown();

}
