package Boids;

import java.util.*;

public class CBoidEye {
	
	private CBoidScene theWorldISee = null;
	
	public CBoidEye(CBoidScene scene)
	{	
		this.theWorldISee = scene;
	}
	
	public Vector<CSceneObject> perceive()
	{
		// The boid that sees all (starting simple for now)
		// TODO: Create a sphere with the boid's position as center. Then check which objects
		//       are in the sphere (the sight range of the boid)
		return theWorldISee.getSceneObjects();
	}
}
