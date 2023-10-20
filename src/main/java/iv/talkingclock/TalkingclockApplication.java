package iv.talkingclock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"iv.talkingclock",
		"iv.talkingclock.rest.controller",
		"iv.talkingclock.entity",
		"iv.talkingclock.service",
		"iv.talkingclock.service.impl",
		"iv.talkingclock.ut"})
public class TalkingclockApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkingclockApplication.class, args);
	}

}
