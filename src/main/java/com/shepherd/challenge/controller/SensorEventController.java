package com.shepherd.challenge.controller;


import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.dto.SensorResponse;
import com.shepherd.challenge.handler.SensorEventHandler;
import com.shepherd.challenge.utils.SensorEventValidator;
import com.sun.media.sound.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor")
public class SensorEventController {

    public static Logger logger = LoggerFactory.getLogger(SensorEventController.class);

    @Autowired
    private SensorEventHandler sensorEventHandler;

    @Autowired
    private SensorEventValidator sensorEventValidator;

    /**
     * The api takes a sensor event object and logs it into database and console.
     * @param sensorEvent
     * @return
     */
    @PostMapping("/logEvent")
    public SensorResponse logEvent(@RequestBody(required = true) SensorEvent sensorEvent) throws InvalidDataException {
        logger.info("Received an event with Id: {}", sensorEvent.getEventId());
        return sensorEventHandler.logEvent(sensorEvent);
    }

    /**
     * This api takes in the sensor event, logs it, runs against anomaly detectors and returns a valid status.
     * @param sensorEvent
     * @return
     */
    @PostMapping("/senseEvent")
    public SensorResponse senseEvent(@RequestBody SensorEvent sensorEvent){
        logger.info("Received an event with id: {}", sensorEvent.getEventId());
        sensorEventValidator.isIncomingEventValid(sensorEvent);
        return sensorEventHandler.senseEvent(sensorEvent);
    }
}
