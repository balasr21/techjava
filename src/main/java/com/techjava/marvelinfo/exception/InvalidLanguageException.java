package com.techjava.marvelinfo.exception;

import java.io.Serializable;

/**
 * The Class InvalidCharacterException
 */
public class InvalidLanguageException extends RuntimeException implements Serializable {


    /** The Constant serialVersionUID. **/
    private static final long serialVersionUID = 3369204895226251657L;

    /**
     * Instantiates InvalidLanguage
     * @param message
     */
    public InvalidLanguageException(String message){
        super(message);
    }

}
