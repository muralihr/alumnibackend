package org.servelots.alumni.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.servelots.alumni.domain.AlumniPhotoCategory;
import org.servelots.alumni.service.AlumniPhotoCategoryService;
import org.servelots.alumni.web.rest.util.HeaderUtil;
import org.servelots.alumni.web.rest.util.PaginationUtil;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing AlumniPhotoCategory.
 */
@RestController
@RequestMapping("/api")
public class AlumniPhotoCategoryResource {

    private final Logger log = LoggerFactory.getLogger(AlumniPhotoCategoryResource.class);

    private static final String ENTITY_NAME = "alumniPhotoCategory";
        
    private final AlumniPhotoCategoryService alumniPhotoCategoryService;

    public AlumniPhotoCategoryResource(AlumniPhotoCategoryService alumniPhotoCategoryService) {
        this.alumniPhotoCategoryService = alumniPhotoCategoryService;
    }

    /**
     * POST  /alumni-photo-categories : Create a new alumniPhotoCategory.
     *
     * @param alumniPhotoCategory the alumniPhotoCategory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new alumniPhotoCategory, or with status 400 (Bad Request) if the alumniPhotoCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/alumni-photo-categories")
    @Timed
    public ResponseEntity<AlumniPhotoCategory> createAlumniPhotoCategory(@Valid @RequestBody AlumniPhotoCategory alumniPhotoCategory) throws URISyntaxException {
        log.debug("REST request to save AlumniPhotoCategory : {}", alumniPhotoCategory);
        if (alumniPhotoCategory.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new alumniPhotoCategory cannot already have an ID")).body(null);
        }
        AlumniPhotoCategory result = alumniPhotoCategoryService.save(alumniPhotoCategory);
        return ResponseEntity.created(new URI("/api/alumni-photo-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /alumni-photo-categories : Updates an existing alumniPhotoCategory.
     *
     * @param alumniPhotoCategory the alumniPhotoCategory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated alumniPhotoCategory,
     * or with status 400 (Bad Request) if the alumniPhotoCategory is not valid,
     * or with status 500 (Internal Server Error) if the alumniPhotoCategory couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/alumni-photo-categories")
    @Timed
    public ResponseEntity<AlumniPhotoCategory> updateAlumniPhotoCategory(@Valid @RequestBody AlumniPhotoCategory alumniPhotoCategory) throws URISyntaxException {
        log.debug("REST request to update AlumniPhotoCategory : {}", alumniPhotoCategory);
        if (alumniPhotoCategory.getId() == null) {
            return createAlumniPhotoCategory(alumniPhotoCategory);
        }
        AlumniPhotoCategory result = alumniPhotoCategoryService.save(alumniPhotoCategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, alumniPhotoCategory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /alumni-photo-categories : get all the alumniPhotoCategories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of alumniPhotoCategories in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/alumni-photo-categories")
    @Timed
    public ResponseEntity<List<AlumniPhotoCategory>> getAllAlumniPhotoCategories(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of AlumniPhotoCategories");
        Page<AlumniPhotoCategory> page = alumniPhotoCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/alumni-photo-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /alumni-photo-categories/:id : get the "id" alumniPhotoCategory.
     *
     * @param id the id of the alumniPhotoCategory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the alumniPhotoCategory, or with status 404 (Not Found)
     */
    @GetMapping("/alumni-photo-categories/{id}")
    @Timed
    public ResponseEntity<AlumniPhotoCategory> getAlumniPhotoCategory(@PathVariable Long id) {
        log.debug("REST request to get AlumniPhotoCategory : {}", id);
        AlumniPhotoCategory alumniPhotoCategory = alumniPhotoCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(alumniPhotoCategory));
    }

    /**
     * DELETE  /alumni-photo-categories/:id : delete the "id" alumniPhotoCategory.
     *
     * @param id the id of the alumniPhotoCategory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/alumni-photo-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlumniPhotoCategory(@PathVariable Long id) {
        log.debug("REST request to delete AlumniPhotoCategory : {}", id);
        alumniPhotoCategoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/alumni-photo-categories?query=:query : search for the alumniPhotoCategory corresponding
     * to the query.
     *
     * @param query the query of the alumniPhotoCategory search 
     * @param pageable the pagination information
     * @return the result of the search
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/_search/alumni-photo-categories")
    @Timed
    public ResponseEntity<List<AlumniPhotoCategory>> searchAlumniPhotoCategories(@RequestParam String query, @ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to search for a page of AlumniPhotoCategories for query {}", query);
        Page<AlumniPhotoCategory> page = alumniPhotoCategoryService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/alumni-photo-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
