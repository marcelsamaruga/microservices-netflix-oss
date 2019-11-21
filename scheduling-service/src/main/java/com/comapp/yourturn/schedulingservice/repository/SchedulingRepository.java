package com.comapp.yourturn.schedulingservice.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comapp.yourturn.schedulingservice.entity.Scheduling;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

	Optional<List<Scheduling>> findSchedulingByDate(Date date);
	
}
