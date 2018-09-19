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

	assertTrue(KataBabySitter.checkValidBabySittingTimes(1700, 1701, 2300));
	assertTrue(KataBabySitter.checkValidBabySittingTimes(1800, 1730, 2700)); // Bed time before start time (allowed)
	assertFalse(KataBabySitter.checkValidBabySittingTimes(1400, 1800, 2300)); // start time before 5pm
	assertFalse(KataBabySitter.checkValidBabySittingTimes(1800, 2500, 2700)); // bed time after midnight
	assertFalse(KataBabySitter.checkValidBabySittingTimes(1700, 1800, 401)); // end time after 4am
    }

    @Test
    public void testRateCalculation() {

	// $12/hour from 17-bed
	// $8/hour from bed-24
	// $16/hour 24-end
	assertTrue(KataBabySitter.calculateRate(1700, 1700, 1800) == 8); // Bed time at start time
	assertTrue(KataBabySitter.calculateRate(2300, 2400, 2500) == 28); // Bed time at midnight
	assertTrue(KataBabySitter.calculateRate(2300, 2400, 2400) == 12); // Bed time and end time at midnight
	assertTrue(KataBabySitter.calculateRate(1700, 1800, 2000) == 28);
	assertTrue(KataBabySitter.calculateRate(1730, 1830, 2000) == 20);
	assertTrue(KataBabySitter.calculateRate(2000, 2330, 2600) == 76);
	assertTrue(KataBabySitter.calculateRate(1730, 1800, 2000) == 16);
	assertTrue(KataBabySitter.calculateRate(1700, 1600, 2400) == 56); // Bed time before start time
    }

}
