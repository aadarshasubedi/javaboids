package Boids;

import java.util.Vector;
import java.util.Iterator;

import VectorMath.Point3D;

public class CBoidScene {
	
	private Vector<CSceneObject> allObjectsInScene= new Vector<CSceneObject>();
	
	public Vector<CSceneObject> getSceneObjects() { return allObjectsInScene; }
	
	public CBoidScene() {
		super();
	
		allObjectsInScene.add(new CBoidObject(this, new Point3D(-2,0,2)));
		allObjectsInScene.add(new CBoidObject(this, new Point3D(2,0,2)));
		allObjectsInScene.add(new CBoidObject(this, new Point3D(0,-2,0)));
		allObjectsInScene.add(new CBoidObject(this, new Point3D(0,2,0)));
		allObjectsInScene.add(new CBoidObject(this, new Point3D(0,1,0)));
	}
	
	public void update()
	{
		// Has to be done in two steps, results in very strange behaviors
		// of the rules when directly applying results of rule evaluation 
		// to new position of boid. 
		if (allObjectsInScene.size() != 0)
		{
			Iterator<CSceneObject> it = allObjectsInScene.iterator();
			
			while (it.hasNext())
			{
				it.next().calculate();
			}
		}
		
		if (allObjectsInScene.size() != 0)
		{
			Iterator<CSceneObject> it = allObjectsInScene.iterator();
			
			while (it.hasNext())
			{
				it.next().update();
			}
		}
	}
}
