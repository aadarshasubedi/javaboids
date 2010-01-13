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
		
		CBoidNavigatorRuleFlockCenter rule = new CBoidNavigatorRuleFlockCenter();
		rule.init(boid, 0.5);
		Vector3D centeroftheworld = null;
		
		Vector<CSceneObject> interactingObjects = eye.perceive();
		
		if (interactingObjects.size() != 0)
		{
			Iterator<CSceneObject> it = interactingObjects.iterator();
			
			while (it.hasNext())
			{
				CBoidObject subject = (CBoidObject)it.next();
				
				// evaluate specific object out of scene against this
				// boid, by scaling evaluation for each available rule
				rule.evaluate(subject);
			}
			
			centeroftheworld = rule.result();
		}
		
		// Get resulting forces from each rule and calculate resulting
		// vector
		
		return centeroftheworld;
	}
}
