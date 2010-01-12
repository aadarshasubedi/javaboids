package Rules;

import VectorMath.*;
import Boids.*;
import Interface.*;

public class CBoidNavigatorRuleFlockCenter extends CBoidNavigatorRule 
	implements IBoidNavigatorRule 
{   
	private double mWeightFactor = 0.0;
	private Point3D mFlockCentroid = new Point3D(0.0f, 0.0f, 0.0f); // Contains body of mass of evaluated flock
	private int mEvaluatedNrOfBoids = 0;
	
	public void	init(CBoidObject actor, double weightFactor) 
	{
		mWeightFactor = weightFactor;
		mEvaluatedNrOfBoids  = 0;
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
		// Distance influences strength (length) of vector
		//mFlockCentroid = mFlockCentroid.divide(mEvaluatedNrOfBoids);
		
		return new Vector3D();
	};
}
