package com.iii.premia.customervatrate.services.customervatrate;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.iii.premia.customervatrate.services.customervatrate package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _DraftScheduleFault_QNAME = new QName(
			"http://CustomerVATRate.services.CustomerVATRate.premia.iii.com/",
			"DraftScheduleFault");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.iii.premia.customervatrate.services.customervatrate
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link CustomerVATRateRequestInfo }
	 * 
	 */
	public CustomerVATRateRequestInfo createCustomerVATRateRequestInfo() {
		return new CustomerVATRateRequestInfo();
	}

	/**
	 * Create an instance of {@link CustomerVATRateRequest }
	 * 
	 */
	public CustomerVATRateRequest createCustomerVATRateRequest() {
		return new CustomerVATRateRequest();
	}

	/**
	 * Create an instance of {@link CustomerVATRateResponseInfo }
	 * 
	 */
	public CustomerVATRateResponseInfo createCustomerVATRateResponseInfo() {
		return new CustomerVATRateResponseInfo();
	}

	/**
	 * Create an instance of {@link CustomerVATRateFault }
	 * 
	 */
	public CustomerVATRateFault createCustomerVATRateFault() {
		return new CustomerVATRateFault();
	}

	/**
	 * Create an instance of {@link ReferralExceptionBean }
	 * 
	 */
	public ReferralExceptionBean createReferralExceptionBean() {
		return new ReferralExceptionBean();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ReferralExceptionBean }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://CustomerVATRate.services.CustomerVATRate.premia.iii.com/", name = "DraftScheduleFault")
	public JAXBElement<ReferralExceptionBean> createDraftScheduleFault(
			ReferralExceptionBean value) {
		return new JAXBElement<ReferralExceptionBean>(
				_DraftScheduleFault_QNAME, ReferralExceptionBean.class, null,
				value);
	}

}
