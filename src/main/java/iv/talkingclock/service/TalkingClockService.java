package iv.talkingclock.service;

import org.springframework.stereotype.Service;

import iv.talkingclock.entity.Time;

@Service
public interface TalkingClockService {
	public Time sayTime(String numericTime);
}
