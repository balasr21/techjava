package com.techjava.marvelinfo.exception;

import java.io.Serializable;

/**
 * The Class InvalidCharacterException
 */
public class InvalidCharacterException extends RuntimeException implements Serializable {

    /** The Constant serialVersionUID. **/
    private static final long serialVersionUID = -3276225145121628422L;

    /**
     * Instantiates InvalidCharacter
     * @param message
     */
    public InvalidCharacterException(String message){
        super(message);
    }

}
