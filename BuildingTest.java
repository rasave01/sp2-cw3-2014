package elevator;

/**
 * @author Radu Asavei + Gary Murphy
 *
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class BuildingTest {

	@Test
	public void getNumOfFloorsTest() {
		Building bldg = new Building(13);
		int expected = 13;
		int actual = bldg.getNumOfFloors();
		assertEquals("Wrong answer: ", expected, actual);
	}
	
	@Test 
	public void setNumOfFloorsTest(){
		Building bldg = new Building(10);
		int expected = 13;
		int actual = bldg.setNumOfFloors(13); // Warning - cannot convert from a void to an int
		assertEquals("Wrong answer: ", expected, actual);
	}
	
	/*@Test 
	public void setNumOfFloorsrrTest(){
		Building bldg = new Building(10);
		int expected = 13;
		int actual = bldg.setNumOfFloors(13);;
		assertEquals("Wrong answer: ", expected, actual);
	}*/

}
