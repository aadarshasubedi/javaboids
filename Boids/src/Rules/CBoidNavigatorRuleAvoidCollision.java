package Rules;

import VectorMath.Point3D;
import VectorMath.Vector3D;
import Boids.CBoidObject;
import Interface.IBoidNavigatorRule;

public class CBoidNavigatorRuleAvoidCollision extends CBoidNavigatorRule 
	implements IBoidNavigatorRule
{
	CBoidObject boid;
	double mWeightFactor;
	int mEvaluatedNrOfBoids;
	
	Vector3D requestVector = new Vector3D(0,0,0);
	
	public void	init(CBoidObject actor, double weightFactor) 
	{
		mWeightFactor = weightFactor;
		mEvaluatedNrOfBoids  = 0;
		boid = actor;	
	};
	
	public void evaluate(CBoidObject object)
	{
		if (boid != object)
		{
			mEvaluatedNrOfBoids++;
			
			// If object to evaluate is a boid, check if we are on a collision course
			{
				// Check if point of object is on heading line of this boid
				Vector3D headingVector = boid.getHeading().normalize();
				
				//System.out.print("heading ="); headingVector.print();
				
				//Vector3D vectorToOtherBoid = boid.getLocationInScene().vector(object.getLocationInScene()).normalize();
				Vector3D vectorToOtherBoid = boid.getLocationInScene().vector(object.getLocationInScene()).normalize();
				
				//System.out.print("heading boid 2 ="); vectorToOtherBoid.print();
				
				double dotProduct = vectorToOtherBoid.dotProduct(headingVector);
				//System.out.print("dotProduct = " + dotProduct + "\n");//				
				//System.out.print("Angle = " + Math.acos(dotProduct) + "\n");
				
				
				// if Point on line, check if angle between two heading vectors is 180 degree
				// if this is the case, boids are approaching eachother on same path
				// steer away a little bit to right perpendicular to moving direction
				//if (dotProduct == 1 || dotProduct == -1)
				if (compare(dotProduct, 1,0.05) || compare(dotProduct, -1,0.05) )
				{
					// point boid evaluated on heading course myBoid
					// is heading of boid evaluated heading in opposite direction myBoid?
					Point3D pntEvalBoidHeadingTo = object.getLocationInScene().add(new Point3D(0,0,0));
					//pntEvalBoidHeadingTo.print();
					double dotProduct2 = headingVector.dotProduct(boid.getLocationInScene().vector(pntEvalBoidHeadingTo).normalize());
					//System.out.print(dotProduct2);
					
					if (compare(dotProduct2, 1, 0.05) || compare(dotProduct2, -1, 0.05) )
					{
					//	System.out.print("add vector...\n");
						Vector3D vecSecond = new Vector3D(1,1,1);
						Vector3D perpendicular = headingVector.crossProduct(vecSecond);
						
						//perpendicular.print();
						requestVector = requestVector.add(perpendicular);
					}
				}
			}
			
			
			
			// if object is a static object, do something else (move along object to left or right)
			
			
		}
	}
	
	private boolean compare(double a, double b, double precision)
	{
		boolean ret = false;
		
		if ((b-a)*(b-a) > (precision*precision))
		{
			ret = false;
		}
		else
		{
			ret = true;
		}
		
		return ret;
	}
	
	public Vector3D result() 
	{		
		if (mEvaluatedNrOfBoids > 0)
		{
		
		}
		
		return requestVector.scalarMultiply(mWeightFactor);
	};

}
