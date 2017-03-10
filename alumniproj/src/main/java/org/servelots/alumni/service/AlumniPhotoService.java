package org.servelots.alumni.service;

import org.servelots.alumni.service.dto.AlumniPhotoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing AlumniPhoto.
 */
public interface AlumniPhotoService {

    /**
     * Save a alumniPhoto.
     *
     * @param alumniPhotoDTO the entity to save
     * @return the persisted entity
     */
    AlumniPhotoDTO save(AlumniPhotoDTO alumniPhotoDTO);

    /**
     *  Get all the alumniPhotos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AlumniPhotoDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" alumniPhoto.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AlumniPhotoDTO findOne(Long id);

    /**
     *  Delete the "id" alumniPhoto.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the alumniPhoto corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AlumniPhotoDTO> search(String query, Pageable pageable);
}
