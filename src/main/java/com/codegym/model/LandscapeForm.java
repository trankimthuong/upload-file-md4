package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class LandscapeForm {
    private Long id;
    private String name;
    private String country;
    private MultipartFile image;

    public LandscapeForm(Long id, String name, String country, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.image = image;
    }

    public LandscapeForm() {
    }

    public LandscapeForm(String name, String country, MultipartFile image) {
        this.name = name;
        this.country = country;
        this.image = image;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
