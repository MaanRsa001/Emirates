package com.iii.premia.customervatrate.services.customervatrate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerVATRateRequestInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="customerVATRateRequestInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReferralInformation" type="{http://CustomerVATRate.services.CustomerVATRate.premia.iii.com/}customerVATRateRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerVATRateRequestInfo", propOrder = { "referralInformation" })
public class CustomerVATRateRequestInfo {

	@XmlElement(name = "ReferralInformation")
	protected CustomerVATRateRequest referralInformation;

	/**
	 * Gets the value of the referralInformation property.
	 * 
	 * @return possible object is {@link CustomerVATRateRequest }
	 * 
	 */
	public CustomerVATRateRequest getReferralInformation() {
		return referralInformation;
	}

	/**
	 * Sets the value of the referralInformation property.
	 * 
	 * @param value
	 *            allowed object is {@link CustomerVATRateRequest }
	 * 
	 */
	public void setReferralInformation(CustomerVATRateRequest value) {
		this.referralInformation = value;
	}

}
