package com.codegym.controller;

import com.codegym.model.Country;
import com.codegym.model.Landscape;
import com.codegym.service.ICountryService;
import com.codegym.service.ILandscapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/landscapes")
public class LandscapeController {
    @Autowired
    private Environment environment;
    @Autowired
    private ILandscapeService landscapeService;
    @Autowired
    private ICountryService countryService;
    @ModelAttribute("listcountries")
    public Iterable<Country> countries(){
        return countryService.findAll();
    }

    @GetMapping("")
    public ModelAndView listLandscape(@RequestParam("s") Optional<String> s, @PageableDefault(size = 3)Pageable pageable){
        Page<Landscape> landscapes;
        ModelAndView modelAndView = new ModelAndView("/landscape/list");
        if(s.isPresent()){
            landscapes = landscapeService.findAllByNameContaining(s.get(), pageable);
            modelAndView.addObject("s", s.get());
        }else{
            landscapes = landscapeService.findAll(pageable);
        }


        modelAndView.addObject("landscapes", landscapes);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/landscape/edit");
        modelAndView.addObject("landscape", landscapeService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editLandscape(@ModelAttribute Landscape landscape, @PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/landscape/edit");
        modelAndView.addObject("landscape", landscape);
        Landscape oldLandScape = landscapeService.findById(id);
        landscape.setImage(oldLandScape.getImage());
        landscapeService.save(landscape);
        modelAndView.addObject("mess", "done edit");
        return new ModelAndView("redirect:" + "/landscapes");
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/landscape/create");
        modelAndView.addObject("landscape", new Landscape());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createLandscape(@ModelAttribute Landscape landscapeForm){
        MultipartFile file = landscapeForm.getAvatar();
        String image = file.getOriginalFilename();
        landscapeForm.setImage(image);
        String fileUpload = environment.getProperty("file_upload").toString();
        try{
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        }catch (IOException e){
            e.printStackTrace();
        }
        Landscape landscape = new Landscape(landscapeForm.getName(), landscapeForm.getContent(),image, landscapeForm.getCountry());
        landscapeService.save(landscape);
        ModelAndView modelAndView = new ModelAndView("/landscape/create");
        modelAndView.addObject("message", "Country updated successfully");
        return new ModelAndView("/landscape/create", "landscape", new Landscape());
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable Long id){
        Landscape landscape = landscapeService.findById(id);
        if(landscape != null){
            ModelAndView modelAndView = new ModelAndView("/landscape/delete");
            modelAndView.addObject("landscape", landscape);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteCountry(@ModelAttribute("landscape") Landscape landscape){
        landscapeService.remove(landscape.getId());
        return "redirect:";
    }
}
