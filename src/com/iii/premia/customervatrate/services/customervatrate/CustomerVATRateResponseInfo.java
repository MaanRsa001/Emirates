package com.iii.premia.customervatrate.services.customervatrate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerVATRateResponseInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="customerVATRateResponseInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OutPut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Responsibility" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerVATRateResponseInfo", propOrder = { "outPut",
		"status", "statusMessage", "responsibility" })
public class CustomerVATRateResponseInfo {

	@XmlElement(name = "OutPut")
	protected String outPut;
	@XmlElement(name = "Status")
	protected String status;
	@XmlElement(name = "StatusMessage")
	protected String statusMessage;
	@XmlElement(name = "Responsibility")
	protected String responsibility;

	/**
	 * Gets the value of the outPut property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOutPut() {
		return outPut;
	}

	/**
	 * Sets the value of the outPut property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOutPut(String value) {
		this.outPut = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

	/**
	 * Gets the value of the statusMessage property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * Sets the value of the statusMessage property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatusMessage(String value) {
		this.statusMessage = value;
	}

	/**
	 * Gets the value of the responsibility property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResponsibility() {
		return responsibility;
	}

	/**
	 * Sets the value of the responsibility property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResponsibility(String value) {
		this.responsibility = value;
	}

}
