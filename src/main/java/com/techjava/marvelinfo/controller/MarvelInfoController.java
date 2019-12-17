package com.techjava.marvelinfo.controller;

import com.techjava.marvelinfo.dto.CharacterDetailsByIdDTO;
import com.techjava.marvelinfo.service.MarvelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;

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
    @ApiOperation(value="Get all Marvel Character IDs",notes="This API retrieves Marvel character IDs from Marvel API.")
    public ResponseEntity<List<Integer>> getCharacters(){

        return new ResponseEntity<>(marvelService.getCharacters(), HttpStatus.OK);

    }

    /**
     * getCharacterTranslationById
     *
     * This method serves 2 purpose
     *   retrieve character information in the language received from Marvel API
     *   and translates received text to target language
     * @param characterid
     * @param language
     * @return
     */
    @ApiOperation(value="Get character information for the given character and translates if required",notes="This API retrieves Marvel character information" +
            "from Marvel API and based on optional parameter will make a translation if required")
    @GetMapping(value="/characters/{characterid}")
    public ResponseEntity<CharacterDetailsByIdDTO> getCharacterTranslationById(@ApiParam(value="CharacterId") @PathVariable("characterid") Integer characterid,
                                                                              @ApiParam(value="Optional parameter for passing language code in ISO-639-1 convention") @QueryParam("language") Optional<String> language){
        if(language.isPresent()) {
            return new ResponseEntity<>(marvelService.getCharacterInfoTranslationById(characterid, language.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(marvelService.getCharacterInfoById(characterid),HttpStatus.OK);
        }
    }

}
