package Boids;

import java.util.Vector;
import java.util.Iterator;

import VectorMath.Point3D;

public class CBoidScene {
	
	private Vector<CSceneObject> allObjectsInScene= new Vector<CSceneObject>();
	
	public Vector<CSceneObject> getSceneObjects() { return allObjectsInScene; }
	
	public CBoidScene() {
		super();
	
		allObjectsInScene.add(new CBoidObject(this));
		allObjectsInScene.add(new CBoidObject(this, new Point3D(-5.0f,0.5f,-6.0f)));
	}
	
	public void update()
	{
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
