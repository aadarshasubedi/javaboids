package Boids;

import VectorMath.Point3D;
import VectorMath.Vector3D;
import Interface.ISceneObject;

public class CSceneObject implements ISceneObject  {

	@Override
	public int getColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector3D getDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point3D getLocationInScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void update() {};
}
