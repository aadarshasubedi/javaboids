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

	public CBoidNavigator(CBoidEye boidEye) {
		this.eye = boidEye;
		navigationRules.add(new CBoidNavigatorRuleFlockCenter());
	}

	public Vector3D calculate(CBoidObject boid) {
		// Initialize all navigation rules to start values
		CBoidNavigatorRuleFlockCenter rule1 = new CBoidNavigatorRuleFlockCenter();
		CBoidNavigatorRuleKeepDistance rule2 = new CBoidNavigatorRuleKeepDistance();
		//CBoidNavigatorRuleAvoidCollision rule3 = new CBoidNavigatorRuleAvoidCollision();

		rule1.init(boid, 1.0);
		rule2.init(boid, 1.0);
		//rule3.init(boid, 1.0);

		Vector3D desiredDirection = null;

		Vector<CSceneObject> interactingObjects = eye.perceive();

		if (interactingObjects.size() != 0) {
			Iterator<CSceneObject> it = interactingObjects.iterator();

			while (it.hasNext()) {
				CBoidObject subject = (CBoidObject) it.next();

				// evaluate specific object out of scene against this
				// boid, by scaling evaluation for each available rule				
				rule1.evaluate(subject);
				rule2.evaluate(subject);
				//rule3.evaluate(subject);
			}

			// Get resulting forces from each rule and calculate resulting
			// vector
			desiredDirection = rule1.result().add(rule2.result());//.add(rule3.result());
		} else {
			desiredDirection = new Vector3D(0, 0, 0);
		}
		
		if (desiredDirection.length() > 1.0)
		{
			desiredDirection = desiredDirection.normalize();
		}

		return desiredDirection;
	}
}
