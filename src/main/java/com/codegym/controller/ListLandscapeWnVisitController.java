package com.codegym.controller;

import com.codegym.model.Landscape;
import com.codegym.model.ListLandscapeWannaVisit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("mylistfavourite")
@RequestMapping("/listlandscapeswnvisit")
public class ListLandscapeWnVisitController {
    @GetMapping("")
    public ModelAndView displayListFavouriteLandscape(@ModelAttribute("mylistfavourite") ListLandscapeWannaVisit listLandscapeWannaVisit){
        ModelAndView modelAndView = new ModelAndView("/listlandscapewnvisit/list");
        for(Landscape landscape : listLandscapeWannaVisit.getLandscapesWnVisit()){
            System.out.println(landscape.getName());
        }
        modelAndView.addObject("landscapes", listLandscapeWannaVisit.getLandscapesWnVisit());
        return modelAndView;
    }
}
