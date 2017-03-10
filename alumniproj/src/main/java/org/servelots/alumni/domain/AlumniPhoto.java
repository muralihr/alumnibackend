package org.servelots.alumni.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A AlumniPhoto.
 */
@Entity
@Table(name = "alumni_photo")
@Document(indexName = "alumniphoto")
public class AlumniPhoto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @NotNull
    @Column(name = "media_type", nullable = false)
    private Integer mediaType;

    @Lob
    @Column(name = "media_file")
    private byte[] mediaFile;

    @Column(name = "media_file_content_type")
    private String mediaFileContentType;

    @Column(name = "url_orfile_link")
    private String urlOrfileLink;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "upload_time")
    private ZonedDateTime uploadTime;

    @ManyToOne
    private User alumniuser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public AlumniPhoto title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public AlumniPhoto description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public AlumniPhoto address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public AlumniPhoto latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public AlumniPhoto longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public AlumniPhoto mediaType(Integer mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getMediaFile() {
        return mediaFile;
    }

    public AlumniPhoto mediaFile(byte[] mediaFile) {
        this.mediaFile = mediaFile;
        return this;
    }

    public void setMediaFile(byte[] mediaFile) {
        this.mediaFile = mediaFile;
    }

    public String getMediaFileContentType() {
        return mediaFileContentType;
    }

    public AlumniPhoto mediaFileContentType(String mediaFileContentType) {
        this.mediaFileContentType = mediaFileContentType;
        return this;
    }

    public void setMediaFileContentType(String mediaFileContentType) {
        this.mediaFileContentType = mediaFileContentType;
    }

    public String getUrlOrfileLink() {
        return urlOrfileLink;
    }

    public AlumniPhoto urlOrfileLink(String urlOrfileLink) {
        this.urlOrfileLink = urlOrfileLink;
        return this;
    }

    public void setUrlOrfileLink(String urlOrfileLink) {
        this.urlOrfileLink = urlOrfileLink;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public AlumniPhoto userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public ZonedDateTime getUploadTime() {
        return uploadTime;
    }

    public AlumniPhoto uploadTime(ZonedDateTime uploadTime) {
        this.uploadTime = uploadTime;
        return this;
    }

    public void setUploadTime(ZonedDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public User getAlumniuser() {
        return alumniuser;
    }

    public AlumniPhoto alumniuser(User user) {
        this.alumniuser = user;
        return this;
    }

    public void setAlumniuser(User user) {
        this.alumniuser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AlumniPhoto alumniPhoto = (AlumniPhoto) o;
        if (alumniPhoto.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, alumniPhoto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AlumniPhoto{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", description='" + description + "'" +
            ", address='" + address + "'" +
            ", latitude='" + latitude + "'" +
            ", longitude='" + longitude + "'" +
            ", mediaType='" + mediaType + "'" +
            ", mediaFile='" + mediaFile + "'" +
            ", mediaFileContentType='" + mediaFileContentType + "'" +
            ", urlOrfileLink='" + urlOrfileLink + "'" +
            ", userAgent='" + userAgent + "'" +
            ", uploadTime='" + uploadTime + "'" +
            '}';
    }
}
