package Boids;

import java.util.Vector;
import java.util.Iterator;

import Rules.*;
import VectorMath.*;

import Rules.CBoidNavigatorRule;

public class CBoidNavigator {

	private Vector<CBoidNavigatorRule> navigationRules = new Vector<CBoidNavigatorRule>();
	private CBoidEye eye = null; // Responsible for creating a list of
									// visible objects in the boid's world.

	private int countForFun = 0;
	private boolean switsj = false;
	
	public CBoidNavigator(CBoidEye boidEye) {
		this.eye = boidEye;
		navigationRules.add(new CBoidNavigatorRuleFlockCenter());
	}

	public Vector3D calculate(CBoidObject boid) {
		// Initialize all navigation rules to start values
		CBoidNavigatorRuleAvoidCollision rule1 = new CBoidNavigatorRuleAvoidCollision();
		CBoidNavigatorRuleKeepDistance rule2 = new CBoidNavigatorRuleKeepDistance();
		CBoidNavigatorRuleFlockCenter rule3 = new CBoidNavigatorRuleFlockCenter();
		CBoidNavigatorRuleAllignVelocity rule4 = new CBoidNavigatorRuleAllignVelocity();

		rule1.init(boid, 0.5);
		rule2.init(boid, 1.0); 
		rule3.init(boid, 1.0);
		rule4.init(boid, 0.7); 

		Vector3D desiredDirection = new Vector3D();

		Vector<CSceneObject> interactingObjects = eye.perceive();

		//System.out.print(interactingObjects.size()+"\n");
		
		if (interactingObjects.size() != 0) {
			Iterator<CSceneObject> it = interactingObjects.iterator();

			while (it.hasNext()) {
				CBoidObject subject = (CBoidObject) it.next();

				// evaluate specific object out of scene against this
				// boid, by scaling evaluation for each available rule				
				rule1.evaluate(subject);
				rule2.evaluate(subject);
				rule3.evaluate(subject);
				rule4.evaluate(subject);
			}

			double MAX_ACCEL = 120.0;
			// Get resulting forces from each rule and calculate resulting
			// vector
			
			desiredDirection = rule1.result();
			
			if (Double.compare(desiredDirection.length(),MAX_ACCEL) < 0)
			{
				desiredDirection = desiredDirection.add(rule2.result());
			}
			
			if (Double.compare(desiredDirection.length(),MAX_ACCEL) < 0)
			{
				desiredDirection = desiredDirection.add(rule3.result());
			}
			
			if (Double.compare(desiredDirection.length(),MAX_ACCEL) < 0)
			{
				desiredDirection = desiredDirection.add(rule4.result());				
			}
			
			//System.out.print(desiredDirection.length());
			
			//desiredDirection.print();
			// force to middle
			countForFun++;
			Vector3D dir = new Vector3D();
			if (countForFun % 150 == 0)
			{
				if (switsj) { switsj = false; } else {switsj = true;}				
			}
			
			if (switsj)
			{
				dir = boid.getLocationInScene().vector(new Point3D(-8*Math.random(),
						-8*Math.random(),
						-8*Math.random()));
			}
			else
			{
				//dir = boid.getLocationInScene().vector(new Point3D(8,8,8));
				dir = new Vector3D(0,0,0);
			}
			
			//dir.print();
			desiredDirection = desiredDirection.add(dir);
			
		} else {
			desiredDirection = new Vector3D(0, 0, 0);
		}
		
//		if (desiredDirection.length() > 1.0)
//		{
//			desiredDirection = desiredDirection.normalize();
//		}

		return desiredDirection;
	}
}
