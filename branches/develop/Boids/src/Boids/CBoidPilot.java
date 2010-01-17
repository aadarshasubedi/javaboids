package Boids;

import VectorMath.*;

public class CBoidPilot {
	
	public Point3D evaluate(CBoidObject boid, double deltaTime)
	{
		Vector3D desiredDirection = new Vector3D(boid.getRequestedAccel());
		
		Vector3D velocityVect = new Vector3D(boid.getVelocity());
		
		System.out.print(" desDir = (" + desiredDirection.getX() + ","
				+ desiredDirection.getY() + ","
				+ desiredDirection.getZ() + ")\n");
		
		// Calculated displacement based on d = v*t + 0.5at^2
		Vector3D acceleration = desiredDirection.scalarMultiply(0.5*deltaTime*deltaTime);
		Vector3D displacement = velocityVect.scalarMultiply(deltaTime).add(acceleration);
		
		// Calculate new velocity v = v + at
		velocityVect = velocityVect.add(desiredDirection.scalarMultiply(deltaTime));
		
		double maxSpeed = 10.0;
		if (velocityVect.length() > maxSpeed)
		{
			velocityVect = velocityVect.normalize().scalarMultiply(maxSpeed);
		}
		
		System.out.print(" newVelocity = (" + velocityVect.getX() + ","
				+ velocityVect.getY() + ","
				+ velocityVect.getZ() + ")\n");
				
		System.out.print("deltaT =" + deltaTime + " disp = (" + displacement.getX() + ","
				+ displacement.getY() + ","
				+ displacement.getZ() + ")\n");
				
		Point3D newPos = boid.getLocationInScene();		
		newPos = newPos.add(new Point3D(displacement.getX(),displacement.getY(),displacement.getZ()));
			
		
		System.out.print("deltaT =" + deltaTime + " newPos+disp = (" + newPos.getX() + ","
				+ newPos.getY() + ","
				+ newPos.getZ() + ")\n");
		
		return newPos;
	};
}
