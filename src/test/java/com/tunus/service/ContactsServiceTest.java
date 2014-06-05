package com.tunus.service;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tunus.domain.Contact;
public class ContactsServiceTest {
	private static ApplicationContext applicationContext;
    private static ContactService contactService;

    public static void showCustomers()
    {
        List<Contact> customers = contactService.findAll();
        System.out.println( "Contacts:" );
        for( Contact customer : customers )
        {
            System.out.println( "\t" + customer.getName() );
        }
    }

    public static void addUser( String name)
    {
    	contactService.save( new Contact( name," "," " ) );
    }

    public static void shutdown()
    {
    	contactService.shutdown();
    }

    public static void main( String[] args )
    {
        // Load the application context
        applicationContext = new ClassPathXmlApplicationContext( "file:src/main/webapp/WEB-INF/applicationContext.xml" );

        // Load our customer service bean
        contactService = ( ContactService )applicationContext.getBean( "contactService" );

        // Test code
        showCustomers();
        addUser( "Michael Haines");
        showCustomers();
        shutdown();
    }
}
