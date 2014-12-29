/*
 * This <code>StopWatch</code> class will be used to measure the running time of the two elevator strategies i.e. : 
 * - The default strategy
 * - The custom strategy
 * 
 * @author Radu Asavei &amp Gary Murphy
 * 		Taken from class notes: Sorting and Searching (p13-16)
 * 		Notes credit: 			Copyright © 2013 by John Wiley & Sons.
 * @since 2-November-2014
 * @version 29-December-2014
 */
package elevator;

public class StopWatch {
	private long elapsedTime;
	private long startTime;
	private boolean isRunning;
	
	/**
	 * Constructs a <code>StopWatch</code> that is in the stopped state
	 * and has no time accumulated
	 */
	public StopWatch(){
		reset();
	}
	
	/**
	 * Starts the <code>StopWatch</code>. 
	 * Time starts accumulating now.
	 */
	public void start(){
		if(isRunning){
			return;
		}
		isRunning = true;
		startTime = System.currentTimeMillis();
	}
	
	public void stop(){
		if(!isRunning){
			return;
		}
		isRunning = false;
		long endTime = System.currentTimeMillis();
		elapsedTime = elapsedTime + endTime - startTime;
	}
	
	/**
	 * @return the total elapsed time.
	 */
	public long getElapsedTime(){
		if(isRunning){
			long endTime = System.currentTimeMillis();
			return elapsedTime + endTime - startTime;
		}else{
			return elapsedTime;
		}
	}
	
	/**
	 * Stops the watch and resets the elapsed time to 0(zero).
	 */
	public void reset(){
		elapsedTime = 0;
		isRunning = false;
	}

}


