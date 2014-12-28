package elevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuildingTest {

	@Test
	public void testsetNumofFloors(int numOfFloors) {
		Building bldg = new Building(13);
		int expected = 13;
		int actual = bldg.getNumOfFloors();
		assertEquals("Wrong answer: ", expected, actual);
	}

}
