package Rules;

import VectorMath.Vector3D;
import Boids.CBoidObject;
import Interface.IBoidNavigatorRule;

public class CBoidNavigatorRuleKeepDistance extends CBoidNavigatorRule  {

	private CBoidObject mBoid = null;
	private double mWeightFactor = 0;
	private int mEvaluatedNrOfBoids = 0;
	private Vector3D mResultVector = new Vector3D();
	
	@Override
	public void evaluate(CBoidObject object) {
		
		if (object != mBoid)
		{
			mEvaluatedNrOfBoids++;

			double distance = mBoid.getLocationInScene().distance(object.getLocationInScene());

			if (distance < 50.0)
			{	
				Vector3D oppositeVector = 
					mBoid.getLocationInScene().vector(object.getLocationInScene()).scalarMultiply(-1.0);

				oppositeVector = oppositeVector.scalarMultiply(1/(distance*distance));

				mResultVector.add(oppositeVector);
			}
		}
	}

	@Override
	public void init(CBoidObject actor, double weightFactor) {
		
		super.init(actor, weightFactor);
		
		mBoid = actor;
		mWeightFactor = weightFactor;
		mEvaluatedNrOfBoids = 0;
	}

	@Override
	public Vector3D result() {		
		return mResultVector.scalarMultiply(mWeightFactor);
	}

}
