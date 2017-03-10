package org.servelots.alumni.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AlumniPhotoCategory.
 */
@Entity
@Table(name = "alumni_photo_category")
@Document(indexName = "alumniphotocategory")
public class AlumniPhotoCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_desription")
    private String categoryDesription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public AlumniPhotoCategory categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesription() {
        return categoryDesription;
    }

    public AlumniPhotoCategory categoryDesription(String categoryDesription) {
        this.categoryDesription = categoryDesription;
        return this;
    }

    public void setCategoryDesription(String categoryDesription) {
        this.categoryDesription = categoryDesription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AlumniPhotoCategory alumniPhotoCategory = (AlumniPhotoCategory) o;
        if (alumniPhotoCategory.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, alumniPhotoCategory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AlumniPhotoCategory{" +
            "id=" + id +
            ", categoryName='" + categoryName + "'" +
            ", categoryDesription='" + categoryDesription + "'" +
            '}';
    }
}
