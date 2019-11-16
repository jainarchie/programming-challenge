package com.shepherd.challenge.dal;


import com.shepherd.challenge.model.SensorEventLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorEventLogRepository extends CrudRepository<SensorEventLog, Integer> {

}
