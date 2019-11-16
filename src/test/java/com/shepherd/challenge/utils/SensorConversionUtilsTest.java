package com.shepherd.challenge.utils;

import com.shepherd.challenge.TestUtils;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.dto.SensorResponse;
import com.shepherd.challenge.enums.SensorStatus;
import com.shepherd.challenge.model.SensorEventLog;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
class SensorConversionUtilsTest {

    /**
     * Tests success response for the methods
     */
    @Test
    void getSensorResponseForSensorEvent() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        SensorResponse sensorResponse = SensorConversionUtils.getSensorResponseForSensorEvent(sensorEvent);
        Assert.assertEquals(sensorResponse.getTimestamp(),sensorEvent.getTimestamp());
        Assert.assertEquals(sensorResponse.getSensorValue(), sensorEvent.getSensorValue());
        Assert.assertEquals(sensorResponse.getSensorStatus(), SensorStatus.NO_MODEL);
        Assert.assertEquals(sensorResponse.getSensorId(), sensorEvent.getSensorId());
        Assert.assertEquals(sensorResponse.getCause(), "");
        Assert.assertEquals(sensorResponse.getMessage(), "");
        Assert.assertEquals(sensorResponse.getEventId(), sensorEvent.getEventId());
    }

    /**
     * Tests method for null argument
     */
    @Test
    void getSensorResponseForSensorEventNull() {
        try{
        SensorConversionUtils.getSensorResponseForSensorEvent(null);}
        catch (IllegalArgumentException e){
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(false);
    }


    /**
     * Tests success response for the methods
     */
    @Test
    void getSensorLogForSensorEvent() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        SensorEventLog sensorLog = SensorConversionUtils.getSensorLog(sensorEvent);
        Assert.assertEquals(sensorLog.getTimestamp(),sensorEvent.getTimestamp());
        Assert.assertEquals(sensorLog.getValue(), sensorEvent.getSensorValue());
        Assert.assertEquals(sensorLog.getSensorId(), sensorEvent.getSensorId());
        Assert.assertEquals(sensorLog.getEventId(), sensorEvent.getEventId());
    }

    /**
     * Tests method for null argument
     */
    @Test
    void getSensorLogForSensorEventNull() {
        try{
            SensorConversionUtils.getSensorLog(null);}
        catch (IllegalArgumentException e){
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(false);
    }
}

