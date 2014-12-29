/**
 * @author Radu Asavei + Gary Murphy
 * @since 2-November-2014
 * @version 29-December-2014
 */

package elevator;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Radu Asavei + Gary Murphy
 *
 */
public class StopWatchTest {

	StopWatch timer = new StopWatch();
	
	@Test
	public void getElapsedTimeTest() throws InterruptedException {
		timer.start();
		//Pause for 2 seconds
		TimeUnit.SECONDS.sleep(2);
		timer.stop();
		int expected = 2;
		double actualApprox = (double) timer.getElapsedTime();
		int actual = (int) Math.floor(actualApprox);
		System.out.println("actual time = " + actual);
		Assert.assertEquals(expected, actual);
	}

}
