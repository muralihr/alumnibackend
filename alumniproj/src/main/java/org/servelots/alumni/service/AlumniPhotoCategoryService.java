package org.servelots.alumni.service;

import org.servelots.alumni.domain.AlumniPhotoCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing AlumniPhotoCategory.
 */
public interface AlumniPhotoCategoryService {

    /**
     * Save a alumniPhotoCategory.
     *
     * @param alumniPhotoCategory the entity to save
     * @return the persisted entity
     */
    AlumniPhotoCategory save(AlumniPhotoCategory alumniPhotoCategory);

    /**
     *  Get all the alumniPhotoCategories.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AlumniPhotoCategory> findAll(Pageable pageable);

    /**
     *  Get the "id" alumniPhotoCategory.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AlumniPhotoCategory findOne(Long id);

    /**
     *  Delete the "id" alumniPhotoCategory.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the alumniPhotoCategory corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AlumniPhotoCategory> search(String query, Pageable pageable);
}
