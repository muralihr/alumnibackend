package org.servelots.alumni.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the AlumniPhoto entity.
 */
public class AlumniPhotoDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    private String address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Integer mediaType;

    @Lob
    private byte[] mediaFile;
    private String mediaFileContentType;

    private String urlOrfileLink;

    private String userAgent;

    private ZonedDateTime uploadTime;

    private Long alumniuserId;

    private String alumniuserLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }
    public byte[] getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(byte[] mediaFile) {
        this.mediaFile = mediaFile;
    }

    public String getMediaFileContentType() {
        return mediaFileContentType;
    }

    public void setMediaFileContentType(String mediaFileContentType) {
        this.mediaFileContentType = mediaFileContentType;
    }
    public String getUrlOrfileLink() {
        return urlOrfileLink;
    }

    public void setUrlOrfileLink(String urlOrfileLink) {
        this.urlOrfileLink = urlOrfileLink;
    }
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public ZonedDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(ZonedDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getAlumniuserId() {
        return alumniuserId;
    }

    public void setAlumniuserId(Long userId) {
        this.alumniuserId = userId;
    }

    public String getAlumniuserLogin() {
        return alumniuserLogin;
    }

    public void setAlumniuserLogin(String userLogin) {
        this.alumniuserLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlumniPhotoDTO alumniPhotoDTO = (AlumniPhotoDTO) o;

        if ( ! Objects.equals(id, alumniPhotoDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AlumniPhotoDTO{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", description='" + description + "'" +
            ", address='" + address + "'" +
            ", latitude='" + latitude + "'" +
            ", longitude='" + longitude + "'" +
            ", mediaType='" + mediaType + "'" +
            ", mediaFile='" + mediaFile + "'" +
            ", urlOrfileLink='" + urlOrfileLink + "'" +
            ", userAgent='" + userAgent + "'" +
            ", uploadTime='" + uploadTime + "'" +
            '}';
    }
}
