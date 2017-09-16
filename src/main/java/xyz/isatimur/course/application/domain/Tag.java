package xyz.isatimur.course.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Tag.
 */
@NoArgsConstructor
@Entity
@Table(name = "course_data_tags")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
        name = "course_data_tags_id_seq",
        sequenceName = "course_data_tags_id_seq",
        allocationSize = 1,
        initialValue = 1000)
    @GeneratedValue(generator = "course_data_tags_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    public Tag(String name, Set<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public Tag(String name) {

        this.name = name;
    }

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Tag name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Tag courses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public Tag addCourse(Course course) {
        this.courses.add(course);
        course.getTags().add(this);
        return this;
    }

    public Tag removeCourse(Course course) {
        this.courses.remove(course);
        course.getTags().remove(this);
        return this;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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
        Tag tag = (Tag) o;
        if (tag.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tag{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
