package Boids;

import VectorMath.*;

public class CBoidPilot {
	
	public Point3D evaluate(CBoidObject boid, double deltaTime)
	{
		Vector3D desiredDirection = new Vector3D(boid.getRequestedAccel());
		//System.out.print("Navigator request: "); desiredDirection.print();
		
		Vector3D velocityVect = new Vector3D(boid.getVelocity());
		System.out.print("Actual velocity: "); velocityVect.print();
		
		// Calculated displacement based on d = v*t + 0.5at^2
		Vector3D accelPart = desiredDirection.scalarMultiply(0.5*deltaTime*deltaTime);
		Vector3D displacement = velocityVect.scalarMultiply(deltaTime).add(accelPart);
		
		// Calculate new velocity v = v + at
		velocityVect = velocityVect.add(desiredDirection.scalarMultiply(deltaTime));
		
		double maxSpeed = 3.0;
		if (velocityVect.length() > maxSpeed)
		{
			velocityVect = velocityVect.normalize().scalarMultiply(maxSpeed);
		}
		System.out.print("New velocity: "); velocityVect.print();
		
		boid.setVelocity(velocityVect);
				
		Point3D newPos = boid.getLocationInScene();		
		newPos = newPos.add(new Point3D(displacement.getX(),displacement.getY(),displacement.getZ()));
		System.out.print("New position: "); newPos.print();
		
		return newPos;
	};
}
