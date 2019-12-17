package com.techjava.marvelinfo.config;

import com.techjava.marvelinfo.dto.CharacterDetailsDTO;
import com.techjava.marvelinfo.restclient.MarvelAPIClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * The Class CharacterInfo
 */
@Component
public class CharacterInfo {

    @Autowired
    MarvelAPIClientImpl marvelAPIClient;

    /**
     * getCharacterDetails
     *
     * This method retrieves character details from Marvel API
     * retrieves information from cache if available
     * Cache key is considered as curren date
     *
     * @return
     */
    @CharacterCacheAnnotation
    public List<CharacterDetailsDTO> getCharacterDetails(Date currentDate){

        return marvelAPIClient.getCharacterDetails() ;

    }

}
