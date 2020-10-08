package com.codegym.service.impl;

import com.codegym.model.Landscape;
import com.codegym.service.ILandscapeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LandscapeServiceImpl implements ILandscapeService {
    private static Map<Long, Landscape> listLandscapes;
    static {
        listLandscapes = new HashMap<>();
        listLandscapes.put(1L, new Landscape(1L, "Phượng hoàng cổ trấn", "Tung Của", "1.png"));
        listLandscapes.put(2L, new Landscape(2L,"Vịnh Hạ Long", "Việt Nam", "2.png"));
        listLandscapes.put(3L, new Landscape(3L,"Verdon Gorge", "Pháp", "1.png"));
        listLandscapes.put(4L, new Landscape(4L,"Hồ nước Thần Tiên", "Scotland", "4.png"));
    }

    @Override
    public List<Landscape> findAll() {
        ArrayList list = new ArrayList<>(listLandscapes.values());
        return list;
    }

    @Override
    public Landscape findById(Long id) {
        return listLandscapes.get(id);
    }

    @Override
    public void update(Landscape model) {
        listLandscapes.put(model.getId(), model);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void save(Landscape model) {
        Long stt = listLandscapes.size() + 1L;
        model.setId(stt);
        listLandscapes.put(stt, model);

    }
}
