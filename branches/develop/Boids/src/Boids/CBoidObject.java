package Boids;

import VectorMath.Point3D;
import VectorMath.Vector3D;
import Interface.ISceneObject;

public class CBoidObject extends CSceneObject implements ISceneObject {

	//static private float maxSpeed = 10.0f;
	
	private float mSpeed = 0.0f;
	private float mRoll  = 15.0f;
	
	private Vector3D heading = new Vector3D();
	private Vector3D velocity = new Vector3D();	

	private Point3D position = new Point3D(0.0, 0.0, 0.0);
	private Point3D updatedPosition = new Point3D(0,0,0);
	
	private CBoidNavigator navigator = null;
	private CBoidEye       eye       = null;
	private CBoidPilot     pilot     = null;
	
	public CBoidObject(CBoidScene scene)
	{		
		initializeBoid(scene);
	}
	
	public CBoidObject(CBoidScene scene, Point3D startPos)
	{
		position = startPos;
		initializeBoid(scene);
	}
	
	private void initializeBoid(CBoidScene scene)
	{
		eye       = new CBoidEye(scene);
		navigator = new CBoidNavigator(eye);
		pilot     = new CBoidPilot();
	}
	
	public void calculate()
	{
		Vector3D result = navigator.calculate(this);
		
		Point3D newPos = this.getLocationInScene();
		newPos = newPos.add(new Point3D(result.getX(), result.getY(), result.getZ()));
		
		updatedPosition = newPos;
		
		pilot.evaluate(this);
		
		this.setPositionInScene(updatedPosition);
	}
	
	public void update()
	{	
		//this.setPositionInScene(updatedPosition);
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
		return position;
	}
	
	public void setPositionInScene(Point3D pos) {
		position = pos;
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

	public Vector3D getHeading() {
		return heading;
	}

	public void setHeading(Vector3D heading) {
		this.heading = heading;
	}

	public Vector3D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3D velocity) {
		this.velocity = velocity;
	}
}
