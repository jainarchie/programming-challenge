package com.shepherd.challenge.handler;

import com.shepherd.challenge.dal.SensorEventLogRepository;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.dto.SensorResponse;
import com.shepherd.challenge.utils.SensorConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class SensorEventHandler {

    public static Logger logger = LoggerFactory.getLogger(SensorEventHandler.class);

    @Autowired
    SensorEventLogRepository sensorEventLogRepository;


    public SensorResponse logEvent(@NotNull SensorEvent sensorEvent) {
        if(sensorEvent==null){
            throw new IllegalArgumentException("Sensor Event Cannot be null");
        }
        logger.info("Received event for logging: "+ sensorEvent);
        sensorEventLogRepository.save(SensorConversionUtils.getSensorLog(sensorEvent));
        logger.info("Saved log to db.");
        SensorResponse sensorResponse = SensorConversionUtils.getSensorResponseForSensorEvent(sensorEvent);
        logger.info("Returning logEvent Response "+sensorResponse);
        return sensorResponse;
    }

}
