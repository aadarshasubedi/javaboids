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
		Vector3D forceVector = new Vector3D(0,0,0);
		
		if (mEvaluatedNrOfBoids > 0)
		{
		// Return vector pointing to flock center
		// Distance influences strength directly. Boid wants to get quicker to
		// center when more far away. This is reflected in the displacement
		// vector which is larger when the boid is more far away.
		mFlockCentroid = mFlockCentroid.divide(mEvaluatedNrOfBoids);
		
		Point3D boidPos = boid.getLocationInScene();
		
		//Vector3D direction = boidPos.vector(mFlockCentroid).normalize();
		Vector3D direction = new Vector3D(boidPos.vector(mFlockCentroid));
		double distance = boidPos.distance(mFlockCentroid);
		
	//	if (distance > 5.0)
		{
			forceVector = direction;
			forceVector = direction.scalarMultiply(0.2);
		}
		
		//System.out.print("Going to Center: "); forceVector.print();
		}
		
		return forceVector.scalarMultiply(mWeightFactor);
	};
}
