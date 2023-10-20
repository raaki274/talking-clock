package iv.talkingclock.rest.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import iv.talkingclock.entity.Time;
import iv.talkingclock.service.TalkingClockService;


@RestController
public class TalkingClockController {
	
	@Autowired
	private TalkingClockService service;
	
	private static final Logger logger = LoggerFactory.getLogger(TalkingClockController.class);
	
    @GetMapping("/talking-clock")
    @ResponseStatus(HttpStatus.OK)
    public Time get() {
    	logger.info("Received request for current time: "+getCurrentTime());
    	return service.sayTime(getCurrentTime());
    }
    
    @GetMapping("/talking-clock/{numericTime}")
    @ResponseStatus(HttpStatus.OK)
    public Time get(@PathVariable String numericTime) {
    	logger.info("Received request with input time: "+numericTime);
    	if(!isValidTime(numericTime)) {
    		Time t = new Time();
    		t.setValue("Invalid time input");
    		return t;
    	}
    	return service.sayTime(numericTime);
    }
    
    private String getCurrentTime() {
		return DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
	}
    
	private boolean isValidTime(String time) {
        if (time == null) return false;
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(time);
        return m.matches();
	}
}



