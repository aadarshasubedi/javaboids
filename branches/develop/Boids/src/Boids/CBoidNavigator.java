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
		Vector<CSceneObject> interactingObjects = eye.perceive();
		
		if (interactingObjects.size() != 0)
		{
			Iterator<CSceneObject> it = interactingObjects.iterator();
			
			while (it.hasNext())
			{
				CBoidObject subject = (CBoidObject)it.next();
				subject.getLocationInScene();
			}
		}
		
		return new Vector3D();
	}
}
