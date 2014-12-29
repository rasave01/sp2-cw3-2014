/**
 * @author Radu Asavei + Gary Murphy
 * @since 2-November-2014
 * @version 29-December-2014
 */
 
package elevator;

import java.util.InputMismatchException;
import java.util.Iterator;

import java.util.Scanner;


public class Simulator {

	private static int ourMoves;
	private static int defaultMoves;
	private static int ourSteps;
	private static int defaultSteps;

	/* <pre>
	 * Starts <code>main</code> method. 
	 * <li>Prompts user for input to run the elevator simulator.</li>
	 * <li>
	 * <p>A summary will be printed once the simulation run ends
	 * which will show the difference between the default and the new/custom strategy 
	 * and display which one is more efficient</li>
	 * @exception <code>InterruptedException</code> Describe any thrown exception
	 * </pre>
	 */
	public static void main(String[] args) throws InterruptedException {

		//Create Scanner variable to accept user input
		Scanner input = new Scanner(System.in);
		
		/*
		 * Create parameters to be used in the input validation loop below
		 */
		int numOfFloorsValid = 0;
		boolean validInput = false;
		System.out.println("Enter the number of floors for the building: ");
		
		/*
		 * 
		 */
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
		System.out.println ("elevator stops = the number of times the elevator stopped        ");
		System.out.println ("      The default strategy made "+defaultMoves+" elevator stops.");
		System.out.println ("              Our strategy made "+ourMoves+" elevator stops.");
		System.out.println ("simulated run-time = the time the elevator took to run the simulation ");
		System.out.println ("(1 millisecond processor time = 1 second elevator time)        ");
		System.out.println (" The simulated run-time for the default strategy was "+defaultTime+" milliseconds.");
		System.out.println ("         The simulated run-time for our strategy was "+ourTime+" milliseconds.");
		System.out.println ("elevator steps = the distance (in floors) the elevator travelled  ");
		System.out.println ("      The default strategy made "+defaultSteps+" elevator steps.");
		System.out.println ("              Our strategy made "+ourSteps+" elevator steps.");
		
		
		if (defaultMoves == ourMoves){
			System.out.println (" By elevator stops both strategies have the same efficiency   ");
		} else if (defaultMoves<ourMoves){
				int stops=ourMoves-defaultMoves;
					System.out.println (" By elevtaor stops the default strategy is more efficient by " +stops+ " stops.");
				} else {
					int stops=defaultMoves-ourMoves;
						System.out.println (" By elevator stops our strategy is more efficient by "+stops+" stops.");
				}
		if (defaultTime == ourTime){
			System.out.println (" By travel time both strategies have the same efficiency   ");
		} else if (defaultTime<ourTime){
				long eff=ourTime-defaultTime;
					System.out.println (" By travel time the default strategy is more efficient by " + eff + " milliseconds.");
				} else {
					long eff=defaultTime-ourTime;
						System.out.println (" By travel time our strategy is more efficient by "+eff+" milliseconds.");
				}
		
		if (defaultSteps == ourSteps){
			System.out.println (" By elevator steps both strategies have the same efficiency   ");
		} else if (defaultTime<ourTime){
				int steps=ourSteps-defaultSteps;
					System.out.println (" By elevator steps the default strategy is more efficient by " + steps + " steps.");
				} else {
					int steps=defaultSteps-ourSteps;
						System.out.println (" By elevator steps our strategy is more efficient by "+ steps +" steps.");
				}
		
		
		
		System.out.println ("************************ END OF SIMULATION ****************************");
	} // end of main method
	
	/* <pre>	
	 * Runs our strategy in Building Bld 
	 * 	@return <code>ourTime</code> i.e. the time the elevator took to pick-up and drop all customers
	 * </pre>
	 */
	private static long runOurStrategy(Building bld) throws InterruptedException {
		
		
		/* 	move the elevator to the 1st floor in order to reset
		* 	This move does not count towards the result, 
		*	as both simulations need to start from the same floor, 
		*	for a more accurate comparison 
		*/
		bld.elevator1.move(1); 
		
		StopWatch ourStrategyTime = new StopWatch();
		// initialise result
		ourMoves = 0;
		ourSteps = 0;
		ourStrategyTime.start();
		
		System.out.println("Our strategy simulation has started:");
		
		// move the elevator to the 1st floor
		bld.elevator1.move(1);
		
		/* elevator going up and picking up all customers
		 * regardless of which direction they are travelling
		 */
		int checkFloor = bld.elevator1.currentFloor;
		while(checkFloor<=bld.elevator1.topFloor){
			
			Iterator<Customer> customerIterator = bld.customerList.iterator();
			while(customerIterator.hasNext()){
				Customer currentCustomer = customerIterator.next();
				if(currentCustomer.getStartFloor()==checkFloor){
					if(bld.elevator1.currentFloor!=checkFloor){
						ourSteps = ourSteps+bld.elevator1.move(checkFloor);
						ourMoves++;
					}
					bld.elevator1.customerJoins(currentCustomer);
				}

			}
			
			/* check if customers already in the elevator need to get off on this floor
			 * if so, take them out of the elevator and off of the registerList
			 */
			for(int i=0; i<bld.elevator1.registerList.size(); i++){
				Customer currentElevCustomer = bld.elevator1.registerList.get(i);
				if(currentElevCustomer.getDestinationFloor()==checkFloor){
					/*if(bld.elevator1.currentFloor!=checkFloor){
						bld.elevator1.move(checkFloor);
						result++;
					}*/
					bld.elevator1.customerLeaves(currentElevCustomer);
				}
			}
			
			checkFloor++;
		}
		while(checkFloor>=1){
			
			/*
			 *   iterate "manually" as using an iterator while removing element throws error
			 */
			for(int i=0; i<bld.elevator1.registerList.size(); i++){
				Customer currentElevCustomer = bld.elevator1.registerList.get(i);
				
				if(currentElevCustomer.getDestinationFloor()==checkFloor){
					if(bld.elevator1.currentFloor!=checkFloor){
						ourSteps = ourSteps+bld.elevator1.move(checkFloor);
						ourMoves++;
					}
					bld.elevator1.customerLeaves(currentElevCustomer);
				}
			}
			checkFloor--;
		}
		ourStrategyTime.stop();
		long ourTime = ourStrategyTime.getElapsedTime();
		System.out.println("*************************************************");
		System.out.println("*      Our strategy simulation is complete      *");
		System.out.println("*************************************************");
		System.out.println(" ");
		return ourTime;
		
	} //end of runOurStrategy method

	
	/*	method to run the default strategy in the user created building
	 * 	@return the number of stops the elevator made to pick-up and drop all customers
	 */
	private static long runDefaultStrategy(Building bld) throws InterruptedException {
		StopWatch defaultStrategyTime = new StopWatch();
		
		// move the elevator to the 1st floor
		bld.elevator1.move(1);
		
		// initialise results
		defaultSteps = 0;
		defaultMoves = 0;
		defaultStrategyTime.start();
		
		
		System.out.println("Default strategy simulation has started:");
				
		// elevator going up and picking up all customers
		int checkFloor = bld.elevator1.currentFloor;
		while(checkFloor<=bld.elevator1.topFloor){
			//System.out.println("Checking floor "+checkFloor);
			Iterator<Customer> customerIterator = bld.customerList.iterator();
			while(customerIterator.hasNext()){
				Customer currentCustomer = customerIterator.next();
				//System.out.println("Checking customer "+currentCustomer.getID());
				
				if(currentCustomer.getStartFloor()==checkFloor){
					if(bld.elevator1.currentFloor!=checkFloor){
						defaultSteps = defaultSteps + bld.elevator1.move(checkFloor);
						defaultMoves++;
					}
					bld.elevator1.customerJoins(currentCustomer);
				}
			}
			checkFloor++;
		}
		
		//move elevator to top floor as required by default strategy
		defaultSteps = defaultSteps + bld.elevator1.move(bld.elevator1.topFloor);
		defaultMoves++;
		
		
		// elevator coming down and dropping off all customers in the elevator
		checkFloor = bld.elevator1.currentFloor;
		
		while(checkFloor>=1){
			
			//iterate "manually" as using an iterator while removing element throws error  
			for(int i=0; i<bld.elevator1.registerList.size(); i++){
				Customer currentElevCustomer = bld.elevator1.registerList.get(i);
				
				if(currentElevCustomer.getDestinationFloor()==checkFloor){
					if(bld.elevator1.currentFloor!=checkFloor){
						defaultSteps = defaultSteps + bld.elevator1.move(checkFloor);
						defaultMoves++;
					}
					bld.elevator1.customerLeaves(currentElevCustomer);
				}
			}
			checkFloor--;
		}
		
		defaultStrategyTime.stop();
		long defTime = defaultStrategyTime.getElapsedTime();
		System.out.println("*************************************************");
		System.out.println("*  The default strategy simulation is complete  *");
		System.out.println("*************************************************");
		System.out.println(" ");
		return defTime;
	} // end of runDefaultStrategy method
	
	
	/* iterate through number of customers and create customers
	 * use i as ID as this will ensure each customer has has an unique ID
	 */
	private static void createCustomers(Building bld, int numOfCustomers) {
		
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
	} //end of createCustomers method
}
