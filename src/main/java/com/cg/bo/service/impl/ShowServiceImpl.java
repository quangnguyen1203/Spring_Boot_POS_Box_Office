package com.cg.bo.service.impl;

import com.cg.bo.model.projection.Show;
import com.cg.bo.repository.ShowRepository;
import com.cg.bo.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Override
    public Iterable<Show> findAll() {
        return showRepository.findAll();
    }

    @Override
    public Optional<Show> findById(Long id) {
        return showRepository.findById(id);
    }

    @Override
    public Show save(Show show) {
        return showRepository.save(show);
    }

    @Override
    public void remove(Long id) {
        showRepository.deleteById(id);
    }


}