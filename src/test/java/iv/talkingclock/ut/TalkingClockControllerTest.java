package iv.talkingclock.ut;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import iv.talkingclock.rest.controller.TalkingClockController;

@RunWith(SpringRunner.class)
@WebMvcTest(TalkingClockController.class)
public class TalkingClockControllerTest {
    @Autowired
    private MockMvc mvc;
    
    @Test
    public void testCurrentTime() throws Exception
    {
	    MvcResult result =mvc.perform(MockMvcRequestBuilders
	        .get("/talking-clock")
		    .accept(MediaType.APPLICATION_JSON))
		    .andDo(print())
		    .andExpect(status().isOk())
		    .andReturn();
	    assertNotEquals(result.getResponse().getContentAsString(), "{\"value\":\"Invalid time input\"}");
    }
    
    @Test
    public void testInputTime1() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "01:00")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("One o'clock"));
    }

    @Test
    public void testInputTime2() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "02:00")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Two o'clock"));
    }
    
    @Test
    public void testInputTime3() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "13:00")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("One o'clock"));
    }
    
    @Test
    public void testInputTime4() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "13:05")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Five past one"));
    }
    
    @Test
    public void testInputTime5() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "13:10")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Ten past one"));
    }
    
    @Test
    public void testInputTime6() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "13:25")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Twenty five past one"));
    }
    
    @Test
    public void testInputTime7() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "13:30")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Half past one"));
    }
    
    @Test
    public void testInputTime8() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "13:35")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Twenty five to two"));
    }
    
    @Test
    public void testInputTime9() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "13:55")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Five to two"));
    }
    
    @Test
    public void testInvalidInputTime1() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "xyz")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Invalid time input"));
    }
    
    @Test
    public void testInvalidInputTime2() throws Exception
    {
      mvc.perform(MockMvcRequestBuilders
          .get("/talking-clock/{numericTime}", "2356")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Invalid time input"));
    }
}
