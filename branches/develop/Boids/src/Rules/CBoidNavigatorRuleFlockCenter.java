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
		mEvaluatedNrOfBoids++;
		mFlockCentroid = mFlockCentroid.add(object.getLocationInScene());
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
		
		Vector3D forceVector = new Vector3D();
		
		if (distance > 0.1)
		{	
			// a value of 1.0 would bring all boids in one step together,
			// this is not desired so take a percentage of total displacement
			forceVector = direction.scalarMultiply(0.05); 			
		}
		
		return forceVector.scalarMultiply(1.0);
	};
}
