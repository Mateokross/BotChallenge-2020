package teamrocket;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Tiroalblanco - a robot by (Tomi)
 */
public class Tiroalblanco extends AdvancedRobot
{
	//run: Tiroalblanco's default behavior
	 
	public void run() {
		
		setBodyColor(new Color(255,255,255));
		setGunColor(new Color(255,0,0));
		setRadarColor(new Color(0,0,0));
		setBulletColor(new Color(164,16,201));
		setScanColor(new Color(4,0,255));
		
		double conf = 0.65; // confianza de que le pegamos
		double PMax = 3, Pmin = 0.1, Pot=2; // potencia max min y la que usamos posta
	
		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(conf);
			turnGunRight(PMax);
			back(Pmin);
			turnGunRight(Pot);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
	
		fire(0.1);
	}



	//onHitByBullet: What to do when you're hit by a bullet
	public void onHitByBullet(HitByBulletEvent e) {
			back(0);
	}
// onHitWall: What to do when you hit a wall
	public void onHitWall(HitWallEvent e) {
			back(0);
	}	
}
