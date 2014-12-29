/**
 * @author Radu Asavei + Gary Murphy
 * @since 2-November-2014
 * @version 29-December-2014
 */

package elevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuildingTest {

	private Building bldg;

	/**
	 * @Test <code>getNumOfFloors</code> method from the Elevator class
	 */
	@Test
	public void getNumOfFloorsTest() {
		Building bldg = new Building(13);
		int expected = 13;
		int actual = bldg.getNumOfFloors();
		assertEquals("Wrong answer: ", expected, actual);
	}

	/**
	 * @Test <code>setNumOfFloors</code> method from the Elevator class
	 * @param <code>bldg</code> is created and initialised to null
	 * allowing the test to set the number of floors
	 */
	@Test 
	public void setNumOfFloorsTest(){
		bldg = null;
		int expected = 13;
		int actual = bldg.setNumOfFloors(13);
		assertEquals("Wrong answer: ", expected, actual);
	}

}
