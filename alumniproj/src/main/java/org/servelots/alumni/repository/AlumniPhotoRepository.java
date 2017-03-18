package org.servelots.alumni.repository;

import org.servelots.alumni.domain.AlumniPhoto;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AlumniPhoto entity.
 */
@SuppressWarnings("unused")
public interface AlumniPhotoRepository extends JpaRepository<AlumniPhoto,Long> {

    @Query("select alumniPhoto from AlumniPhoto alumniPhoto where alumniPhoto.alumniuser.login = ?#{principal.username}")
    List<AlumniPhoto> findByAlumniuserIsCurrentUser();
	
	
    
    List<AlumniPhoto> findByTitle(String title);

}
