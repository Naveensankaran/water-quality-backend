package com.wqs.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wqs.app.entity.WaterReading;

public interface WaterReadingRepository extends JpaRepository<WaterReading, Long> {
	 List<WaterReading> findByLocation(String location);
	 List<WaterReading> findByTimestampBetween(
		        LocalDateTime start,
		        LocalDateTime end
		    );
}
