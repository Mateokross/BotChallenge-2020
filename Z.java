package teamrocket;
import robocode.*;

public class Z extends AdvancedRobot
{
	public void run() {
		
	while(true) {	
		turnRight(-getHeading());
		if (getHeading() == 0)	{
		back (getY() - 18);
		}
	}
	}
}
