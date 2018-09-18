
package kata.babysitter;

// RULES;
//starts no earlier than 5:00PM
//leaves no later than 4:00AM
//gets paid $12/hour from start-time to bedtime
//gets paid $8/hour from bedtime to midnight
//gets paid $16/hour from midnight to end of job
//gets paid for full hours (no fractional hours)
//Feature
//As a babysitter
//In order to get paid for 1 night of work
//I want to calculate my nightly charge
// USAGE:
// Call this program from the command line with two args: start & end times
public class KataBabySitter {

    // ':' is a reserved character in terminal
    // Can handle these time formats:
    // military (1400, 0025)
    // standard, no spaces (500PM, 0200a)
    // standard, hour only, no spaces (5PM, 11a)
    public static void main(String[] args) {

    }

    public static Integer toInt(String string) {

	try {
	    Integer militaryTimeFormat = Integer.valueOf(string);
	    return militaryTimeFormat;

	} catch (NumberFormatException e) {
	    return null;
	}
    }

    public static Integer standardizeTime(String timeString) {

	if (timeString == null || timeString.length() == 0) {
	    return null;
	}

	// handle military time (no letters)
	Integer directIntTranslation = KataBabySitter.toInt(timeString);
	if (directIntTranslation != null && directIntTranslation >= 0 && directIntTranslation <= 2400) {
	    return KataBabySitter.toInt(timeString);
	}

	// Handle standard time (AM / PM designation)
	String hourAndMinuteString = timeString.replaceAll("[a-zA-Z]+", "");
	String periodString = timeString.replaceAll("\\d+", "");

	// Handele hour-only
	if (hourAndMinuteString.length() <= 2) {
	    hourAndMinuteString += "00";
	}

	Integer hourAndMinuteInt = KataBabySitter.toInt(hourAndMinuteString);
	if (hourAndMinuteInt != null
		&& ((periodString.equalsIgnoreCase("a") || periodString.equalsIgnoreCase("am")) || (periodString.equalsIgnoreCase("p") || periodString.equalsIgnoreCase("pm")))) {

	    if (periodString.startsWith("p")) {
		hourAndMinuteInt += 1200;
	    }

	    return hourAndMinuteInt;
	}

	System.out.println("Invalid Parameter: " + timeString);
	return null;
    }

    public static boolean checkValidBabySittingTime(int time) {

	if ((time >= 1700 && time <= 2400) || (time >= 0 && time <= 400)) {
	    return true;
	}
	return false;
    }

}
