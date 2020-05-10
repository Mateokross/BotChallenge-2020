
package teamrocket;
import robocode.util.Utils;
import robocode.*;
import java.awt.Color;
import java.lang.Math;





public class PiolaScanner extends AdvancedRobot {
	//asignamos la clase enemigo a el objeto enemigo1 
	private enemigo enemigo1 = new enemigo();


	//simplemente una variable con valor alto
	int adelante = 100000;
	
	
	//para saber si adelante/atras
	int direccionTanque = 1; //puede ser 1 o -1

	

	public void run() {
		//colores del equipo rocket
		setBodyColor(new Color(255,255,255));
		setGunColor(new Color(255,0,0));
		setRadarColor(new Color(0,0,0));
		setBulletColor(new Color(164,16,201));
		setScanColor(new Color(4,0,255));
		
		//para el radar
		setTurnRadarRight(Double.POSITIVE_INFINITY);// siempre dando vueltas
		setAdjustRadarForGunTurn(true); //permite que el radar gire desanexado del arma
		enemigo1.reset();
	

	while(true) {
			enemigo1.reset(); //vaciamos la info del enemigo
			execute();//sino no funciona el setTurnRadarRight
			setAhead(adelante*direccionTanque);
			
		}
	}



	public void onScannedRobot(ScannedRobotEvent e) {
		if (enemigo1.none() || e.getName().equals(enemigo1.getName())) {
		enemigo1.update(e); //le actualiza las propiedades al enemigo
		
		}
		
		setTurnRadarRight(2.0 * Utils.normalRelativeAngleDegrees(getHeading() + e.getBearing() - getRadarHeading()));
		
		
		
	}



	public void onHitWall(HitWallEvent e) {
			
		direccionTanque = direccionTanque* -1;
		execute();
	}	
	

	public void onRobotDeath(RobotDeathEvent e) {
	
		if (e.getName().equals(enemigo1.getName())){
			enemigo1.reset(); //si nuestro enemigo murio, vaciamos la info para buscar otro
		}

	}

}
