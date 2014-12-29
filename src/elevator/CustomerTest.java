/**
 * @author Radu Asavei + Gary Murphy
 * @since 2-November-2014
 * @version 29-December-2014
 */

package elevator;
import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

	/**
	 * @Test <code>getNumOfFloors</code> method from the Customer class
	 * Creates a Building instance 
	 * Creates a Customer instance
	 * Although the test cannot test for random numbers
	 * the <code>getStartFloor</code> method will generate a number to view in the error
	 */
	@Test
	public void getStartFloorTest() {
		Building bldg = new Building(20);
		Customer a = new Customer(3,bldg);
		int expected = 5;
		// Start floor is random. Check errors to confirm no repetition of start floors to test.
		int actual = a.getStartFloor();
		assertEquals("Wrong answer: ", expected, actual);
	}
	
	
	@Test
	public void getDestinationFloorTest() {
		Building bldg = new Building(20);
		Customer a = new Customer(3,bldg);
		int expected = 3;
		// Destination floor is random. Check errors to confirm no repetition of destination floors to test.
		int actual = a.getDestinationFloor();
		assertEquals("Wrong answer: ", expected, actual);
	}
	
	
	@Test
	public void getInElevatorTest() {
		Building bldg = new Building(20);
		Customer a = new Customer(3,bldg);
		boolean expected = false;
		boolean actual = a.getInElevator();
		assertEquals("Wrong answer: ", expected, actual);
	}

}
