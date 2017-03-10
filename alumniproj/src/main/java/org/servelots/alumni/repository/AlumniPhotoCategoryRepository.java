package org.servelots.alumni.repository;

import org.servelots.alumni.domain.AlumniPhotoCategory;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AlumniPhotoCategory entity.
 */
@SuppressWarnings("unused")
public interface AlumniPhotoCategoryRepository extends JpaRepository<AlumniPhotoCategory,Long> {

}
