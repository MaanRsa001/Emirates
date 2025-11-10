package com.maan.integration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="XML")
public class CustXmlBean {
	@XmlElement(name="CustQueryDtls")
	private CustQueryDtls CustQueryDtls;

	
	public void setCustQueryDtls(CustQueryDtls custQueryDtls) {
		CustQueryDtls = custQueryDtls;
	}

	
	public CustQueryDtls getCustQueryDtls() {
		return CustQueryDtls;
	}



}
