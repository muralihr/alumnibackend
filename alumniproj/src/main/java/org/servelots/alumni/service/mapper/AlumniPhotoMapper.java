package org.servelots.alumni.service.mapper;

import org.servelots.alumni.domain.*;
import org.servelots.alumni.service.dto.AlumniPhotoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity AlumniPhoto and its DTO AlumniPhotoDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface AlumniPhotoMapper {

    @Mapping(source = "alumniuser.id", target = "alumniuserId")
    @Mapping(source = "alumniuser.login", target = "alumniuserLogin")
    AlumniPhotoDTO alumniPhotoToAlumniPhotoDTO(AlumniPhoto alumniPhoto);

    List<AlumniPhotoDTO> alumniPhotosToAlumniPhotoDTOs(List<AlumniPhoto> alumniPhotos);

    @Mapping(source = "alumniuserId", target = "alumniuser")
    AlumniPhoto alumniPhotoDTOToAlumniPhoto(AlumniPhotoDTO alumniPhotoDTO);

    List<AlumniPhoto> alumniPhotoDTOsToAlumniPhotos(List<AlumniPhotoDTO> alumniPhotoDTOs);
}
