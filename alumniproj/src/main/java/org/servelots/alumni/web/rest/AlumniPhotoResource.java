package org.servelots.alumni.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.servelots.alumni.service.AlumniPhotoService;
import org.servelots.alumni.web.rest.util.HeaderUtil;
import org.servelots.alumni.web.rest.util.PaginationUtil;
import org.servelots.alumni.service.dto.AlumniPhotoDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;


import java.io.IOException;
 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST controller for managing AlumniPhoto.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin  
public class AlumniPhotoResource {

    private final Logger log = LoggerFactory.getLogger(AlumniPhotoResource.class);

    private static final String ENTITY_NAME = "alumniPhoto";
        
    private final AlumniPhotoService alumniPhotoService;

    public AlumniPhotoResource(AlumniPhotoService alumniPhotoService) {
        this.alumniPhotoService = alumniPhotoService;
    }

    /**
     * POST  /alumni-photos : Create a new alumniPhoto.
     *
     * @param alumniPhotoDTO the alumniPhotoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new alumniPhotoDTO, or with status 400 (Bad Request) if the alumniPhoto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/alumni-photos")
    @Timed
    public ResponseEntity<AlumniPhotoDTO> createAlumniPhoto(@Valid @RequestBody AlumniPhotoDTO alumniPhotoDTO) throws URISyntaxException {
        log.debug("REST request to save AlumniPhoto : {}", alumniPhotoDTO);
        if (alumniPhotoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new alumniPhoto cannot already have an ID")).body(null);
        }
        AlumniPhotoDTO result = alumniPhotoService.save(alumniPhotoDTO);
        return ResponseEntity.created(new URI("/api/alumni-photos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /alumni-photos : Updates an existing alumniPhoto.
     *
     * @param alumniPhotoDTO the alumniPhotoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated alumniPhotoDTO,
     * or with status 400 (Bad Request) if the alumniPhotoDTO is not valid,
     * or with status 500 (Internal Server Error) if the alumniPhotoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/alumni-photos")
    @Timed
    public ResponseEntity<AlumniPhotoDTO> updateAlumniPhoto(@Valid @RequestBody AlumniPhotoDTO alumniPhotoDTO) throws URISyntaxException {
        log.debug("REST request to update AlumniPhoto : {}", alumniPhotoDTO);
        if (alumniPhotoDTO.getId() == null) {
            return createAlumniPhoto(alumniPhotoDTO);
        }
        AlumniPhotoDTO result = alumniPhotoService.save(alumniPhotoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, alumniPhotoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /alumni-photos : get all the alumniPhotos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of alumniPhotos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/alumni-photos")
    @Timed
    public ResponseEntity<List<AlumniPhotoDTO>> getAllAlumniPhotos(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of AlumniPhotos");
        Page<AlumniPhotoDTO> page = alumniPhotoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/alumni-photos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /alumni-photos/:id : get the "id" alumniPhoto.
     *
     * @param id the id of the alumniPhotoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the alumniPhotoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/alumni-photos/{id}")
    @Timed
    public ResponseEntity<AlumniPhotoDTO> getAlumniPhoto(@PathVariable Long id) {
        log.debug("REST request to get AlumniPhoto : {}", id);
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(alumniPhotoDTO));
    }

    /**
     * DELETE  /alumni-photos/:id : delete the "id" alumniPhoto.
     *
     * @param id the id of the alumniPhotoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/alumni-photos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlumniPhoto(@PathVariable Long id) {
        log.debug("REST request to delete AlumniPhoto : {}", id);
        alumniPhotoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/alumni-photos?query=:query : search for the alumniPhoto corresponding
     * to the query.
     *
     * @param query the query of the alumniPhoto search 
     * @param pageable the pagination information
     * @return the result of the search
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/_search/alumni-photos")
    @Timed
    public ResponseEntity<List<AlumniPhotoDTO>> searchAlumniPhotos(@RequestParam String query, @ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to search for a page of AlumniPhotos for query {}", query);
        Page<AlumniPhotoDTO> page = alumniPhotoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/alumni-photos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
	
	
	    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/upload/alumni-photos")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username ) throws IOException {

        byte[] bytes;

        if (!file.isEmpty()) {
             bytes = file.getBytes();
            //store file in storage
        }
       log.debug("REST request to upload AlumniPhotos for query {}" + username);
 
        log.debug(String.format("receive %s from %s", file.getOriginalFilename(), username));
    }



}
