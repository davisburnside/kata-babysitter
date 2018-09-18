
package kata.babysitter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KataBabySitterTest {
    
    public KataBabySitterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testMain() {

//	String[] args = new String[]{"1800","11pm"};
	
	// Check time standardization 
	assertTrue(KataBabySitter.standardizeTime("1800") == 1800);	
	assertTrue(KataBabySitter.standardizeTime("1100pm") == 2300);
	assertTrue(KataBabySitter.standardizeTime("7p") == 1900);
	assertTrue(KataBabySitter.standardizeTime("7am") == 700);
	
//	KataBabySitter.main(args);
	
    }
    
}
