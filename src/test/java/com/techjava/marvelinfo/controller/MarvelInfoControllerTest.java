package com.techjava.marvelinfo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.techjava.marvelinfo.dto.CharacterDetailsByIdDTO;
import com.techjava.marvelinfo.dto.Thumbnail;
import com.techjava.marvelinfo.service.MarvelService; 

@ExtendWith(SpringExtension.class)
public class MarvelInfoControllerTest {
	
	@InjectMocks
	static
    MarvelInfoController marvelInfoController;
	
	@Mock
	MarvelService marvelService;
	
	
	 
	 
	@DisplayName("Test Character Search Controller")
	@Test
    public void testCharacterSearchController() throws Exception {
		
		List<Integer> characterDetails=IntStream.range(1, 100).boxed().collect(Collectors.toList());
		Mockito.when(marvelService.getCharacters()).thenReturn(characterDetails);
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        ResponseEntity<List<Integer>> responseEntity=marvelInfoController.getCharacters();
        assertEquals(characterDetails,marvelInfoController.getCharacters().getBody());
        assertEquals(HttpStatus.OK,marvelInfoController.getCharacters().getStatusCode());
        
    }
	
	@DisplayName("Test Character search By Id Controller")
	@Test
	public void testCharacterSearchById() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        CharacterDetailsByIdDTO characterDetails=new CharacterDetailsByIdDTO();
        characterDetails.setId(1);
        characterDetails.setName("XXXX");
        Thumbnail thumbnail=new Thumbnail();
        thumbnail.setExtension("jpeg");
        thumbnail.setPath("/");;
        characterDetails.setThumbnail(thumbnail);
      
        Mockito.when(marvelService.getCharacterInfoTranslationById(Mockito.any(),Mockito.any())).thenReturn(characterDetails);
        Mockito.when(marvelService.getCharacterInfoById(Mockito.any())).thenReturn(characterDetails);
    //    ResponseEntity<CharacterDetailsByIdDTO> responseEntity=marvelInfoController.getCharacterTranslationById(Mockito.anyInt(),Optional.class);
        
     //    assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		
		
	}

}
