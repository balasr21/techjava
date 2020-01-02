package com.techjava.marvelinfo.repository;

import com.techjava.marvelinfo.domain.CharacterDetails;
import com.techjava.marvelinfo.dto.CharacterDetailsByIdDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterDetailsRepository extends JpaRepository<CharacterDetails,Long> {

    @Query("select NEW com.techjava.marvelinfo.dto.CharacterDetailsByIdDTO( " +
            "characterId,name,description,thumbnailPath,extension) from CharacterDetails order by searchTimestamp desc")
    public List<CharacterDetailsByIdDTO> getLastCharacterSearch(Pageable pageable);

}
