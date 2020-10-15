package com.codegym.controller;

import com.codegym.model.Country;
import com.codegym.service.ICountryService;
import com.codegym.service.ILandscapeService;
import com.codegym.service.exception.DuplicateCountryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @Autowired
    private ILandscapeService landscapeService;

    @GetMapping("")
    public ModelAndView listCountries(){
        Iterable<Country> countries = countryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/country/list");
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/country/create");
        modelAndView.addObject("country", new Country());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCountry(@ModelAttribute("country") Country country){
        try{
            countryService.save(country);
            ModelAndView modelAndView = new ModelAndView("/country/create");
            modelAndView.addObject("country", new Country());
            modelAndView.addObject("message", "New country created");
            return modelAndView;
        }catch (DuplicateCountryException e){
            return new ModelAndView("/error404");
        }

    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Country country = countryService.findById(id);
        if(country != null){
            ModelAndView modelAndView = new ModelAndView("/country/edit");
            modelAndView.addObject("country", country);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCountry(@ModelAttribute("country") Country country){
        try {
            countryService.save(country);
            ModelAndView modelAndView = new ModelAndView("/country/edit");
            modelAndView.addObject("country", country);
            modelAndView.addObject("message", "Country updated successfully");
            return modelAndView;
        }catch (DuplicateCountryException e){
            return new ModelAndView("/error404");
        }

    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Country country = countryService.findById(id);
        if(country != null){
            ModelAndView modelAndView = new ModelAndView("/country/delete");
            modelAndView.addObject("country", country);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteCountry(@ModelAttribute("country") Country country){
        countryService.remove(country.getId());
        return "redirect:";
    }
}
