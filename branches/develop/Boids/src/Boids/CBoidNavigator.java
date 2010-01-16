package Boids;

import java.util.Vector;
import java.util.Iterator;

import Rules.*;
import VectorMath.*;

import Rules.CBoidNavigatorRule;

public class CBoidNavigator {
	
	private Vector<CBoidNavigatorRule> navigationRules = new Vector<CBoidNavigatorRule>();
	private CBoidEye eye = null;		// Responsible for creating a list of visible objects in the boid's world.
	
	public CBoidNavigator(CBoidEye boidEye)
	{
		this.eye = boidEye;
		navigationRules.add(new CBoidNavigatorRuleFlockCenter());
	}
	
	public Vector3D calculate(CBoidObject boid)
	{
		// Initialize all navigation rules to start values
		
		CBoidNavigatorRuleFlockCenter rule1 = new CBoidNavigatorRuleFlockCenter();
		//CBoidNavigatorRuleKeepDistance rule2 = new CBoidNavigatorRuleKeepDistance();
		
		rule1.init(boid, 1.0);
		//rule2.init(boid, 1.0);
		
		Vector3D desiredDirection = null;
		
		Vector<CSceneObject> interactingObjects = eye.perceive();
		
		if (interactingObjects.size() != 0)
		{
			Iterator<CSceneObject> it = interactingObjects.iterator();
			
			while (it.hasNext())
			{
				CBoidObject subject = (CBoidObject)it.next();
				
				// evaluate specific object out of scene against this
				// boid, by scaling evaluation for each available rule
				rule1.evaluate(subject);
			//	rule2.evaluate(subject);
			}

			double x = 10;
			double y = -10;
			double z = -16;
			
			double rnd = (Math.random()-1)*2;
			Vector3D desireToGoToSceneCenter = boid.getLocationInScene().vector(
					new Point3D(x, y, z));
			
			Vector3D rule = new Vector3D();//rule1.result();
			//desiredDirection = rule.add(rule2.result());//.add(desireToGoToSceneCenter);
			desiredDirection = rule1.result();
			
			System.out.print("(" + desiredDirection.getX() + ","
					+ desiredDirection.getY() + ","
					+ desiredDirection.getZ() + ")\n");
			
			// A boid has a max velocity to get somewhere, is limited here (should go to pilot module)
			double maxVelocit = 0.5; 
			if (desiredDirection.length() > maxVelocit)
			{
				desiredDirection = desiredDirection.normalize().scalarMultiply(maxVelocit);
			}
		}
		else
		{
			desiredDirection = new Vector3D(0,0,0);
		}
		
		// Get resulting forces from each rule and calculate resulting
		// vector
		
		return desiredDirection;
	}
}
