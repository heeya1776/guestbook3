package com.bit2015.guestbook3.exception;

public class GetListException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GetListException(){
		super("GuestBookDao: getListException");
	}
	
	public GetListException(String msg){
		super(msg);
	}
	
}
