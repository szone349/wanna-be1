package com.tunus.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
 
import java.util.List;
 
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@Transactional
public class ContactTest {
	
	 @Autowired
	    private SessionFactory sessionFactory;
	    private Session currentSession;
	 
	    @Before
	    public void openSession() {
	        currentSession = sessionFactory.getCurrentSession();
	    }
	 
	    @Test
	    public void shouldHaveASessionFactory() {
	        assertNotNull(sessionFactory);
	    }

	    @Test
	    public void shouldHaveFourObjectsAtStart() {
	        List<?> results = currentSession.createQuery("from Contact").list();
	        assertTrue(results.size()==4);
	    }
	    @Test
	    public void shouldBeAbleToPersistAnObject() {
	        //assertEquals(0, currentSession.createQuery("from Contact").list().size());
	        Contact jobUser = new Contact("Name1","address1","312321");
	        currentSession.persist(jobUser);
	        currentSession.flush();
	        assertEquals(5, currentSession.createQuery("from Contact").list().size());
	    }
	    
	    @Test
	    public void shouldBeABleToQueryForObjects() {
	        shouldBeAbleToPersistAnObject();
	 
	        assertEquals(1, currentSession.createQuery("from Contact where name = 'Name1'").list().size());
	        assertEquals(0, currentSession.createQuery("from Contact where name = 'Baz'").list().size());
	    }
}
