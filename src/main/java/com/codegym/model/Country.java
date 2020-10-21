package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "country",fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"country"})
    private List<Landscape> landscapes;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Landscape> getLandscapes() {
        return landscapes;
    }

    public void setLandscapes(List<Landscape> landscapes) {
        this.landscapes = landscapes;
    }

    @Override
    public String toString() {
        return name;
    }
}
