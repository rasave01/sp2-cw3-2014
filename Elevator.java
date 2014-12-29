/**
 * @author Radu Asavei + Gary Murphy
 * @since 2-November-2014
 * @version 29-December-2014
 */
package elevator;

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
		
		//System.out.print("The elevator will be assigned a random floor.");
		
		//this.currentFloor = setRandomFloor(numOfFloors);
		
		getCurrentFloor();
		
		// assign direction based on random floor
		// can only be Up for 1 and Down for top floor
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
	
				
	// returns the current floor of the elevator
	public int getCurrentFloor() {
		return currentFloor;
		//System.out.println("The current floor is "+ currentFloor + ".");
	}

	/* 
	 * @return random floor number limited/bound by
	 * the number of floors in the building as chosen by the user
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
	
	//direction getter
	public void getDirection() {
		if (direction==true){
			System.out.println("Elevator going	 ^.UP.^");
		} else {
			System.out.println("Elevator going	v.DOWN.v");
		}
	} //end of getDirection method

	//direction setter
	public void setDirection(boolean direction) {
		this.direction = direction;
	} // end of setDirection method

	//method for customer joining the elevator
	//@SuppressWarnings("unused")
	public void customerJoins(Customer customer){
		this.registerList.add(customer);
		System.out.println("Customer "+customer.getID()+" joins the elevator");
	}
	
	//method for customer leaving the elevator
	//@SuppressWarnings("unused")
	public void customerLeaves(Customer customer){
		this.registerList.remove(customer);
		System.out.println("Customer "+customer.getID()+" leaves the elevator");
	} // end of customerLeaves method
	
	// method to move the elevator to the floor floorNo and display progress
	public int move(int floorNo) throws InterruptedException {
		
		int step = 0;
		
		// check if elevator needs to go up
		if(this.currentFloor<floorNo){
			System.out.println("Elevator moving ^^UP^^ to floor" + floorNo);
			//step=0;
			while(step<floorNo-this.currentFloor){
				System.out.print("^");
				//Pause for 1/4 second				
				Thread.sleep(500);
				step++;
			}
			System.out.println();
		}
		
		// check if elevator needs to go down
		if(this.currentFloor>floorNo){
			System.out.println("Elevator moving vvDOWNvv to floor " + floorNo);
			//step=0;
			while(step<this.currentFloor-floorNo){
				System.out.print("v");
				//Pause for 1/4 second
				Thread.sleep(250);
				step++;
			}
			System.out.println();
		}
		this.currentFloor = floorNo;
		System.out.println("Elevator arrives on floor "+ this.currentFloor);
		return step;
	} // end of move method
}
