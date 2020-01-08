package com.techjava.marvelinfo.exception;

import java.io.Serializable;

/**
 * The Class SearchException
 * 
 *
 */
public class SearchException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Instantiates SearchException
     * @param message
     */
    public SearchException(String message){
        super(message);
    }

}
