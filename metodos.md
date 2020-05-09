[toc]

# Basics

#### Physics

- Time measurement

  Robocode time is measured in "ticks". Each robot gets one turn per tick. 1 tick = 1 turn.

- Coordinates system

  Robocode uses the [Cartesian coordinate system](http://en.wikipedia.org/wiki/Cartesian_coordinate_system), which means that that the (0, 0) coordinate is located at the bottom-left corner of the battlefield.

- Rotational direction system

  Robocode uses a clockwise direction convention where 0°/360° is north, 90° is east, 180° is south, and 270° is west.

###### Movement physics

- Acceleration (a)

  Robots accelerate at the rate of 1 pixel/turn every turn. Robots decelerate at the rate of 2 pixels/turn every turn. Robocode determines acceleration for you, based on the distance you are trying to move.

- Velocity (v)

  The velocity equation is: v = at. Velocity can never exceed 8 pixels/turn. Note that technically, velocity is a vector, but in Robocode we simply assume the direction of the vector to be the robot's heading.

- Distance (d)
  The distance formula is: d = vt. That is, distance = velocity * time

###### Rotation

- Robot base rotation

  The maximum rate of rotation is: (10 - 0.75 * abs(velocity)) deg/turn. The faster you're moving, the slower you turn.

- Gun rotation

  The maximum rate of rotation is: 20 deg/turn. This is added to the current rate of rotation of the robot.

- Radar rotation

  The maximum rate of rotation is: 45 deg/turn. This is added to the current rate of rotation of the gun.

###### Bullets

- Bullet damage

  4 * firepower. If firepower > 1, it does an additional damage = 2 * (power - 1).

- Bullet velocity

  20 - 3 * firepower.

- Gun heat generated on firing

  1 + firepower / 5. You cannot fire if gunHeat > 0. All guns are hot at the start of each round.

- Energy returned on hit

  3 * firepower.

###### Collisions

- Collision with another robot

  Each robot takes 0.6 damage. If a robot is moving away from the collision, it will not be stopped.

- Collision with a wall

  AdvancedRobots take damage = abs(velocity) * 0.5 - 1 (never < 0).

###### Firing pitfall

Because bullets are fired before the gun is moved, calling setFire() will cause the bullet to leave at the current gun heading. This may seem counter-intuitive if you are used to thinking in terms of pointing a gun, then shooting. It is also inconvenient because you can't call `setTurnGun(...)` and `setFire(...)` right after each other (not if you need perfect accuracy, anyway). Most of the time, the error will be so small you won't notice it. El codigo para solucionarlo esta al final de la pág: https://robowiki.net/wiki/Robocode/Game_Physics

#### Scoring

- **Total Score** ‒ This is everything else added up, and determines each robot's rank in this battle.
- **Survival Score** ‒ Each robot that's still alive scores 50 points every time another robot dies.
- **Last Survivor Bonus** ‒ The last robot alive scores 10 additional points for each robot that died before it.
- **Bullet Damage** ‒ Robots score 1 point for each point of damage they do to enemies.
- **Bullet Damage Bonus** ‒ When a robot kills an enemy, it scores an additional 20% of all the damage it did to that enemy.
- **Ram Damage** ‒ Robots score 2 points for each point of damage they cause by ramming enemies.
- **Ram Damage Bonus** ‒ When a robot kills an enemy by ramming, it scores an additional 30% of all the damage it did to that enemy.

#### Energy

Reducing the firepower to use just enough energy to kill an opponent would save a vast amount of energy. Instead of firing full power at a robot with an energy reading of 3.0, you could shoot at with 1.0 energy or even 0.75. 

The formula to calculated the damage to enemy robot for given firepower is `4 * power + max(0, 2 * (power - 1) )`, where *power* is a value between 0.1 and 3. But usually, for some reasons, we do not really care about `max(0, 2 * (power - 1)` and just fire at power `enemyEnergy / 4`.

#### wave surfing

#### Maximum Escape Angle

#### Torneos

###### 1er torneo

A primary differentiator of movements at the highest level of [1v1](https://robowiki.net/wiki/1v1) is accurate bullet dodging. There are still strategic choices, like [distancing](https://robowiki.net/wiki/Distancing), attack angle control, [dive protection](https://robowiki.net/wiki/Dive_Protection), and staying out of corners (which can limit your [Maximum Escape Angle](https://robowiki.net/wiki/Maximum_Escape_Angle)). But distancing is reciprocal, many attack angle control schemes perform similarly, and the benefit of staying far away may be offset by getting yourself cornered.

Between gathering scans of the enemy every tick and receiving bullet hit events for every bullet that hits you or the enemy, you have a very high amount of information about the battle. About the only thing you are unaware of is the location of enemy bullets that don't/haven't yet hit you or one of your bullets. [Wave Surfing](https://robowiki.net/wiki/Wave_Surfing) movements with [Precise Prediction](https://robowiki.net/wiki/Precise_Prediction) put all of this together to dodge enemy bullets effectively. This is particularly potent against [simple targeters](https://robowiki.net/wiki/Category:Simple_Targeting_Strategies), and is also the most effective form of movement against nearly every other targeting algorithm.

###### 3er torneo

Besides moving unpredictably, it's also very important in [Melee](https://robowiki.net/wiki/Melee) to choose a strategically beneficial location. For instance, not being closest to any other bot means that you will likely not be targeted by other bots - this allows you to easily collect survival points and also rack up bullet damage against bots that are not paying attention to you, making them easier targets. Corners tend to be coveted positions in a Melee battle.

You cannot scan the entire battle field every tick in Melee, and Robocode's bullet hit events only fire for bullets that you fire or that hit you, so you have access to a far lower percentage of the total information in the battle. This makes it difficult or impossible to employ precise forms of bullet dodging in Melee. The emphasis, then, is on strategy and moving randomly with respect to the bots most likely to target you.

