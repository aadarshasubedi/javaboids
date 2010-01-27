package Rules;

import VectorMath.Point3D;
import VectorMath.Vector3D;
import Boids.CBoidObject;
import Interface.IBoidNavigatorRule;

public class CBoidNavigatorRuleAllignVelocity extends CBoidNavigatorRule
		implements IBoidNavigatorRule 
{
	private double mWeightFactor = 0.0;	
	private int mEvaluatedNrOfBoids = 0;
	private CBoidObject boid = null;
	
	private Vector3D totalAllignment = new Vector3D();
	
	public void evaluate(CBoidObject object) 
	{
		// The goal is to have all boids moving in the same direction
		// giving a nice smooth movement.
		if (mEvaluatedNrOfBoids < 2)
		{
			mEvaluatedNrOfBoids++;
			//totalAllignment = totalAllignment.add(object.getVelocity().normalize());
			totalAllignment = totalAllignment.add(object.getVelocity());
		}
	}

	@Override
	public void init(CBoidObject actor, double  weightFactor) 
	{
		mWeightFactor = weightFactor;
		boid = actor;
		
		totalAllignment = actor.getVelocity();
	}

	@Override
	public Vector3D result() {

		Vector3D result = new Vector3D();
		result = totalAllignment;
		if (mEvaluatedNrOfBoids>0)
		{
			result = totalAllignment.Scalardivide(mEvaluatedNrOfBoids);
		}
		
		return result.scalarMultiply(mWeightFactor);
	}
}
