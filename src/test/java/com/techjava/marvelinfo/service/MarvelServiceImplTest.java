package com.techjava.marvelinfo.service;

import com.techjava.marvelinfo.config.CharacterInfo;
import com.techjava.marvelinfo.dto.CharacterDetailsDTO;
import com.techjava.marvelinfo.dto.Data;
import com.techjava.marvelinfo.dto.Result;
import com.techjava.marvelinfo.dto.Thumbnail;
import com.techjava.marvelinfo.exception.InvalidCharacterException;
import com.techjava.marvelinfo.exception.SearchException;
import com.techjava.marvelinfo.repository.CharacterDetailsRepository;
import com.techjava.marvelinfo.restclient.MarvelAPIClientImpl;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
public class MarvelServiceImplTest {

    @InjectMocks
    MarvelServiceImpl marvelService;

    @Mock
    com.techjava.marvelinfo.config.CharacterInfo characterInfo;
    
    @Mock
    MarvelAPIClientImpl marvelAPIClient;
    
    @Mock
    CharacterDetailsRepository characterDetailsRepository;


    static List<CharacterDetailsDTO> characterDetailsDTOS=new ArrayList<>();
    
    static List<Integer> characterIds=new ArrayList<>();
    
    static CharacterDetailsDTO characterDetails;

    @BeforeAll
    public static void setup(){


        CharacterDetailsDTO characterDetailsDTO=new CharacterDetailsDTO();
        Data data=new Data();
        List<Result> results=new ArrayList<>();
        Result result=new Result();
        Thumbnail thumbnail=new Thumbnail();

        result.setId(100);
        result.setDescription("XXXX");
        thumbnail.setPath("http://google.com");
        thumbnail.setExtension("jpeg");
        result.setThumbnail(thumbnail);
        results.add(result);

        data.setResults(results);
        characterDetailsDTO.setData(data);
        characterDetailsDTO.setCode(200l);

        characterDetailsDTOS.add(characterDetailsDTO); // First Character
        
        characterDetailsDTO=new CharacterDetailsDTO();
        data=new Data();
        result=new Result();
        thumbnail=new Thumbnail();
        results=new ArrayList<>();
        result.setId(100);
        result.setDescription("XXXX");
        thumbnail.setPath("http://google.com");
        thumbnail.setExtension("jpeg");
        result.setThumbnail(thumbnail);
        results.add(result);

        data.setResults(results);
        characterDetailsDTO.setData(data);
        characterDetailsDTO.setCode(200l);
        
        characterDetailsDTOS.add(characterDetailsDTO); // Second Character
        
        characterDetails=characterDetailsDTO;
        
        
        if(characterDetailsDTOS!=null && !characterDetailsDTOS.isEmpty()) {
        List<List<Integer>> characterList = characterDetailsDTOS.stream().filter(character->character.getData()!=null)
        		.map(character->character.getData().getResults().stream().mapToInt(ord->ord.getId())
        		.boxed().collect(Collectors.toList())).collect(Collectors.toList());
        
        characterIds = characterList.stream().reduce(new ArrayList<>(), (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        });
        		
        }

    }
    
    @AfterEach
    public void reset() {
    	setup();
    }
    
    @DisplayName("Test all characterIds service") 
    @Test
    public void testAllCharacterService(){

        Mockito.when(characterInfo.getCharacterDetails(Mockito.any())).thenReturn(characterDetailsDTOS);
        
        assertEquals(characterIds,marvelService.getCharacters());

    }
    
    @DisplayName("Test empty character list")
    @Test
    public void testEmptyCharacterList() {
    
        Mockito.when(marvelAPIClient.getCharacterDetails()).thenReturn(new ArrayList<>());
 
        assertEquals(new ArrayList<>(),marvelService.getCharacters());
    	
    }
    
    
    
	  @DisplayName("Test character By Id service")
	  @Test 
	  public void testCharacterById() {
	  
		  Mockito.when(marvelAPIClient.getCharacterDetailsById(Mockito.anyInt())).thenReturn(characterDetails);
	      Mockito.when(characterDetailsRepository.save(Mockito.any())).thenReturn(null);
	  
	      assertEquals(characterDetails.getData().getResults().get(0).getId(),marvelService.getCharacterInfoById(1).getId());
	  
	  }
	  
	 
	  @DisplayName("Test character not found exception")
	  @Test 
	  public void testCharacterNotFoundExceptionForSearchByCharacterId() {
		  
		  characterDetails.setCode(404l);
		  characterDetails.setData(null);
		  Mockito.when(marvelAPIClient.getCharacterDetailsById(Mockito.anyInt())).thenReturn(characterDetails);
	      Mockito.when(characterDetailsRepository.save(Mockito.any())).thenReturn(null);
	      Assertions.assertThrows(InvalidCharacterException.class,() -> marvelService.getCharacterInfoById(0));
	  
	  }
	  
	  @DisplayName("Exception while character By Id search")
	  @Test 
	  public void testCharacterSearchExceptionForSearchByCharacterId() {
		  
		  characterDetails.setCode(500l);
		  characterDetails.setData(null);
		  Mockito.when(marvelAPIClient.getCharacterDetailsById(Mockito.anyInt())).thenReturn(characterDetails);
	      Mockito.when(characterDetailsRepository.save(Mockito.any())).thenReturn(null);
	      Assertions.assertThrows(SearchException.class,() -> marvelService.getCharacterInfoById(0));
	      
	  
	  }
	  
	/*
	 * @DisplayName("Test character translation service")
	 * 
	 * @Test public void testCharacterTranslationService() {
	 * 
	 * Mockito.when(marvelAPIClient.getCharacterDetailsById(Mockito.anyInt())).
	 * thenReturn(characterDetails);
	 * Mockito.when(marvelService.translateText(Mockito.any(),Mockito.any())).
	 * thenReturn(null);
	 * assertEquals(100,marvelService.getCharacterInfoTranslationById(Mockito.anyInt
	 * (),Mockito.any()).getId());
	 * 
	 * 
	 * }
	 */
	  
	  @DisplayName("Test last search character list service")
	  public void testLastSearchCharacterService() {
		  
		  Mockito.when(characterDetailsRepository.getLastCharacterSearch(Mockito.any())).thenReturn(Mockito.any());
		  marvelService.getCharactersLastSearches();	
		  
	  }
	  




}
