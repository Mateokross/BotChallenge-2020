package teamrocket;
import robocode.*;
import java.awt.*;
import java.awt.geom.*;
import robocode.util.*;
import java.lang.Math;

public class Meowth extends TeamRobot
{
int a=0,b=0, c = 100; // confianza
boolean inWall = false;
int missed = 0;

//para el radar		
	double energiaVieja = 100;
	int direccion = 1;
	int direcArma;	
//


public void run() {
	
	//colores del equipo rocket
		setBodyColor(new Color(255,255,255));
		setGunColor(new Color(255,0,0));
		setRadarColor(new Color(4,0,255));
		setBulletColor(new Color(164,16,201));
		setScanColor(new Color(4,0,255));
		
	setAdjustGunForRobotTurn(true);// pa desvincular el radar
	setAdjustRadarForGunTurn(true);// pa desvincular el arma

		
	while(true) {
	
	
	if(getY()>25 && inWall==false){
		turnRight(-getHeading());
	} else	{
		turnRight(-getHeading()+90);
		inWall=true;
	}
	if (getHeading() == 0)	{
		back (getY() - 23);
	}
	//if (getY() < 25) {
		setTurnRadarRight(Double.POSITIVE_INFINITY);
	//}
	}
	}
public void onBulletMissed(BulletMissedEvent e) {
	a = Math.max(c-10,5);
	c=a;
	
	while (missed <4){
	missed = missed +1;
	}
	
	if (missed>3){
	direcArma *= -1;
	turnRadarRight(100*direcArma);
	missed=0;
	
	}
}	
public void onBulletHit(BulletHitEvent e) {
	if (isTeammate(e.getName())){
	setBack(100*direccion);
}
	b = Math.min(c+30,100);
	c=b;
	}

public void onHitByBullet(HitByBulletEvent e) {
	if (getY()>25){
	setBack(50*direccion);}
}

public void onHitRobot(HitRobotEvent e) {
	
if(getY()>25){
	if (e.isMyFault()==true){
		setBack(50*direccion);
		setTurnRight(90);
		setAhead(100);
		setTurnLeft(90);
		inWall=false;		
}	else { 
	setBack(60*direccion);
}
	
	}
	
}

public void onScannedRobot(ScannedRobotEvent e) {
	if (isTeammate(e.getName())) {
			return;
		}
	//estar perpendicular al enemigo
	//setTurnRight(getHeading());
	double cambioEnergia = energiaVieja-e.getEnergy(); //variables que vamos a usar para saber si disparo
	
	
	//esquivas la bala
	if (cambioEnergia > 0 && cambioEnergia<=3){ //el maximo es 3 para asi no se confunde con eventos que le bajan vida
		if (getY()<25){
		direccion= direccion*-1; //esquivamos la bala
		setAhead(100*direccion); //distancia que nos corremos para esquivar
		}

	}//si estamos mas cerca nos corremos menos

	//por si lo habiamos perdido de vista
	direcArma = direcArma*-1;
	setTurnGunRight(Double.POSITIVE_INFINITY*direcArma);
	energiaVieja = e.getEnergy();
//	

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
	// mientras la distancia que puede hacer la bala este turno es menor q la que efectivamente hay
	pex += Math.sin(ehead) * evel; // Entonces, le calculo donde va a estar el enemy
	pey += Math.cos(ehead) * evel; // a partir de donde va y su vel
	if (pex < 18 || pey < 18 || pex > ancho - 18 || pey > alto - 18)
	{ // esto dice que si el bot se la esta por dar contra la pared no disparar como si siguiese, 18 es la h y w
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
fire(Potencia*c/100); //disparo
}}