package teamrocket;
import robocode.*;
import java.awt.geom.*;
import robocode.util.*;
import java.lang.Math;

public class Teamrocket extends AdvancedRobot
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
	double mix = getX();
	double miy = getY();
	double pb = getHeadingRadians() + e.getBearingRadians(); // bearing pero solo +
	double ex = getX() + e.getDistance() * Math.sin(pb);
	double ey = getY() + e.getDistance() * Math.cos(pb); // donde esta el tipo
	double ehead = e.getHeadingRadians(); // adonde apunta
	double evel = e.getVelocity(); // su velocidad
	double alto = getBattleFieldHeight(),ancho = getBattleFieldWidth(); // la cancha
	double pex = ex, pey = ey; // donde va a estar, arranco con esto y le sumo su mov
	double t = 0; // esto es para ver cuanto tiempo queda en el tick
	double velB = 20 - Potencia*3; //esta es la velocidad de la bala
	
	while ((++t) * (velB) < Point2D.Double.distance(mix, miy, pex, pey)){
	// dice que mientras la distancia que puede hacer la bala este turno es menor q la que efectivamente hay
	pex += Math.sin(ehead) * evel; // Entonces, le calculo donde va a estar el enemy
	pey += Math.cos(ehead) * evel; // a partir de donde va y su vel
	if (pex < 18 || pey < 18 || pex > ancho - 18 || pey > alto - 18)
	{ // esto dice que si el bot se la esta por dar contra la pared no disparar como siguiese, 18 es la h y w
		pex = Math.min(Math.max(18.0, pex), ancho - 18.0);	
		pey = Math.min(Math.max(18.0, pey), alto - 18.0);
		break;
	}
}
double alfa = Utils.normalAbsoluteAngle(Math.atan2(pex - getX(), pey - getY()));
// math.atan2 me dice el angulo que forma el eje x con la linea que une los puntos donde estoy y donde va a estar el gil
// normalabsoluteangle lo pasa a entre 0 y 2pi xq el de arriba va de menos pi a mas pi
setTurnRadarRightRadians(Utils.normalRelativeAngle(pb - getRadarHeadingRadians())); 
// apunto a donde va a estar al final de t, relativo a donde yo estoy apuntando ahora
setTurnGunRightRadians(Utils.normalRelativeAngle(alfa - getGunHeadingRadians())); // apunto a donde va a estar cuando le llegue la bala
fire(Potencia*c/100);

}}