package com.techjava.marvelinfo.restclient;


import com.techjava.marvelinfo.constant.GlobalConstants;
import com.techjava.marvelinfo.dto.CharacterDetailsDTO;
import com.techjava.marvelinfo.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarvelAPIClientImpl {

    /** The rest template. */
    @Autowired
    private RestTemplate restTemplate;

    @Value("${marvel.service.character.v1.url}")
    private String characterAPIUrl;

    @Value("${marvel.private.key}")
    private String privateKey;

    @Value("${marvel.public.key}")
    private String publiceKey;


    /** The logger. */
    private Logger logger = LoggerFactory.getLogger(MarvelAPIClientImpl.class);

    /**
     * getCharacterDetails
     *
     * This method calls the Marvel API to get character information
     *
     * @return
     */
    public List<CharacterDetailsDTO> getCharacterDetails(){

        int limit= GlobalConstants.MARVEL_DETAILS_LIMIT;
        int offset=0;
        boolean continueIteration=true;
        List<CharacterDetailsDTO> characterDetails=new ArrayList<>();

        while(continueIteration){

            CharacterDetailsDTO characterDetilsOffset;

            characterDetilsOffset=getCharacterDetailsByOffSetAndLimit(offset,limit);
            characterDetails.add(characterDetilsOffset);
            if(characterDetilsOffset==null ||
                    characterDetilsOffset!=null && characterDetilsOffset.getData()!=null
                    && characterDetilsOffset.getData().getCount()==0){
                continueIteration=false;
            }
            continueIteration=false;

            offset=offset+limit;
        }

        return characterDetails;
    }

    /**
     * getCharacterDetailsById
     *
     * This method retrieves character info for specific
     * Marvel character
     *
     * @param characterId
     * @return
     */
    public CharacterDetailsDTO getCharacterDetailsById(Integer characterId){

        CharacterDetailsDTO characterDetails=null;
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, getHttpHeader());
        try{

            logger.info(characterAPIUrl+ "/" + characterId + "?" +getApiHash());

            characterDetails          =
                    restTemplate.exchange(characterAPIUrl+ "/" + characterId + "?" +getApiHash(),
                            HttpMethod.GET,requestEntity,new ParameterizedTypeReference<CharacterDetailsDTO>() {
                            }).getBody();

        }catch(HttpClientErrorException hex){
            logger.error("HTTP error {}", hex.getMessage());
        }catch(Exception e){
            logger.error("Generic error {}",e.getMessage());
        }
        return characterDetails;


    }

    /**
     * getCharacterDetailsByOffSetAndLimit
     *
     * This method makes API calls to Marvel based on offset
     * and limit supplied
     *
     * @param offset
     * @param limit
     */

    private CharacterDetailsDTO getCharacterDetailsByOffSetAndLimit(int offset, int limit) {

        CharacterDetailsDTO characterDetails=null;
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, getHttpHeader());
        try{

            logger.info(characterAPIUrl+ "?" + "limit=" + limit + "&offset=" + offset + "&" +getApiHash());

          characterDetails          =
                    restTemplate.exchange(characterAPIUrl+ "?" + "limit=" + limit + "&offset=" + offset + "&" +getApiHash(),
                            HttpMethod.GET,requestEntity,new ParameterizedTypeReference<CharacterDetailsDTO>() {
                            }).getBody();

        }catch(HttpClientErrorException hex){
            logger.error("HTTP error {}", hex.getMessage());
        }catch(Exception e){
            logger.error("Generic error {}",e.getMessage());
        }
        return characterDetails;

    }

    private HttpHeaders getHttpHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;

    }

    private String getApiHash() {

        String ts= DateUtils.convertDateToString(new Date(),GlobalConstants.DD_MM_YYYY_HH_MM_SS);
      //  String ts="1";

        return "ts="+ts+"&apikey="+publiceKey+"&hash="+DigestUtils.md5Hex(ts+privateKey+publiceKey).toLowerCase();

    }

}
