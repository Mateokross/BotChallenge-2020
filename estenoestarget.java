package teamrocket;


import robocode.AdvancedRobot;
import robocode.Condition;
import robocode.CustomEvent;

import java.awt.*;


public class estenoestarget extends AdvancedRobot {

	int trigger; // Keeps track of when to move
	int wallMargin = 50;
	double leftWall = getX(),rightWall = getBattleFieldWidth() - getX(), bottomWall = getY(), topWall = getBattleFieldHeight() - getY();


	/**
	 * TrackFire's run method
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.white);
		setGunColor(Color.white);
		setRadarColor(Color.white);

		// Initially, we'll move when life hits 80
		trigger = 80;
		// Add a custom event named "trigger hit",
		addCustomEvent(new Condition("closetowall") {
			public boolean test() {
				if(leftWall <= wallMargin ||
				 // or we're too close to the right wall
				 rightWall <= wallMargin ||
				 // or we're too close to the bottom wall
				 bottomWall <= wallMargin ||
				 // or we're too close to the top wall
				 topWall <= wallMargin){
					return (true);
				}else{
					return (false);
				}
			
			}
		});
	}

	/**
	 * onCustomEvent handler
	 */
	public void onCustomEvent(CustomEvent e) {
		// If our custom event "triggerhit" went off,
		if (e.getCondition().getName().equals("closetowall")) {
			// Adjust the trigger value, or
			// else the event will fire again and again and again...
			trigger -= 20;
			out.println("Ouch, down to " + (int) (getEnergy() + .5) + " energy.");
			// move around a bit.
			turnLeft(65);
			ahead(100);
		}
	}
}
