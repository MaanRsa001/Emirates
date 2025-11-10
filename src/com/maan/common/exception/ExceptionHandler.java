package com.maan.common.exception;

//
import com.opensymphony.xwork2.ActionSupport;

public class ExceptionHandler extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private Exception exception;

    public String execute() {
    	try{
    		if(exception != null){
                addActionError(exception.toString());exception.printStackTrace();
                return ERROR; // Everything went OK, we just want to redirect to the error page"
            } else {
               //logger.error("***** TRIED TO LOG EXCEPTION BUT EXCEPTION WAS NULL! I HAVE NOT LOGGED THIS EXCEPTION! *****");
            }

            addActionError(getText("actionUnhandledExceptionHandler.could.not.log.exception"));
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        return "error";
    }

    /**
     * @param exception the exception to set
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }
}
