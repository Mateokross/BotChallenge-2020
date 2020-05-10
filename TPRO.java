package teamrocket;
import robocode.*;
import java.awt.geom.*;
import robocode.util.*;
import java.lang.Math;

public class TPRO extends AdvancedRobot
{
int a=0,b=0, c = 100; // confianza
public void run() {
	setAdjustGunForRobotTurn(true);// pa desvincular el radar
	setAdjustRadarForGunTurn(true);
	setTurnRadarRight(Double.POSITIVE_INFINITY);
	while(true) {
		back(50); // aca ponemos el movimiento
		setTurnRadarRight(Double.POSITIVE_INFINITY);} // esto es por si lo pierde que lo vuelva a encontrar
	}
public void onBulletMissed(BulletMissedEvent e) {
	a = Math.max(c-10,5);
	c=a;
}	
public void onBulletHit(BulletHitEvent e) {
	b = Math.min(c+30,100);
	c=b;
	}

public void onScannedRobot(ScannedRobotEvent e) {
	double PotenciaMax = Math.min(3.0,getEnergy()); // no nos morimos disparando
	double Potencia = Math.min(PotenciaMax, e.getEnergy()); // no gastamos al pedo
	double myX = getX();
	double myY = getY();
	double absoluteBearing = getHeadingRadians() + e.getBearingRadians(); // bearing pero solo +
	double enemyX = getX() + e.getDistance() * Math.sin(absoluteBearing);
	double enemyY = getY() + e.getDistance() * Math.cos(absoluteBearing); // donde esta el tipo
	double enemyHeading = e.getHeadingRadians(); // adonde apunta
	double enemyVelocity = e.getVelocity(); // su velocidad
	double battleFieldHeight = getBattleFieldHeight(),battleFieldWidth = getBattleFieldWidth(); // la cancha
	double predictedX = enemyX, predictedY = enemyY; // donde va a estar, arranco con esto y le sumo su mov
	double deltaTime = 0; // esto es para ver cuanto tiempo queda en el tick
	double velB = 20 - Potencia*3; //esta es la velocidad de la bala
	
	while ((++deltaTime) * (velB) < Point2D.Double.distance(myX, myY, predictedX, predictedY)){
	// dice que mientras la distancia que puede hacer la bala este turno es menor q la que efectivamente hay
	predictedX += Math.sin(enemyHeading) * enemyVelocity; // Entonces, le calculo donde va a estar el enemy
	predictedY += Math.cos(enemyHeading) * enemyVelocity; // a partir de donde va y su vel
	if (predictedX < 18 || predictedY < 18 || predictedX > battleFieldWidth - 18 || predictedY > battleFieldHeight - 18)
	{ // esto dice que si el bot se la esta por dar contra la pared no disparar como siguiese, 18 es la h y w
		predictedX = Math.min(Math.max(18.0, predictedX), battleFieldWidth - 18.0);	
		predictedY = Math.min(Math.max(18.0, predictedY), battleFieldHeight - 18.0);
		break;
	}
}
double theta = Utils.normalAbsoluteAngle(Math.atan2(predictedX - getX(), predictedY - getY()));
// math.atan2 me dice el angulo que forma el eje x con la linea que une los puntos donde estoy y donde va a estar el gil
// normalabsoluteangle lo pasa a entre 0 y 2pi xq el de arriba va de menos pi a mas pi
setTurnRadarRightRadians(Utils.normalRelativeAngle(absoluteBearing - getRadarHeadingRadians())); 
// apunto a donde va a estar al final de t, relativo a donde yo estoy apuntando ahora
setTurnGunRightRadians(Utils.normalRelativeAngle(theta - getGunHeadingRadians())); // apunto a donde va a estar cuando le llegue la bala
fire(Potencia*c/100);

}}