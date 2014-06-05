package com.tunus.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tunus.domain.Contact;
import com.tunus.service.ContactService;

@Controller
public class ContactsController {
	private static final Logger logger = LoggerFactory
			.getLogger(ContactsController.class);
	
	@Inject
	private ContactService contactService;
	
	@RequestMapping("/all")
	public @ResponseBody ModelAndView showUserList() {
		logger.info("showUserList");
		List<Contact> contactList = contactService.findAll();

		ModelAndView mv = new ModelAndView("contactlist");
		mv.addObject("contact", new Contact());
		mv.addObject("contactList", contactList);

		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact")
	Contact contact, BindingResult result) {
		logger.info("addContact");
		if(contact.getId() !=null)
		{
			logger.info("updating contact");
			contactService.update(contact);
		}
		else
		{
			logger.info("saving contact");
			contactService.save(contact);
		}

		return "redirect:/all";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<Contact>> getContacts()
	{
		logger.info("getContacts");
		ArrayList<Contact> contactList = (ArrayList<Contact>) contactService.findAll();

		Map<String, List<Contact>> contactMap = new HashMap<String, List<Contact>>();
		contactMap.put("searchContacts", contactList);
		return contactMap;
	}
	
	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId")
	Integer contactId) {
		logger.info("deleteContact");
		contactService.removeContact(contactId);

		return "redirect:/all";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ModelAndView editContact(@RequestParam(value="searchContactId", required=false) String contactId) {
		logger.info("Selected ContactId- "+ contactId);
		Contact contact = contactService.findById(Integer.parseInt(contactId));
		logger.info("Selected Contact with Id- "+ contact.getId());
		List<Contact> contactList = contactService.findAll();

		ModelAndView mv = new ModelAndView("contactlist");
		mv.addObject("contact", contact);
		mv.addObject("contactList", contactList);

		return mv;
	}

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	
	
	
	

}
