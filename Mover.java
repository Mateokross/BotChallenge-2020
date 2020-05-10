package teamrocket;
import robocode.*;
import java.awt.*;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/AdvancedRobot.html

/**
 * Mover - a robot by (your name here)
 */
public class Mover extends AdvancedRobot
{

   
	boolean movingForward;
	/**
	 * run: Mover's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here
		//colores
		//setBodyColor(new Color(255,255,255));
		//setGunColor(new Color(255,0,0));
		//setRadarColor(new Color(0,0,0));
		//setBulletColor(new Color(164,16,201));
		//setScanColor(new Color(4,0,255));
		
  		 // Create the condition for our custom event
		   Condition closeToWallCondition = new Condition("closetowall") {
		       public boolean test() {
		           return (true);
		       }
		   };
		
		   // Add our custom event based on our condition
		   addCustomEvent(closeToWallCondition);
		
		// Robot main loop
		while(true) {

		}
	}

	/**
	 * closeToWall:  Custom event for avoiding collisions with wall.
	 */

	public void closeToWallCondition(){
		// Tell the game we will want to move ahead 40000 -- some large number
		setAhead(40000);
		movingForward = true;
		// Tell the game we will want to turn right 90
		setTurnRight(90);
		// At this point, we have indicated to the game that *when we do something*,
		// we will want to move ahead and turn right.  That's what "set" means.
		// It is important to realize we have not done anything yet!
		// In order to actually move, we'll want to call a method that
		// takes real time, such as waitFor.
		// waitFor actually starts the action -- we start moving and turning.
		// It will not return until we have finished turning.
		waitFor(new TurnCompleteCondition(this));
		// Note:  We are still moving ahead now, but the turn is complete.
		// Now we'll turn the other way...
		setTurnLeft(180);
		// ... and wait for the turn to finish ...
		waitFor(new TurnCompleteCondition(this));
		// ... then the other way ...
		setTurnRight(180);
		// .. and wait for that turn to finish.
		waitFor(new TurnCompleteCondition(this));
		// then back to the top to do it all again
	}
	
	/**
	 * onHitWall:  Handle collision with wall.
	 */
	public void onHitWall(HitWallEvent e) {
		// Bounce off!
		reverseDirection();
	}

	/**
	 * reverseDirection:  Switch from ahead to back & vice versa
	 */
	public void reverseDirection() {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		//fire(1);
	}

	/**
	 * onHitRobot:  Back up!
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If we're moving the other robot, reverse!
		if (e.isMyFault()) {
			reverseDirection();
		}
	}
}
