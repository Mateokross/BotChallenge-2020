
package teamrocket;

import robocode.*;


//creamos una clase para guardar todas las propiedades que puede tener el enemigo
public class enemigo {
		private double bearing, distancia, energia, direccion, velocidad;
		private String nombre;
		
		public enemigo(){
			reset();}
			
		public void reset(){
			this.nombre="";
			this.bearing=0.0;
			this.distancia=0.0;
			this.energia=0.0;
			this.direccion=0.0;
			this.velocidad=0.0;
}
		public double getBearing(){return this.bearing;}
		public double getDistance(){return this.distancia;}
		public double getEnergy(){return this.energia;}
		public double getHeading(){return this.direccion;}
		public double getVelocity(){return this.velocidad;}
		public String getName() {return this.nombre;}

		public void update(ScannedRobotEvent e){
			this.bearing = e.getBearing();
			this.distancia = e.getDistance();
			this.energia = e.getEnergy();
			this.direccion = e.getHeading();
			this.velocidad = e.getVelocity();
			this.nombre = e.getName();
		}
		
		public boolean none(){
			return this.nombre.equals("");}

}

