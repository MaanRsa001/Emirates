package com.maan.integration;

import javax.xml.bind.annotation.XmlElement;

public class CustQueryDtls {
	@XmlElement(name="Customer")
	private Customer Customer;


	public void setCustomer(Customer customer) {
		Customer = customer;
	}

	public Customer getCustomer() {
		return Customer;
	}

}
