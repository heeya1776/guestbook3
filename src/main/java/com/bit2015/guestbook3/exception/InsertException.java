package com.bit2015.guestbook3.exception;

public class InsertException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InsertException(){
		super("GuestBookDao: insertException");
	}
	
	public InsertException(String msg){
		super(msg);
	}

}
