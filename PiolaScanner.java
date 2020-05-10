
package teamrocket;
import robocode.*;
import java.awt.Color;


public class PiolaScanner extends AdvancedRobot
{
		int adelante = 100000;
		
	//para el radar
		double maxGrados = 360, minGrados = 0;
		int direccionScanner = 1, direccionTanque = 1; //puede ser 1 o -1
		int modoscanner = 1;
		//String[] enemigos = {};
		

	public void run() {
		//colores
		setBodyColor(new Color(255,255,255));
		setGunColor(new Color(255,0,0));
		setRadarColor(new Color(0,0,0));
		setBulletColor(new Color(164,16,201));
		setScanColor(new Color(4,0,255));
		
	//para el radar
		setAdjustRadarForGunTurn(true);
		
	

	while(true) {
			
			setTurnRadarRight(maxGrados*direccionScanner);
			execute();
			setAhead(adelante*direccionTanque);
			setTurnGunRight(20);
			setTurnGunRight(20); 
		}
	}



	public void onScannedRobot(ScannedRobotEvent e) {
		e.
		direccionScanner *= -1;
		
	}



	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	


	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like	
		direccionTanque = direccionTanque* -1;
		setTurnRadarRight(maxGrados*direccionScanner);
		execute();
	}	
}
