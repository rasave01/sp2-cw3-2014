package elevator;

import java.util.Random;
import java.util.ArrayList;

// elevator constructor
public class Elevator {

	private static int currentFloor;
	private static boolean direction;
	public ArrayList<Customer> registerList;
	private int topFloor;
	

	public Elevator(int numOfFloors) {	
		int	topFloor = numOfFloors;
		
		System.out.println("Your building was equiped with an elevator.");
		
		if(numOfFloors>12){	
			System.out.println("This is an American building, so the 13th floor is skipped.");
			topFloor = numOfFloors+1;
		} 
		System.out.print("The follwoing floors are avaiable: ");
		for (int i=1; i<=numOfFloors; i++){
			if (i<13){
				System.out.print(i+" > ");
			}else{
				System.out.print(i+1+ " > ");
			}
		}
		System.out.println();	
		
		System.out.print("The elevator will be assigned a random floor.");
		
		Elevator.currentFloor = setRandomFloor(numOfFloors);
		
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
		this.registerList = new ArrayList<Customer>();
	}
	
				
	// prints the current floor of the elevator
	int getCurrentFloor() {
		return currentFloor;
		//System.out.println("The current floor is "+ currentFloor + ".");
	}

	// create random floor
	public static int setRandomFloor(int numOfFloors) {
		Random randomFloor = new Random(); 
		if(numOfFloors<13){	
			int returnFloor = randomFloor.nextInt(numOfFloors);
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
	public static void getDirection() {
		if (direction==true){
			System.out.println("Elevator going	 ^.UP.^");
		} else {
			System.out.println("Elevator going	v.DOWN.v");
		}
	}

	//direction setter
	public static void setDirection(boolean direction) {
		Elevator.direction = direction;
	}

	//method for customer joining the elevator
	@SuppressWarnings("unused")
	private void customerJoins(Customer customer){
		registerList.add(customer);
	}
	
	//method for customer leaving the elevator
	@SuppressWarnings("unused")
	private void customerLeaves(Customer customer){
		registerList.remove(customer);
	}
	
	public boolean move() {
		if(Elevator.direction==true){
			if (this.getCurrentFloor()==this.topFloor){
				this.direction=false;
				return this.topFloor;
			} else {
				this.currentFloor++;
				return ++currentFloor;
				//return move(currentFloor++, true);
			}
		} else if (currentFloor==1){
				return 1;
			} else {
				return --currentFloor;
			//return move(currentFloor--, true);
		}
	}
	
}
