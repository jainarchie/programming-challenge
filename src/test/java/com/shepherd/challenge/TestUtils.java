package com.shepherd.challenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shepherd.challenge.dto.SensorEvent;
import org.springframework.web.client.RestClientException;

public class TestUtils {

    public static SensorEvent getSensorEventObject(){
        SensorEvent sensorEvent = new  SensorEvent();
        sensorEvent.setEventId("EventId");
        sensorEvent.setSensorId("SensorId");
        sensorEvent.setSensorValue(50.0);
        sensorEvent.setTimestamp(1573900751000l);
        return sensorEvent;
    }


    public static String serializeObject(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RestClientException("Couldn't serialize object :" + obj, e);
        }
    }
}
