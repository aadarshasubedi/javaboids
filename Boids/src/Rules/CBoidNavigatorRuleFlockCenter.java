package Rules;

import VectorMath.*;
import Boids.*;
import Interface.*;

public class CBoidNavigatorRuleFlockCenter extends CBoidNavigatorRule 
	implements IBoidNavigatorRule 
{   
	private double mWeightFactor = 0.0;
	private Point3D mFlockCentroid = new Point3D(0, 0, 0); // Contains body of mass of evaluated flock
	private int mEvaluatedNrOfBoids = 0;
	private CBoidObject boid = null;
	
	public void	init(CBoidObject actor, double weightFactor) 
	{
		mWeightFactor = weightFactor;
		mEvaluatedNrOfBoids  = 0;
		boid = actor;
		
		mFlockCentroid.reset();		
	};
	
	public void evaluate(CBoidObject object)
	{
		if (boid != object)
		{
			mEvaluatedNrOfBoids++;
			mFlockCentroid = mFlockCentroid.add(object.getLocationInScene());
		}
	}
	
	public Vector3D result() 
	{
		// Return vector pointing to flock center
		// Distance influences strength directly. Boid wants to get quicker to
		// center when more far away. This is reflected in the displacement
		// vector which is larger when the boid is more far away.
		mFlockCentroid = mFlockCentroid.divide(mEvaluatedNrOfBoids);
		
		Point3D boidPos = boid.getLocationInScene();
		
		//Vector3D direction = boidPos.vector(mFlockCentroid).normalize();
		Vector3D direction = boidPos.vector(mFlockCentroid);
		double distance = boidPos.distance(mFlockCentroid);
		
		Vector3D forceVector = direction;
		
		// Only when distance larger then a certain threshold
		// Flock becomes unstable when distance very small it will shoot out of screen
		// but with a nice boundary it could give a nice effect (distance = 0)
		if (distance > 0.3)
		{	
			// a value of 1.0 would bring all boids in one step together,
			// this is not desired so take a percentage of total displacement
			
			// When the birds are very far away, the urge to go to the group 
			// might be smaller (they cannot see them) than the resulting velocity 
			// factor might indicate. (distance 100 makes a very large velocity vector,
			// but can the boid actually see those members?)
			forceVector = direction.scalarMultiply(1/(distance*distance));
			
			// A boid has a max velocity to get somewhere, is limited here (should go to pilot module)
			double maxVelocit = 1.5; 
			if (forceVector.length() > maxVelocit)
			{
				forceVector = forceVector.normalize().scalarMultiply(maxVelocit);
			}
		}
		else
		{
			// Do not move close than 0.5 distance
			forceVector = new Vector3D();
		}
		
		return forceVector.scalarMultiply(mWeightFactor);
	};
}
