# Eventos

| Evento            | Que pasó                    | Comentarios                    | Metodos                       |
| ----------------- | --------------------------- | ------------------------------ | ----------------------------- |
| onStatus          | pedis el status de tu robot | es clave pedirlo en cada ronda |                               |
| onWin             | ganaste                     | baile copado                   |                               |
| onCustomEvent     | vos pones las condiciones   | este es de advanced            |                               |
| onBulletHit       | tu bala le dio a otro robot |                                | *event*.getBullet().getName() |
| onBulletHitBullet | tu bala le dio a otra bala  |                                |                               |
| onBulletMissed    | pifieaste la bala           |                                |                               |
| onDeath           | moriste                     | decir f en el chat             |                               |
| onHitByBullet     | te dieron una bala          |                                | *event*.getRobotName()        |
| onHitRobot        | chocaste un robot           |                                | isMyFault()                   |
| onHitWall         | chocaste la wall            |                                | getBearing()                  |
| onRobotDeath      | murio un robot              |                                |                               |
| onScannedRobot    | scaneate a otro robot       | este es clave para el radar    | *event*.getDistance()         |

