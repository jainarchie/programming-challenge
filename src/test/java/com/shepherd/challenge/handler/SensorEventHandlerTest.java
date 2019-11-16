package com.shepherd.challenge.handler;

import com.shepherd.challenge.TestUtils;
import com.shepherd.challenge.dal.SensorEventLogRepository;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.dto.SensorResponse;
import com.shepherd.challenge.utils.SensorConversionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SensorEventHandlerTest {

    @InjectMocks
    private SensorEventHandler sensorEventHandler;

    @Mock
    private SensorEventLogRepository sensorEventLogRepository;

    @Mock
    private SensorConversionUtils sensorConversionUtils;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests the happy case of logging the event
     * @throws Exception
     */
    @Test
    public void testLogEventSuccess(){
        SensorEvent sensorEvent= TestUtils.getSensorEventObject();
        SensorResponse sensorResponse = SensorConversionUtils.getSensorResponseForSensorEvent(sensorEvent);
        SensorResponse sensorResponseResult = sensorEventHandler.logEvent(sensorEvent);
        Assert.assertTrue(sensorResponseResult.getMessage().equalsIgnoreCase(sensorResponse.getMessage()));
        Assert.assertTrue(sensorResponseResult.getEventId().equalsIgnoreCase(sensorResponse.getEventId()));
        Assert.assertTrue(sensorResponseResult.getCause().equalsIgnoreCase(sensorResponse.getCause()));
        Assert.assertTrue(sensorResponseResult.getSensorId().equalsIgnoreCase(sensorResponse.getSensorId()));
        Assert.assertTrue(sensorResponseResult.getSensorStatus().equals(sensorResponse.getSensorStatus()));
        Assert.assertTrue(sensorResponseResult.getSensorValue().equals(sensorResponse.getSensorValue()));
        Assert.assertTrue(sensorResponseResult.getTimestamp().equals(sensorResponse.getTimestamp()));
    }


    /*
    Tests if null object is sent then throws null pointer exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLogEventNullObject() {
        SensorEvent sensorEvent = null;
        Mockito.when(this.sensorConversionUtils.getSensorResponseForSensorEvent(sensorEvent)).thenThrow(IllegalArgumentException.class);
        sensorEventHandler.logEvent(sensorEvent);
    }







}