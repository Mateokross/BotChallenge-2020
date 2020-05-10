package teamrocket;
import robocode.*;
import java.awt.Color;
import robocode.util.*;
import java.awt.geom.*;
import java.lang.Math;

public class Tiroalblanco extends AdvancedRobot
{	 
	public void run() {
		setBodyColor(new Color(255,255,255));
		setGunColor(new Color(255,0,0));
		setRadarColor(new Color(0,0,0));
		setBulletColor(new Color(164,16,201));
		setScanColor(new Color(4,0,255));
		setAdjustGunForRobotTurn(true);// pa desvincular el radar
		setAdjustRadarForGunTurn(true);
		setTurnRadarRight(Double.POSITIVE_INFINITY);// siempre dando vueltas, lo cambiamos por el codigo de juani
// aca abajo voy a hacer un par de funciones necesarias

while(true) {
	ahead(0);
	back(0);
	if 	(Math.abs(getRadarHeading() - getGunHeading()) > 40) {
		if ((Math.abs(getRadarHeading() - getGunHeading()) >= 180 && getRadarHeading() >= getGunHeading()) || (Math.abs(getRadarHeading() - getGunHeading()) < 180 && getRadarHeading() < getGunHeading()))
		{
			if (Math.abs(getRadarHeading() - getGunHeading()) > 20) {
			turnGunLeft(20); } // probe con set pero se caga no se que onda
			else {
				turnGunLeft(Math.abs(getRadarHeading() - getGunHeading()));}
		}
		else {
			if (Math.abs(getRadarHeading() - getGunHeading()) > 20) {
			turnGunRight(20); }
			else {
			turnGunRight(Math.abs(getRadarHeading() - getGunHeading())); } 
			} // contando las {} no tiene sentido esta de aca, pero me la pedia el programa
		}}}

	public void onScannedRobot(ScannedRobotEvent e) {
		double con = 0; 
		con = Math.abs(getRadarHeading() - getGunHeading()); // saco la confianza a partir de que tan cerca
		int conf = (int) con; // esta el gun del radar, confio en juani para que le apunte siempre el radar
		double potencia = 0, vel=0, a=0 ; 
		a=getGunHeat();
	if (a == 0) { // si esta caliente no dispara
	conf = 4; // esto va a ser una formula
		switch (conf) {
			case 0:
			fire(3-conf*3/5);
			break;
			case 1:
			fire(3-conf*3/5);
			break;
			case 2:
			fire(3-conf*3/5);
			break;
			case 3:
			fire(3-conf*3/5);
			break;
			case 4:
			fire(3-conf*3/5);
			break;
			case 5:
			fire(3-conf*3/5);
			break;
		}
	potencia = (3-conf*3/5);
	}
	vel = 20 - potencia*3; //esta es la velocidad de la bala

	double mix = getX(), miy= getY(), pb = getHeadingRadians() + e.getBearingRadians(); // bearing positivo
	double ex = mix + e.getDistance() * Math.sin(pb), ey = miy + e.getDistance() * Math.cos(pb); // saco la posicion del otro
	double ehead = e.getHeadingRadians(), ev =e.getVelocity(); //veo a donde va y la vel.
	double pex = ex + Math.sin(ehead)*ev , pey = ey + Math.cos(ehead)*vel; // predicion del x y el y del enemigo
	double yt= getBattleFieldHeight(), xt = getBattleFieldWidth();
	if(	pex < 18.0 // de aca hasta lo verde de abajo no entiendo un carajo
		|| pey< 18.0
		|| pex > xt - 18.0
		|| pey > yt - 18.0){

		pex = Math.min(Math.max(18.0, pex), 
		    xt - 18.0);	
		pey = Math.min(Math.max(18.0, pey), 
		    yt - 18.0);
	}

	double theta = Utils.normalAbsoluteAngle(Math.atan2(
    pex - getX(), pey - getY()));

//	setTurnRadarRightRadians(Utils.normalRelativeAngle(
//    pb - getRadarHeadingRadians()));

if (Math.abs(getRadarHeading() - getGunHeading()) < 35) {
	//setTurnGunRight(getHeading() - getGunHeading() + e.getBearing()); // bearing es la diferencia
// entre hacia donde esta el bot enemigo y hacia donde estas apuntando vos va de -180 a +180 
	setTurnGunRightRadians(Utils.normalRelativeAngle(
    theta - getGunHeadingRadians()));
	}
		setTurnRadarRight(2.0 * Utils.normalRelativeAngleDegrees(getHeading() + e.getBearing() - getRadarHeading()));
				
	}
}
