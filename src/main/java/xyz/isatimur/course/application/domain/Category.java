package xyz.isatimur.course.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Category.
 */
@NoArgsConstructor
@Entity
@Table(name = "course_list_course_cats")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
        name = "course_list_course_cats_id_seq",
        sequenceName = "course_list_course_cats_id_seq",
        initialValue = 1000,
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "course_list_course_cats_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Set<Course> categories) {
        this.name = name;
        this.categories = categories;
    }

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Course> categories = new HashSet<>();

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

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCategories() {
        return categories;
    }

    public Category categories(Set<Course> courses) {
        this.categories = courses;
        return this;
    }

    public Category addCategory(Course course) {
        this.categories.add(course);
        course.setCategory(this);
        return this;
    }

    public Category removeCategory(Course course) {
        this.categories.remove(course);
        course.setCategory(null);
        return this;
    }

    public void setCategories(Set<Course> courses) {
        this.categories = courses;
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
        Category category = (Category) o;
        if (category.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
