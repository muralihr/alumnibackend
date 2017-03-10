package org.servelots.alumni.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.servelots.alumni.domain.Alumni;
import org.servelots.alumni.repository.AlumniRepository;
import org.servelots.alumni.service.AlumniService;
import org.servelots.alumni.web.rest.util.HeaderUtil;
import org.servelots.alumni.web.rest.util.PaginationUtil;
import org.servelots.alumni.web.rest.util.SpreadSheetReader;

import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Alumni.
 */
@RestController
@RequestMapping("/api")
public class AlumniResource {

    private final Logger log = LoggerFactory.getLogger(AlumniResource.class);

    private static final String ENTITY_NAME = "alumni";
        
    private final AlumniService alumniService;
  
    @Inject 
    private AlumniRepository alumniRepository;


    public AlumniResource(AlumniService alumniService) {
        this.alumniService = alumniService;
    }

    /**
     * POST  /alumni : Create a new alumni.
     *
     * @param alumni the alumni to create
     * @return the ResponseEntity with status 201 (Created) and with body the new alumni, or with status 400 (Bad Request) if the alumni has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/alumni")
    @Timed
    public ResponseEntity<Alumni> createAlumni(@Valid @RequestBody Alumni alumni) throws URISyntaxException {
        log.debug("REST request to save Alumni : {}", alumni);
        if (alumni.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new alumni cannot already have an ID")).body(null);
        }
        Alumni result = alumniService.save(alumni);
        return ResponseEntity.created(new URI("/api/alumni/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /alumni : Updates an existing alumni.
     *
     * @param alumni the alumni to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated alumni,
     * or with status 400 (Bad Request) if the alumni is not valid,
     * or with status 500 (Internal Server Error) if the alumni couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/alumni")
    @Timed
    public ResponseEntity<Alumni> updateAlumni(@Valid @RequestBody Alumni alumni) throws URISyntaxException {
        log.debug("REST request to update Alumni : {}", alumni);
        if (alumni.getId() == null) {
            return createAlumni(alumni);
        }
        Alumni result = alumniService.save(alumni);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, alumni.getId().toString()))
            .body(result);
    }

    
     public void updateFromGSpreadSheet()
    {
    	log.debug("Request to updateFromGSpreadSheet");
        
    	try 
    	{
			List<Alumni> listAlumni = SpreadSheetReader.getAlumniData();
			log.debug("listAlumni" + listAlumni.size());
		    
			for(Alumni a : listAlumni)
			{
				log.debug("Request to updateFromGSpreadSheet" + a.getCornellAlumniIDNumber());
		        		
				Alumni tem = alumniRepository.findByCornellAlumniIDNumber(a.getCornellAlumniIDNumber());
				if(tem == null && a != null)
				{ 
					log.debug("SAVING NON EXISTING DATA " + a.getCornellAlumniIDNumber());
					alumniRepository.save(a);
				}
			}
    	
    	} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    /**
     * GET  /alumni : get all the alumni.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of alumni in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/alumni")
    @Timed
    public ResponseEntity<List<Alumni>> getAllAlumni(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Alumni");
        Page<Alumni> page = alumniService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/alumni");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /alumni/:id : get the "id" alumni.
     *
     * @param id the id of the alumni to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the alumni, or with status 404 (Not Found)
     */
    @GetMapping("/alumni/{id}")
    @Timed
    public ResponseEntity<Alumni> getAlumni(@PathVariable Long id) {
        log.debug("REST request to get Alumni : {}", id);
        Alumni alumni = alumniService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(alumni));
    }

    /**
     * DELETE  /alumni/:id : delete the "id" alumni.
     *
     * @param id the id of the alumni to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/alumni/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlumni(@PathVariable Long id) {
        log.debug("REST request to delete Alumni : {}", id);
        alumniService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/alumni?query=:query : search for the alumni corresponding
     * to the query.
     *
     * @param query the query of the alumni search 
     * @param pageable the pagination information
     * @return the result of the search
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/_search/alumni")
    @Timed
    public ResponseEntity<List<Alumni>> searchAlumni(@RequestParam String query, @ApiParam Pageable pageable)
        throws URISyntaxException {
    	updateFromGSpreadSheet();
        log.debug("REST request to search for a page of Alumni for query {}", query);
        Page<Alumni> page = alumniService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/alumni");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
