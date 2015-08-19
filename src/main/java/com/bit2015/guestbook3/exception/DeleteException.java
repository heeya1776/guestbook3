package com.bit2015.guestbook3.exception;

public class DeleteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeleteException(){
		super("GuestBookDao: deleteException");
	}
	
	public DeleteException(String msg){
		super(msg);
	}
	
}
