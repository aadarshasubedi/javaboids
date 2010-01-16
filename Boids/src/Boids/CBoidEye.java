package Boids;

import java.util.*;

public class CBoidEye {
	
	private CBoidScene theWorldISee = null;
	private CBoidObject theBoid = null;
	
	public CBoidEye(CBoidObject me, CBoidScene scene)
	{	
		this.theWorldISee = scene;
		this.theBoid = me;
	}
	
	public Vector<CSceneObject> perceive()
	{
		// The boid that sees all (starting simple for now)
		// TODO: Create a sphere with the boid's position as center. Then check which objects
		//       are in the sphere (the sight range of the boid)
		
		double MAX_SEEING_DISTANCE = 8;
		
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
