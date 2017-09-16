package xyz.isatimur.course.application.domain;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Material.
 */
@NoArgsConstructor
@Entity
@Table(name = "course_data_materials")
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
        name = "course_data_materials_id_seq",
        sequenceName = "course_data_materials_id_seq",
        allocationSize = 1,
        initialValue = 1000)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "course_data_materials_id_seq")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "cdn")
    private String cdn;

    @OneToOne
    @JoinColumn(unique = true)
    private ContentType contentType;

    @ManyToMany
    private Set<Course> courses = new HashSet<>();

    public Material(String title, String description, Long duration, Long companyId, String cdn, ContentType contentType) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.companyId = companyId;
        this.cdn = cdn;
        this.contentType = contentType;
    }

    public static MaterialBuilder builder() {
        return new MaterialBuilder();
    }

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Material title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Material description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public Material duration(Long duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Material companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCdn() {
        return cdn;
    }

    public Material cdn(String cdn) {
        this.cdn = cdn;
        return this;
    }

    public void setCdn(String cdn) {
        this.cdn = cdn;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public Material contentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Material material = (Material) o;
        if (material.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), material.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Material{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", duration='" + getDuration() + "'" +
            ", companyId='" + getCompanyId() + "'" +
            ", cdn='" + getCdn() + "'" +
            "}";
    }

    public static class MaterialBuilder {
        private Long id;
        private String title;
        private String description;
        private Long duration;
        private Long companyId;
        private String cdn;
        private Course course;
        private ContentType contentType;

        MaterialBuilder() {
        }

        public MaterialBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MaterialBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MaterialBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MaterialBuilder duration(Long duration) {
            this.duration = duration;
            return this;
        }

        public MaterialBuilder companyId(Long companyId) {
            this.companyId = companyId;
            return this;
        }

        public MaterialBuilder cdn(String cdn) {
            this.cdn = cdn;
            return this;
        }

        public MaterialBuilder course(Course course) {
            this.course = course;
            return this;
        }

        public MaterialBuilder contentType(ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public Material build() {
            return new Material( title, description, duration, companyId, cdn,  contentType);
        }

        public String toString() {
            return "Material.MaterialBuilder(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", duration=" + this.duration + ", companyId=" + this.companyId + ", cdn=" + this.cdn + ", course=" + this.course + ", contentType=" + this.contentType + ")";
        }
    }
}
