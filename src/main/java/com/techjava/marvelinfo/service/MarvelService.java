package com.techjava.marvelinfo.service;

import com.techjava.marvelinfo.config.CharacterInfo;
import com.techjava.marvelinfo.dto.CharacterDetailsByIdDTO;
import com.techjava.marvelinfo.dto.CharacterDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MarvelService {

    /**
     * getCharacters
     *
     * Gets character details from Marvel API , retrieves
     * from Cache if available and returns list of
     * characters Ids
     *
     * @return CharacterIds
     */

    List<Integer> getCharacters();


    CharacterDetailsByIdDTO getCharacterInfoById(Integer characterId);

    CharacterDetailsByIdDTO getCharacterInfoTranslationById(Integer characterId,String languageCode);

    List<CharacterDetailsByIdDTO> getCharactersLastSearches();
}
