package elevator;

import java.util.InputMismatchException;
import java.util.Iterator;
/**
 * @author Gary Murphy + Radu Asavei
 *
 */
import java.util.Scanner;


public class Simulator {

	private static int ourStrategyMoves;
	private static int defaultStrategyMoves;

	public static void main(String[] args) throws InterruptedException {

		Scanner input = new Scanner(System.in);
		
		int numOfFloorsValid = 0;
		boolean validInput = false;
		System.out.println("Enter the number of floors for the building: ");
		while(!validInput){
			try {
				//System.out.println("Enter the number of floors for the building: ");
				numOfFloorsValid = input.nextInt();
				//System.out.println("numOfFloorsValid = " + numOfFloorsValid);
				validInput = true;
				break;
			}catch (InputMismatchException invalideInputError) { //if an exception appears, prints message below
			    System.err.println("Please enter an integer greater than 1! ");
			    input.next(); // clear scanner of incorrect input
			    continue; // continues to loop if error is found
			}
		};
		int numOfFloors = numOfFloorsValid;
		Building building1  = new Building(numOfFloors);
		System.out.println("Your building has: "+ numOfFloors + " floors." );
		
		validInput = false;
		int numOfCustomersValid = 0;
		System.out.println("Enter the number of customers for the elevator: ");
		while(!validInput){
			try {
				numOfCustomersValid = input.nextInt();
				break;
			}catch (InputMismatchException invalideInputError) { //if an exception appears, prints message below
			    System.err.println("Please enter an integer greater than 1! ");
			    input.next(); // clear scanner of incorrect input
			    continue; // continues to loop if error is found
			}
		}
		
		input.close(); // Close scanner to save memory during runtime.
		
		int numOfCustomers = numOfCustomersValid;
		
		//Start simulation
		System.out.print("The simulation will run for "+ numOfCustomers+ " customers, ");
		initiateSimulation(building1);
		createCustomers(building1,numOfCustomers);
		
		long defaultTime = runDefaultStrategy(building1);
		long ourTime = runOurStrategy(building1);
		
		//check runs and show result
		System.out.println ("**************** SIMULATION RESULTS COMPARISON ************************");
		System.out.println ("      The default strategy made "+defaultStrategyMoves+" elevator stops.");
		System.out.println ("              Our strategy made "+ourStrategyMoves+" elevator stops.");
		System.out.println ("   The run-time for the default strategy was "+defaultTime+" milliseconds.");
		System.out.println ("           The run-time for our strategy was "+ourTime+" milliseconds.");
		if (defaultStrategyMoves == ourStrategyMoves){
			System.out.println ("    By elevator stops both strategies have the same efficiency   ");
		} else if (defaultStrategyMoves<ourStrategyMoves){
				int stops=ourStrategyMoves-defaultStrategyMoves;
					System.out.println (" By elevtaor stops the default strategy is more efficient by " +stops+ " stops.");
				} else {
					int stops=defaultStrategyMoves-ourStrategyMoves;
						System.out.println (" By elevator stops our strategy is more efficient by "+stops+" stops.");
				}
		if (defaultTime == ourTime){
			System.out.println ("    By travel time both strategies have the same efficiency   ");
		} else if (defaultTime<ourTime){
				long eff=ourTime-defaultTime;
					System.out.println (" By travel time the default strategy is more efficient by " + eff + " milliseconds.");
				} else {
					long eff=defaultTime-ourTime;
						System.out.println (" By travel time our strategy is more efficient by "+eff+" milliseconds.");
				}
		
		
		
		System.out.println ("************************ END OF SIMULATION ****************************");
	}
	// method to run our strategy in Building Bld 
	//Will return the number of stops the elevator made to pick-up and drop all customers
	private static long runOurStrategy(Building bld) throws InterruptedException {
		StopWatch ourStrategyTime = new StopWatch();
		// initialise result
		int result = 0;
		ourStrategyTime.start();
		System.out.println("Our strategy simulation has started:");
		
		// move the elevator to the 1st floor
		bld.elevator1.move(1);
		
		// lift going up and picking up all customers
		int checkFloor = bld.elevator1.currentFloor;
		while(checkFloor<=bld.elevator1.topFloor){
			
			Iterator<Customer> customerIterator = bld.customerList.iterator();
			while(customerIterator.hasNext()){
				Customer currentCustomer = customerIterator.next();
				if(currentCustomer.getStartFloor()==checkFloor){
					if(bld.elevator1.currentFloor!=checkFloor){
						bld.elevator1.move(checkFloor);
						result++;
					}
					bld.elevator1.customerJoins(currentCustomer);
				}

			}
			// check that customers already in the lift do not need to get off on this floor
			// if so take them off the lift and off the registerList
			for(int i=0; i<bld.elevator1.registerList.size(); i++){
				Customer currentLiftCustomer = bld.elevator1.registerList.get(i);
				if(currentLiftCustomer.getDestinationFloor()==checkFloor){
					if(bld.elevator1.currentFloor!=checkFloor){
						bld.elevator1.move(checkFloor);
						result++;
					}
					bld.elevator1.customerLeaves(currentLiftCustomer);
				}
			}
			
			checkFloor++;
		}
		while(checkFloor>=1){
			
			//iterate "manually" as using an iterator while removing element throws error  
			for(int i=0; i<bld.elevator1.registerList.size(); i++){
				Customer currentLiftCustomer = bld.elevator1.registerList.get(i);
				
				if(currentLiftCustomer.getDestinationFloor()==checkFloor){
					if(bld.elevator1.currentFloor!=checkFloor){
						bld.elevator1.move(checkFloor);
						result++;
					}
					bld.elevator1.customerLeaves(currentLiftCustomer);
				}
			}
			checkFloor--;
		}
		ourStrategyMoves = result;
		ourStrategyTime.stop();
		long ourTime = ourStrategyTime.getElapsedTime();
		System.out.println("*************************************************");
		System.out.println("*      Our strategy simulation is complete      *");
		System.out.println("*************************************************");
		System.out.println(" ");
		return ourTime;
		
	}

	
	// method to run the default strategy in Building Bld 
	//Will return the number of stops the elevator made to pick-up and drop all customers
	private static long runDefaultStrategy(Building bld) throws InterruptedException {
		StopWatch defaultStrategyTime = new StopWatch();
		// initialise results
		int result = 0;
		defaultStrategyTime.start();
		System.out.println("Default strategy simulation has started:");
		
		// move the elevator to the 1st floor
		bld.elevator1.move(1);
		
		// lift going up and picking up all customers
		int checkFloor = bld.elevator1.currentFloor;
		while(checkFloor<=bld.elevator1.topFloor){
			//System.out.println("Checking floor "+checkFloor);
			Iterator<Customer> customerIterator = bld.customerList.iterator();
			while(customerIterator.hasNext()){
				Customer currentCustomer = customerIterator.next();
				//System.out.println("Checking customer "+currentCustomer.getID());
				
				if(currentCustomer.getStartFloor()==checkFloor){
					if(bld.elevator1.currentFloor!=checkFloor){
						bld.elevator1.move(checkFloor);
						result++;
					}
					bld.elevator1.customerJoins(currentCustomer);
				}
			}
			checkFloor++;
		}
		
		//move elevator to top floor as required by default strategy
		bld.elevator1.move(bld.elevator1.topFloor);
		result++;
		// lift coming down and dropping all customers in the lift
		checkFloor = bld.elevator1.currentFloor;
		
		while(checkFloor>=1){
			
			//iterate "manually" as using an iterator while removing element throws error  
			for(int i=0; i<bld.elevator1.registerList.size(); i++){
				Customer currentLiftCustomer = bld.elevator1.registerList.get(i);
				
				if(currentLiftCustomer.getDestinationFloor()==checkFloor){
					if(bld.elevator1.currentFloor!=checkFloor){
						bld.elevator1.move(checkFloor);
						result++;
					}
					bld.elevator1.customerLeaves(currentLiftCustomer);
				}
			}
			checkFloor--;
		}
		
		defaultStrategyMoves = result;
		defaultStrategyTime.stop();
		long defTime = defaultStrategyTime.getElapsedTime();
		System.out.println("*************************************************");
		System.out.println("*  The default strategy simulation is complete  *");
		System.out.println("*************************************************");
		System.out.println(" ");
		return defTime;
	}

	private static void createCustomers(Building bld, int numOfCustomers) {
		// TODO Auto-generated method stub
		for (int i=1; i<=numOfCustomers; i++){
			Customer newCustomer = new Customer(i,bld);
			bld.customerList.add(newCustomer);
			System.out.println("New customer created: ID="+newCustomer.getID()+"; Start floor="+newCustomer.getStartFloor()+"; Destination floor="+newCustomer.getDestinationFloor());
		}
		
	}

	static void initiateSimulation(Building bld){
		//System.out.print("The simulation will run for "+ bld.elevator1.numOfCustomers+ " customers, ");
		if (bld.getNumOfFloors()>12){
			int topFloor = bld.getNumOfFloors()+1;
			System.out.print("in an American building with "+ bld.getNumOfFloors() + " floors, from 1 to "+ topFloor +" (floor 13 is skipped)." );
		} else {
			System.out.print("in a building with "+ bld.getNumOfFloors() + " floors, starting from 1 to "+ bld.getNumOfFloors());
		}
		System.out.println();
		bld.elevator1.getCurrentFloor();
		//bld.elevator1.getDirection();
	}
}
