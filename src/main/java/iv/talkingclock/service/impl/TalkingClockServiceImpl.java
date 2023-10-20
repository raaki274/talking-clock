package iv.talkingclock.service.impl;

import org.springframework.stereotype.Service;

import iv.talkingclock.entity.Time;
import iv.talkingclock.service.TalkingClockService;

@Service
public class TalkingClockServiceImpl implements TalkingClockService {

	@Override
	public Time sayTime(String numericTime) {
		
		String[] timeTokens = numericTime.split(":");
		
		int hours = Integer.parseInt(timeTokens[0])%12;
		int mins = Integer.parseInt(timeTokens[1]);
		
		int minTxtId = 2;
		int preTxtId = 2;
		
		if(mins == 15 || mins == 45)
			minTxtId = 0;
		
		if(mins == 30)
			minTxtId = 1;
		
		if(mins > 0 && mins <= 30) {
			preTxtId = 0;
		}
		
		if(mins > 30) {
			preTxtId = 1;
			mins = 60-mins;
			hours += 1;
		}
		
		if(mins == 0) {
			minTxtId = 2;
			preTxtId = 2;
		}
		
		String timeText = "";
		if(mins == 0) {
			timeText = numberToWord(hours == 0?hours+12:hours)+" "+Constants.prepositions[preTxtId];
		} else {
			timeText = (mins == 15 || mins == 30)
						? Constants.minutetext[minTxtId]
							+" "+Constants.prepositions[preTxtId]
							+" "+numberToWord(hours == 0?hours+12:hours)
						:numberToWord(mins)
							+" "+Constants.prepositions[preTxtId]
							+" "+numberToWord(hours == 0?hours+12:hours);
		}
		
		Time t = new Time();
		t.setValue(toSentenceCase(timeText));
		
		return t;
	}
	
	private String numberToWord(int n) {
		if(n < 0 || n > 99)
			return "Invalid input";
		
		if (n < 20)
			return Constants.units[n];
		
		return Constants.tens[n / 10] + ((n % 10 != 0) ? " " : "") + Constants.units[n % 10];
	}
	
	
	private String toSentenceCase(String text) {
	    if (text == null || text.isEmpty()) {
	        return text;
	    }
	    return text.substring(0, 1).toUpperCase()+text.substring(1).toLowerCase();
	}

}
