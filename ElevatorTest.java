/**
 * @author Radu Asavei + Gary Murphy
 *
 */
package elevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class ElevatorTest {
	
	private int currentFloor;
	private int destinationFloor;
	Elevator e = new Elevator(20);

	/*
	 * @Test in progress - move method incomplete
	 * 	
	@Test
	public void moveTest(){
		boolean expected = true;
		boolean actual = e.move();
		assertEquals("Wrong answer: ", expected, actual);
	}*/
	
	@Test
	public void getDirectionTest(){
		boolean direction = true;
		String expected = "Elevator going	 ^.UP.^";
		String actual = e.getDirection(); //Warning - cannot convert from a void to a string.
		assertEquals("Wrong answer: ", expected, actual);
		
	}

}
