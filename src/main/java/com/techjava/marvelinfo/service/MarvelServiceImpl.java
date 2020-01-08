package com.techjava.marvelinfo.service;

import com.techjava.marvelinfo.config.CharacterInfo;
import com.techjava.marvelinfo.constant.GlobalConstants;
import com.techjava.marvelinfo.domain.CharacterDetails;
import com.techjava.marvelinfo.dto.CharacterDetailsByIdDTO;
import com.techjava.marvelinfo.dto.CharacterDetailsDTO;
import com.techjava.marvelinfo.exception.InvalidCharacterException;
import com.techjava.marvelinfo.exception.InvalidLanguageException;
import com.techjava.marvelinfo.exception.SearchException;
import com.techjava.marvelinfo.repository.CharacterDetailsRepository;
import com.techjava.marvelinfo.restclient.MarvelAPIClientImpl;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarvelServiceImpl implements MarvelService {

    @Autowired
    CharacterInfo characterInfo;

    @Autowired
    MarvelAPIClientImpl marvelAPIClient;

    @Autowired
    CharacterDetailsRepository characterDetailsRepository;

    @Value("${google.cloud.api.key}")
    private String googleCloudKey;

    /** The logger. */
    private Logger logger = LoggerFactory.getLogger(MarvelServiceImpl.class);


    /**
     * getCharacters
     * <p>
     * Gets character details from Marvel API , retrieves
     * from Cache if available and returns list of
     * characters Ids
     *
     * @return CharacterIds
     */
    @Override
    public List<Integer> getCharacters() {

        List<CharacterDetailsDTO> characterDetails = characterInfo.getCharacterDetails(new Date());

        if(characterDetails!=null && !characterDetails.isEmpty()) {
            List<List<Integer>> allCharacters = characterDetails.stream().map(ch -> ch.getData().getResults().stream().map(ids -> ids.getId()).collect(Collectors.toList())).collect(Collectors.toList());

            return allCharacters.stream().reduce(new ArrayList<>(), (l1, l2) -> {
                l1.addAll(l2);
                return l1;
            });
        }

        return new ArrayList<>();

    }

    /**
     * getCharacterInfoById - This method retrieves
     * information from marvel API
     *
     *
     * @param characterId
     * @return CharacterInfo
     */
    @Override
    public CharacterDetailsByIdDTO getCharacterInfoById(Integer characterId) {

        CharacterDetailsDTO characterInfo=marvelAPIClient.getCharacterDetailsById(characterId);
        CharacterDetailsByIdDTO characterDetailsByIdDTO=new CharacterDetailsByIdDTO();

        if(characterInfo!=null && characterInfo.getData()!=null && characterInfo.getData().getResults()!=null) {
            characterDetailsByIdDTO.setId(characterInfo.getData().getResults().get(0).getId());
            characterDetailsByIdDTO.setName(characterInfo.getData().getResults().get(0).getName());
            characterDetailsByIdDTO.setDescription(characterInfo.getData().getResults().get(0).getDescription());
            characterDetailsByIdDTO.setThumbnail(characterInfo.getData().getResults().get(0).getThumbnail());

            populateSearchDetails(characterDetailsByIdDTO);

       }else if(characterInfo!=null && characterInfo.getCode()!=null && characterInfo.getCode() == HttpStatus.NOT_FOUND.value()){
            throw new InvalidCharacterException(GlobalConstants.INVALID_CHARACTER);
       }else {
    	   throw new SearchException(GlobalConstants.SEARCH_EXCEPTION);
       }
        return characterDetailsByIdDTO;

    }



    /**
     * getCharacterInfoTranslationById This method retrieves
     *        information from marvel API , translates based
     *        on given ISO-639-1 language code
     *
     *
     * @param characterId
     * @param languageCode
     * @return
     */
    @Override
    public CharacterDetailsByIdDTO getCharacterInfoTranslationById(Integer characterId, String languageCode) {
        CharacterDetailsDTO characterInfo=marvelAPIClient.getCharacterDetailsById(characterId);
        CharacterDetailsByIdDTO characterDetailsByIdDTO=new CharacterDetailsByIdDTO();

        if(characterInfo!=null && characterInfo.getData()!=null && characterInfo.getData().getResults()!=null) {
            characterDetailsByIdDTO.setId(characterInfo.getData().getResults().get(0).getId());
            characterDetailsByIdDTO.setName(translateText(characterInfo.getData().getResults().get(0).getName(),languageCode));
            characterDetailsByIdDTO.setDescription(translateText(characterInfo.getData().getResults().get(0).getDescription(),languageCode));
            characterDetailsByIdDTO.setThumbnail(characterInfo.getData().getResults().get(0).getThumbnail());
        }else if(characterInfo.getCode() == HttpStatus.NOT_FOUND.value()){
            throw new InvalidCharacterException(GlobalConstants.INVALID_CHARACTER);
        }
        return characterDetailsByIdDTO;
    }



    /**
     * translateText - This method uses GOOGLE API to translate text
     *
     * @param inputString
     * @param targetLanguage
     * @return
     */
    @Override
    public String translateText(String inputString,String targetLanguage){

        try {
            Translate t = new Translate.Builder(
                    GoogleNetHttpTransport.newTrustedTransport()
                    , GsonFactory.getDefaultInstance(), null)
                    .setApplicationName("MarvelCharacterInfo")
                    .build();
            Translate.Translations.List list = t.new Translations().list(
                    Arrays.asList(inputString),
                    // Target language
                    targetLanguage);

            list.setKey(googleCloudKey);
            TranslationsListResponse response = list.execute();
            return response.getTranslations().get(0).getTranslatedText();
        }catch(Exception e){
            logger.error("Error in Translating text {}",e.getMessage());
            throw new InvalidLanguageException(GlobalConstants.INVALID_LANGUAGE);
        }

    }

    @Override
    public List<CharacterDetailsByIdDTO> getCharactersLastSearches() {
        int pageNo=0;
        return characterDetailsRepository.getLastCharacterSearch(PageRequest.of(pageNo,GlobalConstants.NO_OF_LAST_SEARCHES));
    }

    /**
     * populateSearchDetails - This method populates current search details into DB
     * @param characterDetailsByIdDTO
     */
    private void populateSearchDetails(CharacterDetailsByIdDTO characterDetailsByIdDTO) {

        CharacterDetails characterDetails=new CharacterDetails();

        try{
            characterDetails.setId(characterDetailsByIdDTO.getId());
            characterDetails.setName(characterDetailsByIdDTO.getName());
            characterDetails.setDescription(characterDetailsByIdDTO.getDescription());
            characterDetails.setExtension(characterDetailsByIdDTO.getThumbnail().getExtension());
            characterDetails.setThumbnailPath(characterDetailsByIdDTO.getThumbnail().getPath());
            characterDetails.setSearchTimestamp(new Date());
            characterDetailsRepository.save(characterDetails);
        }catch(Exception e){
            logger.error("Error in saving search details {}",e.getMessage());
        }

    }


}
