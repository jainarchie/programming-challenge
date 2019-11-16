package com.shepherd.challenge.utils;

import com.shepherd.challenge.dto.SensorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SensorEventValidator {

    public static Logger logger = LoggerFactory.getLogger(SensorEventValidator.class);

    /**
     * Method validates if the event is not null, and all the values are populated.
     * @param sensorEvent
     * @return
     */
    public Boolean isIncomingEventValid(SensorEvent sensorEvent){
        logger.info("Validating the sensor event.");
        if(sensorEvent==null){
            logger.info("Sensor event is null");
            return false;
        }
        if(sensorEvent.getEventId()==null ||
                sensorEvent.getSensorId()==null ||
                sensorEvent.getSensorValue()==null ||
                sensorEvent.getTimestamp()==null){
            logger.info("All the fields of the sensor event are not populated.");
            return false;
        }
        logger.info("Sensor Event validation success.");
        return true;
    }
}
