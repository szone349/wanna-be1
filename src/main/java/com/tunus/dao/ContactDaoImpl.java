package com.tunus.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tunus.domain.Contact;
@Repository( "contactDao" )
public class ContactDaoImpl extends HibernateDaoSupport  implements  ContactDao
{

	//@Autowired
	//private SessionFactory sessionFactory;
	public Contact findById(Integer id) {
		return ( Contact )getSessionFactory().getCurrentSession().get( Contact.class, id );
	}

	public List<Contact> findAll() {
		return getSessionFactory().getCurrentSession().createQuery( "from com.tunus.domain.Contact" ).list();
	}

	public void save(Contact contact) {
		getSessionFactory().getCurrentSession().saveOrUpdate( contact );
		
	}

	public void update(Contact contact) {
		getSessionFactory().getCurrentSession().update( contact );
		
	}

	public void delete(Contact contact) {
		getSessionFactory().getCurrentSession().delete( contact );		
	}
	
	public void removeContact(Integer id) {
		Contact contact = (Contact) getSessionFactory().getCurrentSession().load(
				Contact.class, id);
		if (null != contact) {
			getSessionFactory().getCurrentSession().delete(contact);
		}
	}

	public void shutdown()
    {
		getSessionFactory().openSession().createSQLQuery( "SHUTDOWN" ).executeUpdate();
    }

   @Autowired
    public void init( SessionFactory sessionFactory )
    {
        setSessionFactory( sessionFactory );
    }

	

}
