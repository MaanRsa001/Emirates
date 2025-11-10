package com.maan.marine.displayBean;

import java.util.ArrayList;
import java.util.List;

public class MarineDisplayBean {

	public List getExistingCustomerList(String[][] value) {// vinoth exisiting
		// customer string to list
		ArrayList list = new ArrayList();
		for (int i = 0; i < value.length; i++) {

			CustomerListBean bean = new CustomerListBean();
			bean.setCustomerID(value[i][0] == null ? "" : value[i][0]);
			bean.setCustomerName(value[i][1] == null ? "" : value[i][1]);
			bean.setEmailID(value[i][3] == null ? "" : value[i][3]);
			bean.setContactNO(value[i][4] == null ? "" : value[i][4]);
			list.add(bean);
		}
		return list;
	}

	public List getMarinePortFolioList(String[][] value) {// vinoth Portfolio
		// string to list
		ArrayList list = new ArrayList();
		try {
			for (int i = 0; i < value.length; i++) {
				PortfolioListBean bean = new PortfolioListBean();
				bean.setSno(i + 1);
				bean.setPolicyNo(value[i][0] == null ? "" : value[i][0]);
				bean.setQuoteNo(value[i][9] == null ? "" : value[i][9]);
				bean.setCustomerName(value[i][6] == null ? "" : value[i][6]);
				bean.setStartDate(value[i][3] + "/" + value[i][4] + "/"
						+ value[i][5]);
				bean.setPremium(value[i][2] == null ? "" : value[i][2]);
				bean.setCustomerID(value[i][1] == null ? "" : value[i][1]);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List getMarineReferralPolicyList(String[][] value) {// vinoth
																// Portfolio
		// string to list
		ArrayList list = new ArrayList();
		String cName;
		try {
			for (int i = 0; i < value.length; i++) {
				ReferralPolicyDisplayBean bean = new ReferralPolicyDisplayBean();
				bean.setSno(i + 1);
				bean.setQuoteNo(value[i][0] == null ? "" : value[i][0]);
				bean.setQuoteDate(value[i][2] + "/" + value[i][3] + "/"
						+ value[i][4]);

				bean.setCustomerNo(value[i][1] == null ? "" : value[i][1]);
				bean.setCompanyName(value[i][13] == null ? "" : value[i][13]);
				bean.setLoginID(value[i][11] == null ? "" : value[i][11]);
				if (value[i][12] == null || value[i][12].equals("")
						|| value[i][12].equalsIgnoreCase("null")) {
					cName = (value[i][8] == null ? "" : value[i][8]) + " "
							+ (value[i][9] == null ? "" : value[i][9]);
				} else {
					cName = value[i][12];
				}
				bean.setCustomerName(cName);
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List getMaraineDeclarationList(String[][] value) {// vinoth
		// Declaration
		// string to list
		ArrayList list = new ArrayList();
		try {
			for (int i = 0; i < value.length; i++) {
				DeclarationListBean bean = new DeclarationListBean();
				bean.setSno(i + 1);
				bean.setPolicyNo(value[i][0] == null ? "" : value[i][0]);
				bean.setMississippiNo(value[i][2] == null ? "" : value[i][2]);
				bean.setOpenCoverCustomerName(value[i][1] == null ? ""
						: value[i][1]);
				bean
						.setNoofCertificates(value[i][5] == null ? ""
								: value[i][5]);
				bean.setPremium(value[i][3] == null ? "" : value[i][3]);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List getMarineQuoteRegisterList(String[][] value, String quoteStatus) {
		ArrayList list = new ArrayList();
		com.maan.product.ProductSelection product = new com.maan.product.ProductSelection();
		String str = "";
		String openMissippiNo[][] = new String[0][0];
		if (quoteStatus.equalsIgnoreCase("Existing")) {
			for (int i = 0; i < value.length; i++) {
				MarineQuoteRegisterBean MQB = new MarineQuoteRegisterBean();
				MQB.setSno("" + (i + 1));
				MQB.setQuoteNo(value[i][0] == null ? "" : value[i][0]);
				MQB.setValdityDate(value[i][5] + "/" + value[i][6] + "/"
						+ value[i][7]);
				MQB.setQuoteDate(value[i][2] + "/" + value[i][3] + "/"
						+ value[i][4]);
				MQB.setCustId(value[i][1] == null ? "0" : value[i][1]);
				if (value[i][13].equals("")
						|| value[i][13].equalsIgnoreCase("null"))
					value[i][13] = "0";
				MQB.setOpenCoverNo(value[i][13] == null ? "0" : value[i][13]);
				openMissippiNo = product.getOpenCoverInfo(value[i][13]);
				if (openMissippiNo.length > 0)
					MQB.setOpenMissippiNo(openMissippiNo[0][0]);
				else
					MQB.setOpenMissippiNo("0");
				if (value[i][11] == null || value[i][11].equals("")
						|| value[i][11].equalsIgnoreCase("null")) {
					str = (value[i][8] == null ? "" : value[i][8]) + " "
							+ (value[i][9] == null ? "" : value[i][9]);
				} else {
					str = value[i][11];
				}
				MQB.setCustomerName(str);
				list.add(MQB);
			}
		} else {
			for (int i = 0; i < value.length; i++) {
				MarineQuoteRegisterBean MQB = new MarineQuoteRegisterBean();
				MQB.setSnoLap("" + (i + 1));

				MQB.setQuoteNoLap(value[i][0] == null ? "" : value[i][0]);
				MQB.setValdityDateLap(value[i][5] + "/" + value[i][6] + "/"
						+ value[i][7]);
				MQB.setQuoteDateLap(value[i][2] + "/" + value[i][3] + "/"
						+ value[i][4]);
				MQB.setCustomerNameLap(value[i][8]);
				list.add(MQB);
			}
		}
		return list;
	}
}
