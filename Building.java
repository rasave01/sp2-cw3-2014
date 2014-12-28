package elevator;
import java.util.ArrayList;
public class Building {
	
	private static int numOfFloors;
	
	@SuppressWarnings("unused") Elevator elevator1;

	public ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	// building constructor
	public Building(int numOfFloors) {
		this.setNumOfFloors(numOfFloors);
		System.out.println("Your building has "+ numOfFloors+" floors." );
		
	//	create new elevator for this building
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
	 */
	public void setNumOfFloors(int numOfFloors) {
		Building.numOfFloors = numOfFloors;
	}

	/*
	 * @return the customerList
	 
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	/**
	 * @param customerList the customerList to set
	 
	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
	
	/**
	 * @return the elev
	 
	public Elevator getElev() {
		return elev;
	}

	/**
	 * @param elev the elev to set
	 
	public void setElev(Elevator elev) {
		this.elev = elev;
	}*/
}
