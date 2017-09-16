package xyz.isatimur.course.application.domain;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ContentType.
 */
@NoArgsConstructor
@Entity
@Table(name = "course_list_course_conttypes")
public class ContentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_list_course_conttypes_id_seq")
    @SequenceGenerator(
        name = "course_list_course_conttypes_id_seq",
        sequenceName = "course_list_course_conttypes_id_seq",
        initialValue = 1000,
        allocationSize = 1
    )
    private Long id;

    @Column(name = "name")
    private String name;

    public ContentType(String name) {
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

    public ContentType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
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
        ContentType contentType = (ContentType) o;
        if (contentType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contentType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContentType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
