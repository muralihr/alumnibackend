package org.servelots.alumni.service;

import org.servelots.alumni.domain.Alumni;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Alumni.
 */
public interface AlumniService {

    /**
     * Save a alumni.
     *
     * @param alumni the entity to save
     * @return the persisted entity
     */
    Alumni save(Alumni alumni);

    /**
     *  Get all the alumni.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Alumni> findAll(Pageable pageable);

    /**
     *  Get the "id" alumni.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Alumni findOne(Long id);

    /**
     *  Delete the "id" alumni.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the alumni corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Alumni> search(String query, Pageable pageable);
    
 //   public void updateFromGSpreadSheet();
}
