package Boids;

import java.util.*;

public class CBoidEye {
	
	private CBoidScene theWorldISee = null;
	private CBoidObject theBoid = null;
	private double MAX_SEEING_DISTANCE = 0;
	
	public CBoidEye(CBoidObject me, CBoidScene scene)
	{	
		this.theWorldISee = scene;
		this.theBoid = me;
		
		// Not every boid has the same vision
		this.MAX_SEEING_DISTANCE = 10 + Math.random() * 4; 
	}
	
	public Vector<CSceneObject> perceive()
	{
		// The boid that sees all (starting simple for now)
		
		Vector<CSceneObject> boidsInTheWorld = theWorldISee.getSceneObjects();
		Vector<CSceneObject> boidsISee = new Vector<CSceneObject>();
		
		if (boidsInTheWorld.size() != 0)
		{
			boidsInTheWorld.firstElement();
			
			Iterator<CSceneObject> it = boidsInTheWorld.iterator();
			
			while(it.hasNext())
			{
				CBoidObject obj = (CBoidObject)it.next();
				
				// Do not see your self
				if (obj != theBoid)
				{
					if (theBoid.getLocationInScene().distance(obj.getLocationInScene()) < MAX_SEEING_DISTANCE)
					{
						boidsISee.add(obj);
					}
				}
			}
		}
		
		return boidsISee;
	}
}
