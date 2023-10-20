package ivprep;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TalkingClock {
	
	public static final String[] units = { 
			"", 
			"One", 
			"Two", 
			"Three", 
			"Four",
			"Five", 
			"Six", 
			"Seven", 
			"Eight", 
			"Nine", 
			"Ten", 
			"Eleven", 
			"Twelve",
			"Thirteen", 
			"Fourteen", 
			"Fifteen", 
			"Sixteen", 
			"Seventeen",
			"Eighteen", 
			"Nineteen"
	};

	public static final String[] tens = { 
			"",
			"",
			"Twenty",
			"Thirty",
			"Forty",
			"Fifty",
			"Sixty",
			"Seventy",
			"Eighty",
			"Ninety"
	};
	
	public static final String[] prepositions = {
			"past",
			"to",
			"o'clock"
	};
	
	public static final String[] minutetext = {
			"Quarter",
			"Half",
			""
	};
	
	public String sayTime(String numericTime) {
		if(!isValidTime(numericTime))
			return "Invalid time input";
		
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
			timeText = numberToWord(hours == 0?hours+12:hours)+" "+prepositions[preTxtId];
		} else {
			timeText = (mins == 15 || mins == 30)
						? minutetext[minTxtId]
							+" "+prepositions[preTxtId]
							+" "+numberToWord(hours == 0?hours+12:hours)
						:numberToWord(mins)
							+" "+prepositions[preTxtId]
							+" "+numberToWord(hours == 0?hours+12:hours);
		}
		
		return toSentenceCase(timeText);
	}
	
	private String numberToWord(int n) {
		if(n < 0 || n > 99)
			return "Invalid input";
		
		if (n < 20)
			return units[n];
		
		return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
	}
	
	private static String getCurrentTime() {
		return DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
	}
	
	private boolean isValidTime(String time) {
        if (time == null) return false;
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(time);
        return m.matches();
	}
	
	public static String toSentenceCase(String text) {
	    if (text == null || text.isEmpty()) {
	        return text;
	    }
	    return text.substring(0, 1).toUpperCase()+text.substring(1).toLowerCase();
	}
	
	public static void main(String[] args) {
		String inputTime = (args.length > 0)?args[0]:getCurrentTime();
		System.out.println(inputTime+"\t"+new TalkingClock().sayTime(inputTime));
	}
}
