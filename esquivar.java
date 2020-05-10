package teamrocket;
import robocode.*;
import java.awt.geom.*;
import robocode.util.*;
import java.lang.Math;



public class esquivar extends AdvancedRobot
{

//para el radar		
	double energiaVieja = 100;
	int direccion = 1;
	int direcArma;
//

	public void run() {
		setAdjustGunForRobotTurn(true);// pa desvincular el radar
		setAdjustRadarForGunTurn(true);
		setTurnRadarRight(Double.POSITIVE_INFINITY);
		
		
		while(true) {
		
	back(0);
	setTurnRadarRight(Double.POSITIVE_INFINITY);}
	}



	public void onScannedRobot(ScannedRobotEvent e) {
	
//estar perpendicular al enemigo
	setTurnRight(e.getBearing()+90-30*direccion);
	double cambioEnergia = energiaVieja-e.getEnergy();
	//esquivas la bala
	if (cambioEnergia > 0 && cambioEnergia<=3){
		direccion= direccion*-1;
		setAhead((e.getDistance()/4+25)*direccion);
	}
	//cuandolovolves a encontrar
	direcArma = direcArma*-1;
	setTurnGunRight(Double.POSITIVE_INFINITY*direcArma);
	energiaVieja = e.getEnergy();
//	

double bulletPower = Math.min(3.0,getEnergy());
double myX = getX();
double myY = getY();
double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
double enemyX = getX() + e.getDistance() * Math.sin(absoluteBearing);
double enemyY = getY() + e.getDistance() * Math.cos(absoluteBearing);
double enemyHeading = e.getHeadingRadians();
double enemyVelocity = e.getVelocity();
double oldEnemyHeading = enemyHeading;
double enemyHeadingChange = enemyHeading - oldEnemyHeading;

double deltaTime = 0;
double battleFieldHeight = getBattleFieldHeight(), 
       battleFieldWidth = getBattleFieldWidth();
double predictedX = enemyX, predictedY = enemyY;
while((++deltaTime) * (20.0 - 3.0 * bulletPower) < 
      Point2D.Double.distance(myX, myY, predictedX, predictedY)){		
	predictedX += Math.sin(enemyHeading) * enemyVelocity;
	predictedY += Math.cos(enemyHeading) * enemyVelocity;
	enemyHeading += enemyHeadingChange;
	if(	predictedX < 18.0 
		|| predictedY < 18.0
		|| predictedX > battleFieldWidth - 18.0
		|| predictedY > battleFieldHeight - 18.0){

		predictedX = Math.min(Math.max(18.0, predictedX), 
		    battleFieldWidth - 18.0);	
		predictedY = Math.min(Math.max(18.0, predictedY), 
		    battleFieldHeight - 18.0);
		break;
	}
}
double theta = Utils.normalAbsoluteAngle(Math.atan2(
    predictedX - getX(), predictedY - getY()));

setTurnRadarRightRadians(Utils.normalRelativeAngle(
    absoluteBearing - getRadarHeadingRadians()));
setTurnGunRightRadians(Utils.normalRelativeAngle(
    theta - getGunHeadingRadians()));
fire(1);
}}
