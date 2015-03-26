package com.mbagrov.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Odour on 13.02.2015.
 */
@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private Long id;

    @OneToMany(targetEntity = Person.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "company")
    private List<Person> persons;


    @Column(name = "company_name")
    private String name;

    public Company() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Company other = (Company) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
