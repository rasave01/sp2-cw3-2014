package elevator;

import java.util.Iterator;
/**
 * @author Gary Murphy + Radu Asavei
 *
 */
import java.util.Scanner;


public class Simulator {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of floors for the building: ");
		int numOfFloors = input.nextInt();
		Building building1  = new Building(numOfFloors);
		

		System.out.println("Enter the number of customers for the elevator: ");
		int numOfCustomers = input.nextInt();
		input.close();
		
		//Start simulation
		System.out.print("The simulation will run for "+ numOfCustomers+ " customers, ");
		initiateSimulation(building1);
		
		createCustomers(building1,numOfCustomers);
		
		int defaultRuns = runDefaultStrategy(building1);
		int ourRuns = runOurStrategy(building1);
		
		//check runs and show result
		if (defaultRuns == ourRuns){
			System.out.println ("Both strategies have the same efficiency");
		} else if (defaultRuns<ourRuns){
					System.out.println ("The default strategy is more efficient");
				} else {
					System.out.println ("Our strategy is more efficient");
				}
	}

	private static int runOurStrategy(Building building1) {
		// TODO Auto-generated method stub
		int result = 0;
		return result;
	}

	private static int runDefaultStrategy(Building bld) {
		// TODO Auto-generated method stub
		// move the elevator to the 1st floor
		int result = 0;
		System.out.println("Default strategy simulation has started:");
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
		
		//move elevator to top floor
		bld.elevator1.move(bld.elevator1.topFloor);
		
		// lift coming down and dropping all customers
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
		
		
		System.out.println("Default strategy efficiency - Number of stops = "+result);
		return result;
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
