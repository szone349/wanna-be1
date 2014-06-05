package com.tunus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tunus.dao.ContactDao;
import com.tunus.domain.Contact;

@Service( "contactService" )
public class ContactServiceImpl implements ContactService{
	 @Autowired
	 private ContactDao contactDao;
	 @Transactional
	public Contact findById(Integer id) {
		 return contactDao.findById( id );
	}
	@Transactional
	public List<Contact> findAll() {
		return contactDao.findAll();
	}

	@Transactional
	public void save(Contact contact) {
		contactDao.save( contact );
		
	}
	@Transactional
	public void update(Contact contact) {
		contactDao.update( contact );
		
	}

	@Transactional
	public void delete(Contact contact) {
		contactDao.delete( contact );
		
	}
	@Transactional
	public void removeContact(Integer id) {
		contactDao.removeContact(id);
	}

	
	public void shutdown() {
		contactDao.shutdown();
		
	}

}
