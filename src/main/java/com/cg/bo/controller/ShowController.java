package com.cg.bo.controller;

import com.cg.bo.model.projection.Schedule;
import com.cg.bo.model.projection.Show;
import com.cg.bo.service.impl.ScheduleServiceImpl;
import com.cg.bo.service.impl.ShowServiceImpl;
import com.cg.bo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowServiceImpl showService;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @GetMapping("/create")
    public ModelAndView showCreateShowForm(){
        return new ModelAndView("/projection/show/create");
    }

    @PostMapping("/create")
    public ResponseEntity<Show> createNewShow(@RequestBody Show show){
        return new ResponseEntity<>(showService.save(show), HttpStatus.CREATED);
    }


    @GetMapping("/allActiveShowsToday/{scheduleId}")
    public ResponseEntity<Iterable<Show>> findAllShowStatusTrue(@PathVariable Long scheduleId){
        Schedule schedule  = scheduleService.findById(scheduleId).get();
        Iterable<Show> shows = showService.findShowsBySchedule(schedule);
        setStatusForShow(shows);
        return new ResponseEntity<>(showService.findAllByScheduleAndStatusTrue(schedule), HttpStatus.OK);
    }

    public void setStatusForShow(Iterable<Show> shows){
        for (Show s: shows
        ) {
            s.setStatus(dateUtils.differentTimeInMinutes(s.getTime_start().toString()) <= 30);
            showService.save(s);
        }
    }
}