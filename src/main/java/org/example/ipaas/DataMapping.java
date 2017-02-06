package org.example.ipaas;

import org.apache.camel.salesforce.dto.Contact;

import twitter4j.Status;

public class DataMapping {

	public Contact map(Status status) {
		Contact contact = new Contact();
		contact.setFirstName(status.getUser().getName().split(" ")[0]);
		contact.setLastName(status.getUser().getName().split(" ")[1]);
		contact.setDescription(status.getText());
		contact.setTitle(status.getUser().getScreenName());
		return contact;
	}
}
