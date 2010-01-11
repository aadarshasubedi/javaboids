package Boids;

import VectorMath.Point3D;
import VectorMath.Vector3D;
import Interface.ISceneObject;

public class CBoidObject extends CSceneObject implements ISceneObject {

	private float mSpeed = 0.0f;
	private float mRoll  = 15.0f;

	private Point3D position = new Point3D(0.0f, 0.0f, 0.0f);
	
	public CBoidObject()
	{		
	}
	
	public CBoidObject(Point3D startPos)
	{
		position = startPos;
	}
	
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
		
		position.setX(position.getX() + 0.05f);
		position.setY(position.getY() + 0.01f);
		
		return position;
	}

	@Override
	public float getSpeed() {
		return mSpeed;
	}

	public void setSpeed(float speed) {
		mSpeed = speed;
	}

	public float getPitch() {
		return 0.0f;
	}

	public float getRoll() {
		
		if (mRoll == 15.0f)
		{
			mRoll = -15.0f;
		}
		else 
		{ 
			mRoll = 15.0f; 
		}
		
		return mRoll;
	}
}
