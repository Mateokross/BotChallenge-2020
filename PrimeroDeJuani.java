package teamrocket;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * PrimeroDeJuani - a robot by (your name here)
 */
public class PrimeroDeJuani extends AdvancedRobot
{
	/**
	 * run: PrimeroDeJuani's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here
		
		double distancia = 10000 //para ver cuantos pixeles vamos a querer avanzar
		int alto = 0, largo = 0
		
		
		//colores
		setBodyColor(Color.white);
		setGunColor(Color.red);
		setRadarColor(Color.black);
		setBulletColor(Color.purple);
		setScanColor(Color.blue);
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			alto = getHeight()
			largo = getWidth()
			
			if alto/getBattleFieldHeight()< 0.95 && largo/getBattleFieldWidht()< 0.95 
				getHeading()
			
			
			setAhead(distancia)
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}








