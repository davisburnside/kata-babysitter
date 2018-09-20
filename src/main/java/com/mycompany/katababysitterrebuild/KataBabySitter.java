package com.mycompany.katababysitterrebuild;

// RULES;
//starts no earlier than 5:00PM
//leaves no later than 4:00AM
//gets paid $12/hour from start-time to bedtime
//gets paid $8/hour from bedtime to midnight
//gets paid $16/hour from midnight to end of job
//gets paid for full hours (no fractional hours)
// USAGE:
// Call this program from the command line with three args: start time, bedtime, & end time
// Accoring to the logic above, I disallowed a bedtime after midnight
public class KataBabySitter {

    // ':' is a reserved character in terminal
    // Can handle these time formats:
    // military (1400, 0025)
    // standard, no spaces (500PM, 0200a)
    // standard, hour only, no spaces (5PM, 11a)
    public static void main(String[] args) {
		
	if (args.length >= 3) {
	    
	    // Check time validities
	    int startTime = KataBabySitter.standardizeTimeToModifiedMilitary(args[0]);
	    int bedTime = KataBabySitter.standardizeTimeToModifiedMilitary(args[1]);
	    int endTime = KataBabySitter.standardizeTimeToModifiedMilitary(args[2]);
	    if (startTime == -1 || bedTime == -1 || endTime == -1) {
		System.out.println("Enter valid time format (example: 1700, 5p, 500pm");
		System.exit(1);
	    }

	    if (!KataBabySitter.checkValidBabySittingTimes(startTime, bedTime, endTime)) {
		System.exit(1);
	    }
	    
	    int amountEarned = KataBabySitter.calculateRate(startTime, bedTime, endTime);
	    System.out.println("You earned $" + amountEarned);

	} else {
	    System.out.println("Enter a start time, bedtime, and end time");
	}
    }

    public static int toInt(String string) {

	try {
	    Integer militaryTimeFormat = Integer.valueOf(string);
	    return militaryTimeFormat;

	} catch (NumberFormatException e) {
	    return -1;
	}
    }

    public static int standardizeTimeToModifiedMilitary(String timeString) {

	if (timeString == null || timeString.length() == 0) {
	    return -1;
	}

	// Seperate time values from period 
	String hourAndMinuteString = timeString.replaceAll("[a-zA-Z]+", "");
	String periodString = timeString.replaceAll("\\d+", "");

	// Handle hour-only time strings
	if (hourAndMinuteString.length() <= 2) {
	    hourAndMinuteString += "00";
	}

	int hourAndMinuteInt = KataBabySitter.toInt(hourAndMinuteString);
	if (hourAndMinuteInt != -1) {

	    // If there is no period string, the time can be assumed to already be in military format
	    if (periodString != null && !periodString.equals("")) {

		if ((periodString.equalsIgnoreCase("a") || periodString.equalsIgnoreCase("am"))
			|| (periodString.equalsIgnoreCase("p") || periodString.equalsIgnoreCase("pm"))) {

		    if (periodString.startsWith("p")) {
			hourAndMinuteInt += 1200;
		    }
		}
		else{
		    System.out.println("Enter a valid AM/PM designation");
		    return -1;
		}
	    }

	    // shift AM times forward by 24 hours
	    if (hourAndMinuteInt >= 0 && hourAndMinuteInt <= 400) {
		hourAndMinuteInt += 2400;
	    }

	    return hourAndMinuteInt;
	}

	return -1;
    }

    public static boolean checkValidBabySittingTimes(int startTime, int bedtime, int endTime) {

	if (startTime < 1700 || startTime >= 2800) {
	    System.out.println("Enter a valid start time (between 5PM and 3:59 AM)");
	    return false;
	}

	if (endTime <= 1700 || endTime > 2800) {
	    System.out.println("Enter a valid end time (between 5:01PM and 4 AM)");
	    return false;
	}

	if (endTime <= startTime) {
	    System.out.println("Start time must be before end time");
	    return false;
	}
	
	if (bedtime > 2400) {
	    System.out.println("bed time must be before midnight");
	    return false;
	}

	return true;

    }

    public static int calculateRate(int startTime, int bedTime, int endTime) {

	double baseRate = 8;
	int totalPay = 0;
	int timeTicker = startTime;

	// DEBUG:
//	System.out.println("Start: " + startTime + "  Bed: " + bedTime + " End: " + endTime);

	while ((timeTicker += 100) <= endTime) {
	    
	    // If time is BEFORE bedtime and BEFORE midnight
	    if (timeTicker <= bedTime && timeTicker <= 2400){
		totalPay += baseRate * 1.5;
	    }
	    
	    // If time is AFTER bedtime and BEFORE midnight
	    else if (timeTicker > bedTime && timeTicker <= 2400){
		totalPay += baseRate;
	    }
	    
	    // If time is AFTER bedtime and AFTER midnight
	    else if (timeTicker > bedTime && timeTicker > 2400){
		totalPay += baseRate * 2;
	    }
	    
	    // DEBUG:
//	    System.out.println(timeTicker-100 + " -> " + timeTicker +  ": " + totalPay);
	    	    
	}
	
	// DEBUG:
//	System.out.println("\n\n\n");
	
	
	return totalPay;
    }
}
