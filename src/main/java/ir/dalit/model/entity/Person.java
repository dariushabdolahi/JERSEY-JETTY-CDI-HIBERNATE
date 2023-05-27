package ir.dalit.model.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ps")
    @SequenceGenerator(name = "ps")
    private Long id;

    @Column(columnDefinition = "VARCHAR2(20)")
    private String name;

    @Column(columnDefinition = "VARCHAR2(20)")
    private String family;

    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Person setFamily(String family) {
        this.family = family;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }
}
