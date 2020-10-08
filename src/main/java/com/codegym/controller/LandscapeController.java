package com.codegym.controller;

import com.codegym.model.Landscape;
import com.codegym.model.LandscapeForm;
import com.codegym.service.ILandscapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/landscapes")
public class LandscapeController {
    @Autowired
    private Environment environment;
    @Autowired
    private ILandscapeService landscapeService;

    @GetMapping()
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("landscape/list");
        modelAndView.addObject("list", landscapeService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/landscape/edit");
        modelAndView.addObject("landscape", landscapeService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editLandscape(@ModelAttribute Landscape landscape){
        ModelAndView modelAndView = new ModelAndView("/landscape/edit");
        modelAndView.addObject("landscape", landscape);
        landscapeService.update(landscape);
        modelAndView.addObject("mess", "done edit");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/landscape/create");
        modelAndView.addObject("landscape", new LandscapeForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createLandscape(@ModelAttribute LandscapeForm landscapeForm){
        Landscape landscape = new Landscape(landscapeForm.getName(), landscapeForm.getCountry());
        MultipartFile file = landscapeForm.getImage();
        String image = file.getOriginalFilename();
        landscape.setImage(image);
        String fileUpload = environment.getProperty("file_upload").toString();
        try{
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        }catch (IOException e){
            e.printStackTrace();
        }
        landscapeService.save(landscape);
        return new ModelAndView("/landscape/create", "landscape", new LandscapeForm());
    }
}
