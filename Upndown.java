package teamrocket;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Upndown - a robot by (your name here)
 */
public class Upndown extends Robot
{
		double moveAmount; // How much to move
		

	public void run() {
	
	moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// Initialize moveAmount to the maximum possible for this battlefield.
	turnLeft(getHeading() % 90);
	ahead(moveAmount);
	turnRight(180);
		// Robot main loop
		while(true) {
		
			// Move up the wall
			ahead(moveAmount);
		
			// Turn back
			turnRight(180);
		}
	}
}