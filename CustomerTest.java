package elevator;

/**
 * @author Radu Asavei + Gary Murphy
 *
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

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
