package teamrocket;
import robocode.*;
import java.awt.Color;
import robocode.util.Utils;

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
		
		setAdjustGunForRobotTurn(true);// pa desvincular el radar
		setAdjustRadarForGunTurn(true);
		setTurnRadarRight(Double.POSITIVE_INFINITY);// siempre dando vueltas, lo cambiamos por el codigo de juani
		
		double conf = 0.65; // confianza de que le pegamos
		double PMax = 3, Pmin = 0.1, Pot=2; // potencia max min y la que usamos posta
	
		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(0);
			
			back(0);
			turnGunRight(0);
		}
	}

	// onScannedRobot: What to do when you see another robot
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
		setTurnRadarRight(2.0 * Utils.normalRelativeAngleDegrees(getHeading() + e.getBearing() - getRadarHeading()));
	}
}
