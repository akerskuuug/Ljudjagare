package se.chalmers.proofofconceptlj;

public class Human {
	private static double distanceBetweenEars = 0.01;
	
	private Vector2 position;
	private double  rotation;					//in radians from pos x-axis
	
	
	public Human(Vector2 position, double rotation) {
		this.position = position;
		this.rotation = rotation;
	}

	public Human() {
		this(Vector2.zero(), 0);
	}
	
	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public Vector2 getLeftEarPos() {
		double h 	= distanceBetweenEars/2;
		double dx 	= h * Math.cos(rotation);
		double dy 	= h * Math.sin(rotation);
		double x 	= this.position.getX() - dx;
		double y 	= this.position.getY() - dy;
		
		return new Vector2(x,y);
		
	}
	
	public Vector2 getRightEarPos() {
		double h 	= distanceBetweenEars/2;
		double dx 	= h * Math.cos(rotation);
		double dy 	= h * Math.sin(rotation);
		double x 	= this.position.getX() + dx;
		double y 	= this.position.getY() + dy;
		
		return new Vector2(x,y);
	}
}