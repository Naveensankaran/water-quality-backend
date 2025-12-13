package com.wqs.app.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wqs.app.dto.WaterRequest;
import com.wqs.app.entity.WaterReading;
import com.wqs.app.repository.WaterReadingRepository;
import com.wqs.app.service.WaterService;

@Service
public class WaterServiceImpl implements WaterService {

    private final WaterReadingRepository repo;

    public WaterServiceImpl(WaterReadingRepository repo) {
        this.repo = repo;
    }

    @Override
    public WaterReading addReading(WaterRequest r) {

        String quality = "Good";

        if (r.getPh() < 6 || r.getPh() > 8) quality = "Poor";
        else if (r.getTurbidity() > 5) quality = "Poor";
        else if (r.getTds() > 500) quality = "Moderate";

        WaterReading reading = new WaterReading();
        reading.setPh(r.getPh());
        reading.setTurbidity(r.getTurbidity());
        reading.setTds(r.getTds());
        reading.setTemperature(r.getTemperature());
        reading.setQuality(quality);
        reading.setLocation(r.getLocation());



        return repo.save(reading);
    }

    @Override
    public List<WaterReading> getAll() {
        return repo.findAll();
    }
    
    @Override
    public WaterReading getById(Long id) {
        return repo.findById(id).orElse(null);
    }
    
    @Override
    public List<WaterReading> getByLocation(String location) {
        return repo.findByLocation(location);
    }

    @Override
    public List<WaterReading> getByDate(LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);

        return repo.findByTimestampBetween(start, end);
    }
    
    @Override
    public WaterReading updateReading(Long id, WaterRequest r) {

        WaterReading reading = repo.findById(id).orElse(null);

        if (reading == null) {
            return null;
        }

        // Recalculate quality
        String quality = "Good";

        if (r.getPh() < 6 || r.getPh() > 8) quality = "Poor";
        else if (r.getTurbidity() > 5) quality = "Poor";
        else if (r.getTds() > 500) quality = "Moderate";

        reading.setPh(r.getPh());
        reading.setTurbidity(r.getTurbidity());
        reading.setTds(r.getTds());
        reading.setTemperature(r.getTemperature());
        reading.setLocation(r.getLocation());
        reading.setQuality(quality);

        return repo.save(reading);
    }

    @Override
    public String deleteById(Long id) {

        if (!repo.existsById(id)) {
            return "Water reading not found";
        }

        repo.deleteById(id);
        return "Water reading deleted successfully";
    }


}
