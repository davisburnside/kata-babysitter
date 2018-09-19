package kata.babysitter;

import org.junit.Test;
import static org.junit.Assert.*;

public class KataBabySitterTest {

    public KataBabySitterTest() {
    }

    @Test
    public void testTimeStandardization() {
	
	assertTrue(KataBabySitter.standardizeTimeToModifiedMilitary("4f") == -1);
	assertTrue(KataBabySitter.standardizeTimeToModifiedMilitary("1800") == 1800);
	assertTrue(KataBabySitter.standardizeTimeToModifiedMilitary("1100pm") == 2300);
	assertTrue(KataBabySitter.standardizeTimeToModifiedMilitary("1100a") == 1100);
	assertTrue(KataBabySitter.standardizeTimeToModifiedMilitary("3am") == 2700);
	assertTrue(KataBabySitter.standardizeTimeToModifiedMilitary("7p") == 1900);
	assertTrue(KataBabySitter.standardizeTimeToModifiedMilitary("3pm") == 1500);
	assertTrue(KataBabySitter.standardizeTimeToModifiedMilitary("0000") == 2400);
    }
    
    @Test
    public void testTimeValidityChecks() {

	assertTrue(KataBabySitter.checkValidBabySittingTimes(1700, 1700, 2300));
	assertFalse(KataBabySitter.checkValidBabySittingTimes(1400, 1700, 2300));
	assertFalse(KataBabySitter.checkValidBabySittingTimes(1400, 1700, 401));
    }
    
     @Test
    public void testRateCalculation() {

	//gets paid $12/hour from start-time to bedtime
	//gets paid $8/hour from bedtime to midnight
	//gets paid $16/hour from midnight to end of job
	assertTrue(KataBabySitter.calculateRate(1700, 1800, 2000) == 28); // fails

    }

}
