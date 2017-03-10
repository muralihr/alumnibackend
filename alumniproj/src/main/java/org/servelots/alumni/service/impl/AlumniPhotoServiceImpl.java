package org.servelots.alumni.service.impl;

import org.servelots.alumni.service.AlumniPhotoService;
import org.servelots.alumni.domain.AlumniPhoto;
import org.servelots.alumni.repository.AlumniPhotoRepository;
import org.servelots.alumni.repository.search.AlumniPhotoSearchRepository;
import org.servelots.alumni.service.dto.AlumniPhotoDTO;
import org.servelots.alumni.service.mapper.AlumniPhotoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AlumniPhoto.
 */
@Service
@Transactional
public class AlumniPhotoServiceImpl implements AlumniPhotoService{

    private final Logger log = LoggerFactory.getLogger(AlumniPhotoServiceImpl.class);
    
    private final AlumniPhotoRepository alumniPhotoRepository;

    private final AlumniPhotoMapper alumniPhotoMapper;

    private final AlumniPhotoSearchRepository alumniPhotoSearchRepository;

    public AlumniPhotoServiceImpl(AlumniPhotoRepository alumniPhotoRepository, AlumniPhotoMapper alumniPhotoMapper, AlumniPhotoSearchRepository alumniPhotoSearchRepository) {
        this.alumniPhotoRepository = alumniPhotoRepository;
        this.alumniPhotoMapper = alumniPhotoMapper;
        this.alumniPhotoSearchRepository = alumniPhotoSearchRepository;
    }

    /**
     * Save a alumniPhoto.
     *
     * @param alumniPhotoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AlumniPhotoDTO save(AlumniPhotoDTO alumniPhotoDTO) {
        log.debug("Request to save AlumniPhoto : {}", alumniPhotoDTO);
        AlumniPhoto alumniPhoto = alumniPhotoMapper.alumniPhotoDTOToAlumniPhoto(alumniPhotoDTO);
        alumniPhoto = alumniPhotoRepository.save(alumniPhoto);
        AlumniPhotoDTO result = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto);
        alumniPhotoSearchRepository.save(alumniPhoto);
        return result;
    }

    /**
     *  Get all the alumniPhotos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AlumniPhotoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AlumniPhotos");
        Page<AlumniPhoto> result = alumniPhotoRepository.findAll(pageable);
        return result.map(alumniPhoto -> alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto));
    }

    /**
     *  Get one alumniPhoto by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AlumniPhotoDTO findOne(Long id) {
        log.debug("Request to get AlumniPhoto : {}", id);
        AlumniPhoto alumniPhoto = alumniPhotoRepository.findOne(id);
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto);
        return alumniPhotoDTO;
    }

    /**
     *  Delete the  alumniPhoto by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AlumniPhoto : {}", id);
        alumniPhotoRepository.delete(id);
        alumniPhotoSearchRepository.delete(id);
    }

    /**
     * Search for the alumniPhoto corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AlumniPhotoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AlumniPhotos for query {}", query);
        Page<AlumniPhoto> result = alumniPhotoSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(alumniPhoto -> alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto));
    }
}
