package org.servelots.alumni.web.rest;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

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


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import javax.inject.Inject;
import java.io.IOException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import org.servelots.alumni.domain.AlumniPhoto;
import org.servelots.alumni.domain.Alumni;
import org.servelots.alumni.service.*;
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
    @Inject 
    private   AlumniService alumniService;

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
    public ResponseEntity < AlumniPhotoDTO > createAlumniPhoto(@Valid @RequestBody AlumniPhotoDTO alumniPhotoDTO) throws URISyntaxException {
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
    public ResponseEntity < AlumniPhotoDTO > updateAlumniPhoto(@Valid @RequestBody AlumniPhotoDTO alumniPhotoDTO) throws URISyntaxException {
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
    public ResponseEntity < List < AlumniPhotoDTO >> getAllAlumniPhotos(@ApiParam Pageable pageable)
    throws URISyntaxException {
        log.debug("REST request to get a page of AlumniPhotos");
        Page < AlumniPhotoDTO > page = alumniPhotoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/alumni-photos");
        return new ResponseEntity < > (page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /alumni-photos/:id : get the "id" alumniPhoto.
     *
     * @param id the id of the alumniPhotoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the alumniPhotoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/alumni-photos/{id}")
    @Timed
    public ResponseEntity < AlumniPhotoDTO > getAlumniPhoto(@PathVariable Long id) {
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
    public ResponseEntity < Void > deleteAlumniPhoto(@PathVariable Long id) {
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
    public ResponseEntity < List < AlumniPhotoDTO >> searchAlumniPhotos(@RequestParam String query, @ApiParam Pageable pageable)
    throws URISyntaxException {
        log.debug("REST request to search for a page of AlumniPhotos for query {}", query);
        Page < AlumniPhotoDTO > page = alumniPhotoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/alumni-photos");
        return new ResponseEntity < > (page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/alumni-photos_alumni_id/{id}")
    @Timed
    public ResponseEntity < List < AlumniPhoto >> searchAlumniPhotosByTitle(@PathVariable Long id)
    throws URISyntaxException {
        log.debug("REST request to search for a page of AlumniPhotos for query {}", id);
        Alumni alumni = alumniService.findOne(id);        
        List < AlumniPhoto >  listOfPhotos; 
        String alumniNum = alumni.getCornellAlumniIDNumber();
        listOfPhotos  = alumniPhotoService.findByTitle(alumniNum);
        ResponseEntity < List < AlumniPhoto>> result = new ResponseEntity  <>(listOfPhotos , null,HttpStatus.OK );        
        return result;
    }



    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/upload/alumni-photos")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username, @RequestParam("location") String location) throws IOException {

        byte[] bytes;
        String title = username;
        String description = location;
        String address = "";
        String mediaFileContentType = "";
        String urlOrfileLink = "http://pan.alumni.com//alumniimages//";
        String userAgent;
        ZonedDateTime uploadTime;
        Long alumniuserId;
        String alumniuserLogin;



        if (!file.isEmpty()) {
            bytes = file.getBytes();
            //store file in storage
        }
        String storageDirectory = "c:\\alumniphotos";
        String downLoadFileName = storageDirectory + "//" + file.getOriginalFilename();
        String contentType = file.getContentType();
        if (!file.isEmpty()) {
            try {
                bytes = file.getBytes();
                // Creating the directory to store file
                // Create the file on server
                File serverFile = new File(downLoadFileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                AlumniPhotoDTO alumniPhotoDTO = new AlumniPhotoDTO();
                alumniPhotoDTO.setTitle(username);
                alumniPhotoDTO.setDescription(description);
                alumniPhotoDTO.setLatitude(88.88);
                alumniPhotoDTO.setLongitude(88.88);
                alumniPhotoDTO.setMediaType(1);
                alumniPhotoDTO.setAlumniuserLogin(title);
                alumniPhotoDTO.setUrlOrfileLink(urlOrfileLink + file.getOriginalFilename());
                AlumniPhotoDTO result = alumniPhotoService.save(alumniPhotoDTO);

                log.debug("Server File Location=" + serverFile.getAbsolutePath());


                log.debug("REST request to upload AlumniPhotos for query {}" + username);

                log.debug(String.format("receive %s from %s", file.getOriginalFilename(), username));
            } catch (Exception e) {}
        }

    }
}