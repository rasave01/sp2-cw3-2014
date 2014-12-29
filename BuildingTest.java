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


	@Test
	public void getNumOfFloorsTest() {
		Building bldg = new Building(13);
		int expected = 13;
		int actual = bldg.getNumOfFloors();
		assertEquals("Wrong answer: ", expected, actual);
	}

	
	@Test 
	public void setNumOfFloorsrrTest(){
		bldg = null;
		int expected = 13;
		int actual = bldg.setNumOfFloors(13);
		assertEquals("Wrong answer: ", expected, actual);
	}

}
