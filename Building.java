/**
 * @author Radu Asavei + Gary Murphy
 * @since 2-November-2014
 * @version 29-December-2014
 */

package elevator;
import java.util.ArrayList;
public class Building {
	
	private static int numOfFloors;
	
	Elevator elevator1;

	public ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	// building constructor
	public Building(int numOfFloors) {
		this.setNumOfFloors(numOfFloors);
		System.out.println("Your building has " + numOfFloors + " floors." );
		System.out.println(" ");
		
	/*	create new elevator for this building
	 * 	use the integer given by the user for the number of floors for the building
	 */
		this.elevator1 = new Elevator(numOfFloors);
		
	// customer list for this building

	}

	/**
	 * @return the numOfFloors
	 */
	public int getNumOfFloors() {
		return numOfFloors;
	}

	/**
	 * @param numOfFloors the numOfFloors to set
	 * @return 
	 */
	public int setNumOfFloors(int numOfFloors) {
		return Building.numOfFloors = numOfFloors;
	}
}
