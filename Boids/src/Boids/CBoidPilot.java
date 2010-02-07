package Boids;

import VectorMath.*;

public class CBoidPilot {
	
	private double MAX_ACCEL_SPEED = 120.0;
	private double MAX_SPEED = 10.0;
	
	public Point3D evaluate(CBoidObject boid, double deltaTime)
	{
		Vector3D desiredDirection = new Vector3D(boid.getRequestedAccel());
		//System.out.print("Navigator request: "); desiredDirection.print();
		
		Vector3D velocityVect = new Vector3D(boid.getVelocity());
		//System.out.print("Actual velocity: "); velocityVect.print();
		
		Vector3D stayInBoundary = StayInMovingSpace(boid.getLocationInScene());
		desiredDirection = desiredDirection.add(stayInBoundary).normalize().scalarMultiply(MAX_ACCEL_SPEED);
		
		// Calculated displacement based on d = v*t + 0.5at^2
		Vector3D accelPart = desiredDirection.scalarMultiply(0.5*deltaTime*deltaTime);
		Vector3D displacement = velocityVect.scalarMultiply(deltaTime).add(accelPart);
		
		// Calculate new velocity v = v + at
		velocityVect = velocityVect.add(desiredDirection.scalarMultiply(deltaTime));
		
		double maxSpeed = MAX_SPEED;
		if (velocityVect.length() > maxSpeed)
		{
			velocityVect = velocityVect.normalize().scalarMultiply(maxSpeed);
		}
		//System.out.print("New velocity: "); velocityVect.print();
		
		boid.setVelocity(velocityVect);
				
		Point3D newPos = boid.getLocationInScene();		
		newPos = newPos.add(new Point3D(displacement.getX(),displacement.getY(),displacement.getZ()));
		//System.out.print("New position: "); newPos.print();
		
		return newPos;
	};
	
	private Vector3D StayInMovingSpace(Point3D testPos)
	{
		// Check if the current location of the boid is not reaching one
		// of the boundary planes of moving space
		// When position is already outside, do nothing and assume this is an entering boid
		// Other wise test and return 'accelerate away' vector if necessary
		
		double backplaneZ 	= 10;
		double fronplaneZ 	= 0; //-2
		double leftPlaneX 	= -20;
		double rightPlaneX 	= 20;
		double topPlaneY 	= 20;
		double bottomplaneY = -20;
		
		double posX = testPos.getX();
		double posY = testPos.getY();
		double posZ = testPos.getZ();
		
		Vector3D moveAway = new Vector3D();
		//testPos.print();
		
		if (Double.compare(backplaneZ, posZ) < 0)
		{	
			moveAway.setZ(-1);
		}
		
		if (Double.compare(fronplaneZ, posZ) > 0)
		{
			moveAway.setZ(1);
		}
		
		if (Double.compare(rightPlaneX, posX) < 0)
		{
			moveAway.setX(-1);
		}
		
		if (Double.compare(leftPlaneX, posX) > 0)
		{
			moveAway.setX(1);
		}
		
		if (Double.compare(topPlaneY, posY) < 0)
		{
			moveAway.setY(-1);
		}
		
		if (Double.compare(bottomplaneY, posY) > 0)
		{
			moveAway.setY(1);
		}
		
		//moveAway.print();
		return moveAway;
		
	}
}
