package elevator;

import java.util.Random;
import java.util.ArrayList;


public class Elevator {

	public int currentFloor;
	public boolean direction;
	public ArrayList<Customer> registerList = new ArrayList<Customer>();
	public int topFloor;
	
	// elevator constructor
	public Elevator(int numOfFloors) {	
		this.topFloor = numOfFloors;
		
		System.out.println("Your building was equipped with an elevator.");
		
		if(numOfFloors>12){	
			System.out.println("This is an American building, so the 13th floor is skipped.");
			this.topFloor = numOfFloors+1;
		} 
		System.out.print("The following floors are avaiable: ");
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
		/*if (currentFloor==1){
				setDirection(true);
		} else if (currentFloor==topFloor){ 
				setDirection(false);
				} else {
					Random randomDirection = new Random();
					boolean randomDir = randomDirection.nextBoolean();
					//setDirection(randomDir);	
				}*/
	}
	
				
	// prints the current floor of the elevator
	public int getCurrentFloor() {
		return currentFloor;
		//System.out.println("The current floor is "+ currentFloor + ".");
	}

	// create random floor
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
	}

	//direction setter
	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	//method for customer joining the elevator
	@SuppressWarnings("unused")
	public void customerJoins(Customer customer){
		this.registerList.add(customer);
		System.out.println("Customer "+customer.getID()+" joins the elevator");
	}
	
	//method for customer leaving the elevator
	@SuppressWarnings("unused")
	public void customerLeaves(Customer customer){
		
		
		
		this.registerList.remove(customer);
		//this.customerList.remove(customer);
		System.out.println("Customer "+customer.getID()+" leaves the elevator");
	}
	// method to move the elevator to the floor floorNo
	public void move(int floorNo) {
		this.currentFloor = floorNo;
		System.out.println("Elevator moves to floor "+ this.currentFloor);
	}

}
