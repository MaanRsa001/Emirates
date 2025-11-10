package com.maan.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Logger;

public final class ResourceLocator {
	private static final Logger logger = LogUtil.getLogger(ResourceLocator.class);
	
  private static ResourceLocator instance=new ResourceLocator();
	
  private ResourceLocator(){
	  
  }
  public static ResourceLocator getInstance(){
	  
	  return instance;
  }
  public  ResourceBundle getDBBundle() {
	
	  ResourceBundle dbBundle=null;
	  try{
		  dbBundle=ResourceBundle.getBundle(DBConstants.DB_QUERY_PROPERTY_FILE);
	  }
	  catch(MissingResourceException e){
		  
	  }
	  
	  
	  return dbBundle;
  }
  public  ResourceBundle getCustomerBundle() {
		
	  ResourceBundle dbBundle=null;
	  try{
		  dbBundle=ResourceBundle.getBundle(DBConstants.CUSTOMER_PROPERTY_FILE);
	  }
	  catch(MissingResourceException e){
		  logger.debug(e);
	  }
	  return dbBundle;
  }
  public  ResourceBundle getTravelBundle() {
		
	  ResourceBundle dbBundle=null;
	  try{
		  dbBundle=ResourceBundle.getBundle(DBConstants.TRAVEL_PROPERTY_FILE);
	  }
	  catch(MissingResourceException e){
		  logger.debug(e);
	  }
	  return dbBundle;
  }	
	

}
