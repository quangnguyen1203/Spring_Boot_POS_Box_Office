package com.cg.bo.service.impl;

import com.cg.bo.model.projection.Schedule;
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


    @Override
    public Iterable<Show> findShowsBySchedule(Schedule schedule) {
        return showRepository.findShowsBySchedule(schedule);
    }

    @Override
    public Iterable<Show> findAllByScheduleAndStatusTrue(Schedule schedule) {
        return showRepository.findAllByScheduleAndStatusTrueOrderByScheduleDesc(schedule);
    }

    @Override
    public Iterable<Show> searchShowOfScheduleWhereShowNameLike(Long schedule_id, String film_name) {
        return showRepository.searchShowOfScheduleWhereShowNameLike(schedule_id,film_name);
    }
}