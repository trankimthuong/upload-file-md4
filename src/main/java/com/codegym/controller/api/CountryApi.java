package com.codegym.controller.api;

import com.codegym.model.Country;
import com.codegym.service.ICountryService;
import com.codegym.service.exception.DuplicateCountryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/countries")
public class CountryApi {
    @Autowired
    private ICountryService countryService;

    @GetMapping("")
    public ResponseEntity<Iterable<Country>> findAll(){
        for (Country c: countryService.findAll()){
            System.out.println(c.getName());
        }
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getOneCountry(@PathVariable Long id){
        System.out.println(countryService.findById(id));
        return new ResponseEntity<>(countryService.findById(id), HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Country> create(@RequestBody Country country){
        try {
            countryService.save(country);
        } catch (DuplicateCountryException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Country> edit(@PathVariable Long id, @RequestBody Country country){
        country.setId(id);
        try {
            countryService.save(country);
        } catch (DuplicateCountryException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Country> delete(@PathVariable Long id){
        countryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
