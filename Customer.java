package elevator;

public class Customer {
	
	//public static final int numOfFloors = null;

	private int startFloor;
	private int destinationFloor;
	private boolean inElevator;
	private int ID;
	// customer constructor
	public Customer(int newID, Building bld){
		ID = newID;
		startFloor = Elevator.setRandomFloor(bld.getNumOfFloors());
		destinationFloor = Elevator.setRandomFloor(bld.getNumOfFloors());
		inElevator = false;
		
		//System.out.println("Random customer "+ID+"starts on floor "+startFloor+" and goes to floor "+destinationFloor);
	}
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
