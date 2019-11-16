package com.shepherd.challenge.controller;


import com.shepherd.challenge.TestUtils;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.dto.SensorResponse;
import com.shepherd.challenge.handler.SensorEventHandler;
import com.shepherd.challenge.utils.SensorUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class SensorEventControllerTest {


    @InjectMocks
    private SensorEventController sensorEventController;

    @Mock
    private SensorEventHandler sensorEventHandler;



    private MockMvc mockMvc;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sensorEventController).build();
    }

    /**
     * Tests the happy case of logging the event
     * @throws Exception
     */
    @Test
    public void testLogEventSuccess() throws Exception {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        SensorResponse sensorResponse = SensorUtils.getSensorResponseForSensorEvent(sensorEvent);
        Mockito.when(sensorEventHandler.logEvent(sensorEvent)).thenReturn(sensorResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/sensor/logEvent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.serializeObject(sensorEvent)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     *Tests error in case of null object sent in request
     * @throws Exception
     */
    @Test
    public void testLogEventNullEvent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sensor/logEvent")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * Tests invalid object sent in request
     * @throws Exception
     */
    @Test
    public void testLogEventInvalidObject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sensor/logEvent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.serializeObject("testString")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }


}