package xyz.isatimur.course.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * A Course.
 */
@NoArgsConstructor
@Entity
@Table(name = "course_data_courses", uniqueConstraints = @UniqueConstraint(columnNames = "url"))
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
        name = "course_data_courses_id_seq",
        sequenceName = "course_data_courses_id_seq",
        allocationSize = 1,
        initialValue = 1000)
    @GeneratedValue(generator = "course_data_courses_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "publish_date")
    private ZonedDateTime publishDate;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "logo_url")
    private String logoUrl;

    @OneToOne
    @JoinColumn(unique = true)
    private Status status;

    @OneToOne
    @JoinColumn(unique = true)
    private Category category;

    @OneToOne
    @JoinColumn(unique = true)
    private Author author;

    @OneToMany
    @JoinTable(name = "course_ref_course_built",
        joinColumns = @JoinColumn(name = "id_course", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_source", referencedColumnName = "id"))
    @JsonIgnore
    private List<Material> materials = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "course_ref_courses_tags",
        joinColumns = @JoinColumn(name = "id_courses", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_tags", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();

    public Course(String url, String title, String shortDescription, String description, Long duration, ZonedDateTime publishDate, BigDecimal price, String logoUrl, Status status, Category category, Author author, List<Material> materials, Set<Tag> tags) {
        this.url = url;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.duration = duration;
        this.publishDate = publishDate;
        this.price = price;
        this.logoUrl = logoUrl;
        this.status = status;
        this.category = category;
        this.author = author;
        this.materials = materials;
        this.tags = tags;
    }

    public static CourseBuilder builder() {
        return new CourseBuilder();
    }

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public Course url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public Course title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Course shortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public Course description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public Course duration(Long duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public ZonedDateTime getPublishDate() {
        return publishDate;
    }

    public Course publishDate(ZonedDateTime publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public void setPublishDate(ZonedDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Course price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Course logoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Status getStatus() {
        return status;
    }

    public Course status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public Course category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public Course author(Author author) {
        this.author = author;
        return this;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public Course materials(List<Material> materials) {
        this.materials = materials;
        return this;
    }

    public Course addMaterial(Material material) {
        this.materials.add(material);
        material.setCourse(this);
        return this;
    }

    public Course removeMaterial(Material material) {
        this.materials.remove(material);
        material.setCourse(null);
        return this;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Course tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Course addTag(Tag tag) {
        this.tags.add(tag);
        tag.getCourses().add(this);
        return this;
    }

    public Course removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getCourses().remove(this);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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
        Course course = (Course) o;
        if (course.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", title='" + getTitle() + "'" +
            ", shortDescription='" + getShortDescription() + "'" +
            ", description='" + getDescription() + "'" +
            ", duration='" + getDuration() + "'" +
            ", publishDate='" + getPublishDate() + "'" +
            ", price='" + getPrice() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            "}";
    }

    public static class CourseBuilder {
        private Long id;
        private String url;
        private String title;
        private String shortDescription;
        private String description;
        private Long duration;
        private ZonedDateTime publishDate;
        private BigDecimal price;
        private String logoUrl;
        private Status status;
        private Category category;
        private Author author;
        private List<Material> materials;
        private Set<Tag> tags;

        CourseBuilder() {
        }

        public CourseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CourseBuilder url(String url) {
            this.url = url;
            return this;
        }

        public CourseBuilder title(String title) {
            this.title = title;
            return this;
        }

        public CourseBuilder shortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public CourseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CourseBuilder duration(Long duration) {
            this.duration = duration;
            return this;
        }

        public CourseBuilder publishDate(ZonedDateTime publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public CourseBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public CourseBuilder logoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        public CourseBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public CourseBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public CourseBuilder author(Author author) {
            this.author = author;
            return this;
        }

        public CourseBuilder materials(List<Material> materials) {
            this.materials = materials;
            return this;
        }

        public CourseBuilder tags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Course build() {
            return new Course(url, title, shortDescription, description, duration, publishDate, price, logoUrl, status, category, author, materials, tags);
        }

        public String toString() {
            return "Course.CourseBuilder(id=" + this.id + ", url=" + this.url + ", title=" + this.title + ", shortDescription=" + this.shortDescription + ", description=" + this.description + ", duration=" + this.duration + ", publishDate=" + this.publishDate + ", price=" + this.price + ", logoUrl=" + this.logoUrl + ", status=" + this.status + ", category=" + this.category + ", author=" + this.author + ", materials=" + this.materials + ", tags=" + this.tags + ")";
        }
    }
}
