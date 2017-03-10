package org.servelots.alumni.repository;

import org.servelots.alumni.domain.Alumni;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Alumni entity.
 */
@SuppressWarnings("unused")
public interface AlumniRepository extends JpaRepository<Alumni,Long> {
	

    Alumni findByCornellAlumniIDNumber(String alumniId);

}
