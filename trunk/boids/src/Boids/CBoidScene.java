package Boids;

import java.util.Vector;

import VectorMath.Point3D;

public class CBoidScene {
	
	private Vector<CSceneObject> allObjectsInScene= new Vector<CSceneObject>();
	
	public Vector<CSceneObject> getSceneObjects() { return allObjectsInScene; }
	
	public CBoidScene() {
		super();
	
		allObjectsInScene.add(new CBoidObject());
		allObjectsInScene.add(new CBoidObject(new Point3D(-5.0f,0.5f,-6.0f)));
	}
}
