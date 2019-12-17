package com.techjava.marvelinfo.controller;

import com.techjava.marvelinfo.dto.CharacterDetailsByIdDTO;
import com.techjava.marvelinfo.service.MarvelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * The Class MarvelInfoController
 *
 */

@RestController
@CrossOrigin
@RequestMapping("/marvelinfo/api")
public class MarvelInfoController {


    @Autowired
    MarvelService marvelService;

    /**
     * Gets the Marvel Characters ID
     *
     * @return Character ID
     */
    @GetMapping(value="/characters")
    public ResponseEntity<List<Integer>> getCharacters(){

        return new ResponseEntity<>(marvelService.getCharacters(), HttpStatus.OK);

    }

    @GetMapping(value="/characters/{characterid}")
    public ResponseEntity<CharacterDetailsByIdDTO> getCharactersById(@PathVariable("characterid") Integer characterid){

        return new ResponseEntity<>(marvelService.getCharacterInfoById(characterid),HttpStatus.OK);
    }

    @GetMapping(value="/characters/{characterid}/language/{languagecode}")
    public ResponseEntity<CharacterDetailsByIdDTO> getCharacterTranslationById(@PathVariable("characterid") Integer characterid,@PathVariable("languagecode") String languagecode){
        return new ResponseEntity<>(marvelService.getCharacterInfoTranslationById(characterid,languagecode),HttpStatus.OK);
    }

}
