package com.codegym.model;

import java.util.*;

public class ListLandscapeWannaVisit {
    private List<Landscape> landscapesWnVisit;

    public ListLandscapeWannaVisit() {
        landscapesWnVisit = new ArrayList<Landscape>();
    }

    public List<Landscape> getLandscapesWnVisit() {
        return landscapesWnVisit;
    }

    public void setLandscapesWnVisit(List<Landscape> landscapesWnVisit) {
        this.landscapesWnVisit = landscapesWnVisit;
    }

    public void addNewLandscape(Landscape landscape){
        landscapesWnVisit.add(landscape);
    }
}
