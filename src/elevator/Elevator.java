/**
 * @author Radu Asavei + Gary Murphy
 * @since 2-November-2014
 * @version 29-December-2014
 */
package elevator;

import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;


public class Elevator {

	public int currentFloor;
	public boolean direction;
	public ArrayList<Customer> registerList = new ArrayList<Customer>();
	public int topFloor;
	
	/*
	 *  elevator constructor
	 */
	public Elevator(int numOfFloors) {	
		this.topFloor = numOfFloors;
		System.out.println("Your building is equipped with an elevator.");
		
		if(numOfFloors>12){	
			System.out.println("This is an American building, so the 13th floor is skipped.");
			this.topFloor = numOfFloors+1;
		} 
		System.out.print("The following floors are available: ");
		for (int i=1; i<=numOfFloors; i++){
			if (i<13){
				System.out.print(i+" > ");
			}else{
				System.out.print(i+1+ " > ");
			}
		}
		System.out.println();	
		
		getCurrentFloor();
		
		/**
		 *  assigns direction based on random floor
		 *  can only be Up for 1 and Down for top floor
		 */
		if (currentFloor==1){
				setDirection(true);
		} else if (currentFloor==topFloor){ 
				setDirection(false);
				} else {
					Random randomDirection = new Random();
					boolean randomDir = randomDirection.nextBoolean();
					setDirection(randomDir);	
				}
	}
	
				
	/**
	 *  returns the current floor of the elevator
	 * @return <code>currentFloor</code>
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Sets a random floor within a range set by the maximum number as given by user input in <code>numOfFloors</code> 
	 * @return random floor number
	 * @param numOfFloors - user input
	 */
	public static int setRandomFloor(int numOfFloors) {
		Random randomFloor = new Random(); 
		if(numOfFloors<13){	
			int returnFloor = randomFloor.nextInt(numOfFloors);
			if (returnFloor==0){
				returnFloor = setRandomFloor(numOfFloors);
			}
			return returnFloor;
		} else {
			int returnFloor = randomFloor.nextInt(numOfFloors)+1;
			if(returnFloor==13){
				returnFloor = setRandomFloor(numOfFloors);
			}
			return returnFloor;
		}
	}
	
	/**
	 * direction getter
	 */
	public void getDirection() {
		if (direction==true){
			System.out.println("Elevator going	 ^.UP.^");
		} else {
			System.out.println("Elevator going	v.DOWN.v");
		}
	} //end of getDirection method

	/**
	 * direction setter
	 * @param direction
	 */
	public void setDirection(boolean direction) {
		this.direction = direction;
	} // end of setDirection method

	/**
	 * method for customer joining the elevator
	 * @param customer
	 */
	//@SuppressWarnings("unused")
	public void customerJoins(Customer customer){
		this.registerList.add(customer);
		System.out.println("Customer "+customer.getID()+" joins the elevator");
		printRegisterListIDs();
	}
	
	/**
	 * method for customer leaving the elevator
	 * @param customer
	 */
	//@SuppressWarnings("unused")
	public void customerLeaves(Customer customer){
		this.registerList.remove(customer);
		System.out.println("Customer "+customer.getID()+" leaves the elevator");
		printRegisterListIDs();
	} // end of customerLeaves method
	
	// method to move the elevator to the floor floorNo and display progress
	public int move(int floorNo) throws InterruptedException {
		
		int step = 0;
		// check if elevator needs to go up
		if(this.currentFloor<floorNo){
			
			if (floorNo!=1){// quick fix for moving up to floor 1 anomaly - should look into this if there's time...
				System.out.println("Elevator moving ^^UP^^ to floor " + floorNo);
			}
			while(step<floorNo-this.currentFloor){
				System.out.print("^");
				//Pause for 1/2 second				
				Thread.sleep(500);
				step++;
			}
			System.out.println();
		}
		
		// check if elevator needs to go down
		if(this.currentFloor>floorNo){
			System.out.println("Elevator moving vvDOWNvv to floor " + floorNo);
	
			while(step<this.currentFloor-floorNo){
				System.out.print("v");
				//Pause for 1/2 second
				Thread.sleep(500);
				step++;
			}
			System.out.println();
		}
		this.currentFloor = floorNo;
		System.out.println("Elevator arrives on floor "+ this.currentFloor);
		return step;
	} // end of move method
	
	
	/**
	 * Prints the list of customers currently in the elevator
	 * */
	public void printRegisterListIDs(){
		// initialise print statement
		String registerList = "The following customers are in the elevator: ";
		Iterator<Customer> registerIterator = this.registerList.iterator();
		while (registerIterator.hasNext()){
			// iterate through customers in the lift and add IDs to print statement
			registerList += (registerIterator.next().getID()+"; ");
		}
		// remove the last "; "
		registerList = registerList.substring(0, registerList.length()-2);
		// print out the print statement
		System.out.println(registerList);
	}
}
