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

###### Gun heat



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

#### Radar

A radar in Robocode can turn a maximum of 45° or π/4 rad in a single tick. The radar scans robots up to 1200 units away. The angle that the radar rotates between two ticks creates what is called a radar arc, and every robot detected within the arc is sent to the `onScannedRobot()` method in order of distance from the scanning bot. The closest bot is detected first, while the furthest bot is detected last. By default, the `onScannedRobot()` method has the lowest event priority of all the event handlers in Robocode, so it is the last one to be triggered each tick.

By default, Robocode hides radar arcs,can be enabled in Robocode's Preferences. (cuando probemos nuestro bot tenemos que hacer esto).

###### Codigos importantes y tips fundamentales

Your first action in `run()` should **always** be:

```
setAdjustGunForRobotTurn(true);
setAdjustRadarForGunTurn(true);
```

This allows your robot's base, gun, and radar to rotate independently. It is practically essential for radar locks and any form of accurate targeting.

One of the first actions your robot performs should be to turn the radar as much as possible. A simple implementation would be:

```
setTurnRadarRight(Double.POSITIVE_INFINITY);
```

This causes the radar to begin turning clockwise, forever. However, it may not be wise to always turn the radar clockwise. Sometimes, turning it counterclockwise might provide more information, faster. The optimal scan direction is the one with the shortest rotational difference to the angle between the robot and the battlefield center.

For even faster information collection, you should turn the gun (or even the robot base as well) in the same direction as the radar. Due to [Robocode game physics](https://robowiki.net/wiki/Robocode/Game_Physics), spinning the gun and radar at the same time will give your robot a 65° (20° + 45°) scan arc, instead of a 45° arc.





#### Torneos y su estrategia



###### 1er torneo

A primary differentiator of movements at the highest level of [1v1](https://robowiki.net/wiki/1v1) is accurate bullet dodging. There are still strategic choices, like [distancing](https://robowiki.net/wiki/Distancing), attack angle control, [dive protection](https://robowiki.net/wiki/Dive_Protection), and staying out of corners (which can limit your [Maximum Escape Angle](https://robowiki.net/wiki/Maximum_Escape_Angle)). But distancing is reciprocal, many attack angle control schemes perform similarly, and the benefit of staying far away may be offset by getting yourself cornered.

Between gathering scans of the enemy every tick and receiving bullet hit events for every bullet that hits you or the enemy, you have a very high amount of information about the battle. About the only thing you are unaware of is the location of enemy bullets that don't/haven't yet hit you or one of your bullets. [Wave Surfing](https://robowiki.net/wiki/Wave_Surfing) movements with [Precise Prediction](https://robowiki.net/wiki/Precise_Prediction) put all of this together to dodge enemy bullets effectively. This is particularly potent against [simple targeters](https://robowiki.net/wiki/Category:Simple_Targeting_Strategies), and is also the most effective form of movement against nearly every other targeting algorithm.

###### 1-vs-1 radar

One on one radars are the smallest of the bunch and many can get a scan in every turn, producing a perfect lock. This simplest lock is:

```
public void onScannedRobot(ScannedRobotEvent e) {
    setTurnRadarRight(2.0 * Utils.normalRelativeAngleDegrees(getHeading() + e.getBearing() - getRadarHeading()));
}
```



###### 3er torneo

Besides moving unpredictably, it's also very important in [Melee](https://robowiki.net/wiki/Melee) to choose a strategically beneficial location. For instance, not being closest to any other bot means that you will likely not be targeted by other bots - this allows you to easily collect survival points and also rack up bullet damage against bots that are not paying attention to you, making them easier targets. Corners tend to be coveted positions in a Melee battle.

You cannot scan the entire battle field every tick in Melee, and Robocode's bullet hit events only fire for bullets that you fire or that hit you, so you have access to a far lower percentage of the total information in the battle. This makes it difficult or impossible to employ precise forms of bullet dodging in Melee. The emphasis, then, is on strategy and moving randomly with respect to the bots most likely to target you.

###### Melee radars

Melee radars are more complex and take up considerably more room inside a robot. Since the field of opponents does not usually fall within a 45° area, compromises must be made between frequent data of one bot (e.g., the firing target) and consistently updated data of all bots. Common melee radars include:

- Spinning radar ‒ Simple but inefficient.
- Oldest scanned radar ‒ Scan all bots, and then reverse (unless it would be more efficient to not do that). Probably good enough.
- Optimal radar ‒ Present in all top melee bots. Left as an exercise to the reader.
- Gun heat lock ‒ Lock on a target before firing, spin otherwise. Some bots do this. / este es el que va





#### Como embocarle a un bot que se mueve

Let's assume a triangle with sides `a`, `b` and `c` and angles (vertices) `A`, `B`, and `C`. `A` is the angle opposite to `a`, `B` is opposite to b, and `C` is opposite to `c`. The [Law of sines](http://en.wikipedia.org/wiki/Law_of_sines) says that:

[![LawOfSines.png](https://robowiki.net/w/images/6/63/LawOfSines.png)](https://robowiki.net/wiki/File:LawOfSines.png)

Now let's say that your bot is in the vertex `A` and the enemy bot is in the vertex `C`. We will fire a bullet with angle `A` to hit the bot in vertex `B`. We know the value of `b` (it is the distance `D` from your bot to the enemy). We don't know `c`, but we know that it will be the distance traveled by the bullet. Also, we know that `a` will be the distance traveled by the enemy bot. If we put `a`, `b`, and `c` as a function of time, we have:

```
b = D
c = Vb * t (Vb is the bullet speed)
a = Vr * t (Vr is the enemy bot velocity)
```

Now, using the Law of sines:

```
   a/sin(A) = c/sin(C) 
-> Vr*t / sin(A) = Vb*t / sin(C) 
-> sin(A) = Vr/Vb * sin(C) 
-> A = asin(Vr/Vb * sin(C))
```

We don't know the value of `C`, but we can take the worst scenario where `C = PI/2` (`sin(C) = 1`) to get a Maximum Escape Angle of `A = asin(Vr/Vb * 1) = asin (Vr/Vb)`.

With a maximum Robot velocity of 8.0, a theoretical Maximum Escape Angle would be `asin(8.0/Vb)`. Note that the actual maximum depends on the enemy's current heading, speed, and [Wall Distance](https://robowiki.net/w/index.php?title=Wall_Distance&action=edit&redlink=1).

#### Dos opciones:

##### El Pro: Pattern Matching

A form of [log-based targeting](https://robowiki.net/wiki/Log-based_targeting) that tries to match the most recent series of enemy movements against a history of previous enemy movements. The most common form finds the closest match, then uses the movements immediately after that match to predict the enemy's future movements; other variations exist, as well.

###### How it works

There are many different pattern matching algorithms, but here is the pseudocode for a typical one, derived from David Mold's [MogBot](https://robowiki.net/wiki/MogBot) algorithm:

- Keep a log of enemy movements that stores the enemy's velocity and heading change each tick.
- When aiming, take the last 7 enemy movements and use this as your pattern (let's call it `p`, made up of `p[0], p[1], ... , p[6]`).
- Search through your history of enemy movements and find the series of 7 ticks that most closely matches p. For each position x in your search history:
  - Measure the difference between `x` and `p[0]`, `x + 1` and `p[1]`, up through `x + 6` and `p[6]`. This measurement could simply be `abs(velocity1 - velocity2) + abs(turnRate1 - turnRate2)` for each individual tick.
  - Sum these differences - the lower the result, the closer the match. Call this sum `s`.
  - Remember the lowest value of `s` and its position in the log.
- Once you have found the closest match, imagine that the enemy will move with the exact same series of movements that he did last time. Note the enemy's current position and heading, then iterate through the movements immediately after the match we found.
  - For each successive movement frame, move the enemy's position ahead by the past frame's velocity and then turn the enemy's heading by the past frame's heading.
  - Figure out how long it will take your bullet to get there and repeat the prediction that many times, then fire at that spot.

##### El no pro: Circular Targeting

A method of [targeting](https://robowiki.net/wiki/Targeting) which assumes that the target will continue moving with the same turn rate and at the same speed.

###### Esta el codigo entero de este: https://robowiki.net/wiki/Circular_Targeting



#### wave surfing / como hacer para que no te emboquen

A form of precise bullet dodging movement used primarily in [1v1](https://robowiki.net/wiki/One_on_One). A [Wave](https://robowiki.net/wiki/Waves) is a mechanic used to represent all possible locations of a bullet. Through observation of waves and bullets, one can try to project the relative dangers of each area of a wave in the air -- i.e., the likelihood that the enemy fired at various angles. (Note that Robocode bots cannot see bullets in the air.)

###### How it works

- Detect an [energy drop](https://robowiki.net/wiki/Energy_Drop) to know that a bullet was fired. Create a matching Wave.
- Gather data from `onHitByBullet` or `onBulletHitBullet`, always matching to the correct Wave, to learn what firing angles the enemy gun uses in different situations.
- For the nearest bullet(s) in the air, use [precise prediction](https://robowiki.net/wiki/Precise_Prediction) to deduce the areas of the wave(s) your bot could reach.
- Try to move to the safest reachable spot on each wave.

