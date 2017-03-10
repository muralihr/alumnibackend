package org.servelots.alumni.service.impl;

import org.servelots.alumni.service.AlumniPhotoCategoryService;
import org.servelots.alumni.domain.AlumniPhotoCategory;
import org.servelots.alumni.repository.AlumniPhotoCategoryRepository;
import org.servelots.alumni.repository.search.AlumniPhotoCategorySearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AlumniPhotoCategory.
 */
@Service
@Transactional
public class AlumniPhotoCategoryServiceImpl implements AlumniPhotoCategoryService{

    private final Logger log = LoggerFactory.getLogger(AlumniPhotoCategoryServiceImpl.class);
    
    private final AlumniPhotoCategoryRepository alumniPhotoCategoryRepository;

    private final AlumniPhotoCategorySearchRepository alumniPhotoCategorySearchRepository;

    public AlumniPhotoCategoryServiceImpl(AlumniPhotoCategoryRepository alumniPhotoCategoryRepository, AlumniPhotoCategorySearchRepository alumniPhotoCategorySearchRepository) {
        this.alumniPhotoCategoryRepository = alumniPhotoCategoryRepository;
        this.alumniPhotoCategorySearchRepository = alumniPhotoCategorySearchRepository;
    }

    /**
     * Save a alumniPhotoCategory.
     *
     * @param alumniPhotoCategory the entity to save
     * @return the persisted entity
     */
    @Override
    public AlumniPhotoCategory save(AlumniPhotoCategory alumniPhotoCategory) {
        log.debug("Request to save AlumniPhotoCategory : {}", alumniPhotoCategory);
        AlumniPhotoCategory result = alumniPhotoCategoryRepository.save(alumniPhotoCategory);
        alumniPhotoCategorySearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the alumniPhotoCategories.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AlumniPhotoCategory> findAll(Pageable pageable) {
        log.debug("Request to get all AlumniPhotoCategories");
        Page<AlumniPhotoCategory> result = alumniPhotoCategoryRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one alumniPhotoCategory by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AlumniPhotoCategory findOne(Long id) {
        log.debug("Request to get AlumniPhotoCategory : {}", id);
        AlumniPhotoCategory alumniPhotoCategory = alumniPhotoCategoryRepository.findOne(id);
        return alumniPhotoCategory;
    }

    /**
     *  Delete the  alumniPhotoCategory by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AlumniPhotoCategory : {}", id);
        alumniPhotoCategoryRepository.delete(id);
        alumniPhotoCategorySearchRepository.delete(id);
    }

    /**
     * Search for the alumniPhotoCategory corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AlumniPhotoCategory> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AlumniPhotoCategories for query {}", query);
        Page<AlumniPhotoCategory> result = alumniPhotoCategorySearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
