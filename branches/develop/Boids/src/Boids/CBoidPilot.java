package Boids;

import VectorMath.*;

public class CBoidPilot {
	
	public Point3D evaluate(CBoidObject boid, double deltaTime)
	{
		Vector3D desiredDirection = boid.getRequestedAccel();
		
		Point3D newPos = boid.getLocationInScene();		
		newPos = newPos.add(new Point3D(desiredDirection.getX(), desiredDirection.getY(), desiredDirection.getZ()));
		
		return newPos;
	};
}
