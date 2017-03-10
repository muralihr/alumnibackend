package org.servelots.alumni.service.impl;

import org.servelots.alumni.service.AlumniService;
import org.servelots.alumni.web.rest.util.SpreadSheetReader;
import org.servelots.alumni.domain.Alumni;
import org.servelots.alumni.repository.AlumniRepository;
import org.servelots.alumni.repository.search.AlumniSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Alumni.
 */
@Service
@Transactional
public class AlumniServiceImpl implements AlumniService{

    private final Logger log = LoggerFactory.getLogger(AlumniServiceImpl.class);
    
    private final AlumniRepository alumniRepository;

    private final AlumniSearchRepository alumniSearchRepository;

    public AlumniServiceImpl(AlumniRepository alumniRepository, AlumniSearchRepository alumniSearchRepository) {
        this.alumniRepository = alumniRepository;
        this.alumniSearchRepository = alumniSearchRepository;
    }

    /**
     * Save a alumni.
     *
     * @param alumni the entity to save
     * @return the persisted entity
     */
    @Override

    @Transactional(readOnly=false)
    public Alumni save(Alumni alumni) {
        log.debug("Request to save Alumni : {}", alumni);
        Alumni result = alumniRepository.save(alumni);
        alumniSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the alumni.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Alumni> findAll(Pageable pageable) {
        log.debug("Request to get all Alumni");
        Page<Alumni> result = alumniRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one alumni by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Alumni findOne(Long id) {
        log.debug("Request to get Alumni : {}", id);
        Alumni alumni = alumniRepository.findOne(id);
        return alumni;
    }

    /**
     *  Delete the  alumni by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Alumni : {}", id);
        alumniRepository.delete(id);
        alumniSearchRepository.delete(id);
    }

    /**
     * Search for the alumni corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Alumni> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Alumni for query {}", query);
          
        Page<Alumni> result = alumniSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
  
}
