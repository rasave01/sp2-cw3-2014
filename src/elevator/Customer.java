/**
 * @author Radu Asavei + Gary Murphy
 * @since 2-November-2014
 * @version 29-December-2014
 */

package elevator;

public class Customer {
	
	//public static final int numOfFloors = null;

	private int startFloor;
	private int destinationFloor;
	private boolean inElevator;
	private int ID;
	
	/**
	 * Constructs a <code>Customer</code> with
	 * <li>an ID number</li>
	 * <li>a start floor (random)</li>
	 * <li>a destination floor (random)</li>
	 * start and destination floor is bound by @param bld
	 */
	public Customer(int newID, Building bld){
		ID = newID;
		startFloor = Elevator.setRandomFloor(bld.getNumOfFloors());
		destinationFloor = Elevator.setRandomFloor(bld.getNumOfFloors());
		
		// makes sure destination is not the same with start
		while (destinationFloor==startFloor){
			destinationFloor = Elevator.setRandomFloor(bld.getNumOfFloors());
		}
		inElevator = false;
	} //end of Customer constructor
	
	public int getStartFloor(){
		return this.startFloor;
	}
	
	public int getDestinationFloor(){
		return destinationFloor;
	}
	
	public boolean getInElevator(){
		return inElevator;
	}
	
	public int getID(){
		return ID;
	}
}
