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
		//System.out.println("Your building has: "+ numOfFloors+" floors." );

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
		Iterator<Customer> customerIterator = bld.customerList.iterator();
		while (customerIterator.hasNext()){
			if(bld.elevator1.getCurrentFloor()==customerIterator.next().getStartFloor()){
				
			}
		}
		int result = 0;
		
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
		Elevator.getCurrentFloor();
		Elevator.getDirection();
	}
}
