package com.shepherd.challenge.utils;

import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.dto.SensorResponse;
import com.shepherd.challenge.enums.SensorStatus;
import com.shepherd.challenge.model.SensorEventLog;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


/**
 * Utils class which stores utility methods for sensor related models.
 */
@Component
public class SensorConversionUtils {

    /**
     * Fetches a basic response object for a sensor event
     * @param sensorEvent
     * @return
     */
    public static SensorResponse getSensorResponseForSensorEvent(@NotNull SensorEvent sensorEvent) {
        if(sensorEvent==null){
            throw new IllegalArgumentException("Sensor Event Cannot Be null");
        }
        return SensorResponse.builder()
                .sensorId(sensorEvent.getSensorId())
                .sensorValue(sensorEvent.getSensorValue())
                .eventId(sensorEvent.getEventId())
                .timestamp(sensorEvent.getTimestamp())
                .cause("")
                .message("")
                .sensorStatus(SensorStatus.NO_MODEL)
                .build();
    }

    /**
     * Generates a sensor log for an event received.
     * @param sensorEvent
     * @return
     */
    public static SensorEventLog getSensorLog(@NotNull SensorEvent sensorEvent) {
        if(sensorEvent==null){
            throw new IllegalArgumentException("Sensor Event cannot be null");
        }
        return SensorEventLog.builder()
                .sensorId(sensorEvent.getSensorId())
                .timestamp(sensorEvent.getTimestamp())
                .eventId(sensorEvent.getEventId())
                .value(sensorEvent.getSensorValue()).build();
    }

}
