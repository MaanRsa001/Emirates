package com.iii.premia.customervatrate.services.customervatrate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ReferralExceptionBean complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ReferralExceptionBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorDetails" type="{http://CustomerVATRate.services.CustomerVATRate.premia.iii.com/}customerVATRateFault"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferralExceptionBean", propOrder = { "errorDetails" })
public class ReferralExceptionBean {

	@XmlElement(name = "ErrorDetails", required = true)
	protected CustomerVATRateFault errorDetails;

	/**
	 * Gets the value of the errorDetails property.
	 * 
	 * @return possible object is {@link CustomerVATRateFault }
	 * 
	 */
	public CustomerVATRateFault getErrorDetails() {
		return errorDetails;
	}

	/**
	 * Sets the value of the errorDetails property.
	 * 
	 * @param value
	 *            allowed object is {@link CustomerVATRateFault }
	 * 
	 */
	public void setErrorDetails(CustomerVATRateFault value) {
		this.errorDetails = value;
	}

}
