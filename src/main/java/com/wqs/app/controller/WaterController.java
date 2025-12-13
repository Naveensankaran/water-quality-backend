package com.wqs.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wqs.app.dto.WaterRequest;
import com.wqs.app.entity.WaterReading;
import com.wqs.app.service.WaterService;

@RestController
@RequestMapping("/api/water-readings")
public class WaterController {

    private final WaterService service;

    public WaterController(WaterService service) {
        this.service = service;
    }

    // ADD WATER READING
    @PostMapping
    public WaterReading add(@RequestBody WaterRequest req) {
        return service.addReading(req);
    }

    // GET ALL READINGS
    @GetMapping
    public List<WaterReading> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public WaterReading getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @GetMapping("/location/{location}")
    public List<WaterReading> getByLocation(@PathVariable String location) {
        return service.getByLocation(location);
    }

    @GetMapping("/date")
    public List<WaterReading> getByDate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return service.getByDate(date);
    }
    
    @PutMapping("/{id}")
    public WaterReading update(
            @PathVariable Long id,
            @RequestBody WaterRequest request) {

        return service.updateReading(id, request);
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteById(id);
    }



}
