package com.shepherd.challenge.utils;

import com.shepherd.challenge.TestUtils;
import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.dto.SensorResponse;
import com.shepherd.challenge.enums.SensorStatus;
import com.shepherd.challenge.model.SensorEventLog;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
class SensorUtilsTest {

    /**
     * Tests success response for the methods
     */
    @Test
    void getSensorResponseForSensorEvent() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        SensorResponse sensorResponse = SensorUtils.getSensorResponseForSensorEvent(sensorEvent);
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
        SensorUtils.getSensorResponseForSensorEvent(null);}
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
        SensorEventLog sensorLog = SensorUtils.getSensorLog(sensorEvent);
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
            SensorUtils.getSensorLog(null);}
        catch (IllegalArgumentException e){
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(false);
    }

    @Test
    void setStatusByPriority() {
        AnomalyDetectorResponse anomalyDetectorResponse1 = TestUtils.getAnomalyDetectorResponse();
        AnomalyDetectorResponse anomalyDetectorResponse2 = TestUtils.getAnomalyDetectorResponse();
        anomalyDetectorResponse2.setSensorStatus(SensorStatus.ANOMALY);
        AnomalyDetectorResponse result = SensorUtils.setStatusByPriority(anomalyDetectorResponse1,anomalyDetectorResponse2);
        Assert.assertTrue(result.getSensorStatus().equals(SensorStatus.ANOMALY));
        anomalyDetectorResponse2.setSensorStatus(SensorStatus.NO_ANOMALY);
        result = SensorUtils.setStatusByPriority(anomalyDetectorResponse1,anomalyDetectorResponse2);
        Assert.assertTrue(result.getSensorStatus().equals(SensorStatus.NO_ANOMALY));
        anomalyDetectorResponse1.setSensorStatus(SensorStatus.ANOMALY);
        result = SensorUtils.setStatusByPriority(anomalyDetectorResponse1,anomalyDetectorResponse2);
        Assert.assertTrue(result.getSensorStatus().equals(SensorStatus.ANOMALY));
    }

    @Test
    void setStatusByPriorityNull() {
        AnomalyDetectorResponse anomalyDetectorResponse2 = TestUtils.getAnomalyDetectorResponse();
        try{
        AnomalyDetectorResponse result = SensorUtils.setStatusByPriority(null,anomalyDetectorResponse2);}
        catch (Exception e){
            return;
        }
        Assert.assertTrue(false);

    }

    @Test
    void setStatusByPriorityNull2() {
        AnomalyDetectorResponse anomalyDetectorResponse2 = TestUtils.getAnomalyDetectorResponse();
        try{
            AnomalyDetectorResponse result = SensorUtils.setStatusByPriority(anomalyDetectorResponse2,null);}
        catch (Exception e){
            return;
        }
        Assert.assertTrue(false);

    }

    @Test
    void getSensorResponseForEvent() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        SensorResponse response = SensorUtils.getSensorResponseForEvent(sensorEvent,null);
        Assert.assertTrue(response.getEventId().equalsIgnoreCase(sensorEvent.getEventId()));
    }

    @Test
    void getSensorResponseForEvent2() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        AnomalyDetectorResponse anomalyDetectorResponse = TestUtils.getAnomalyDetectorResponse();
        SensorResponse result = SensorUtils.getSensorResponseForEvent(sensorEvent,anomalyDetectorResponse);
        Assert.assertTrue(result.getSensorStatus().equals(SensorStatus.NO_MODEL));


    }
}

