package Boids;

import VectorMath.Point3D;
import VectorMath.Vector3D;
import Interface.ISceneObject;

public class CBoidObject extends CSceneObject implements ISceneObject {

	private double maxAcceleration = 10;

	private double mAccel = 0;
	private double mSpeed = 3;
	private double mRoll = 15;

	private double mLastTime = 0;

	private Vector3D heading = new Vector3D(-1, 0, 0); // Direction boid flies
														// to
	private Vector3D velocity = new Vector3D(); // Resulting vector of heading
												// and speed

	private Vector3D requestedAccel = new Vector3D(); // Acceleration desired
														// by navigator module

	private Point3D position = new Point3D(0, 0, 0);
	private Point3D updatedPosition = new Point3D(0, 0, 0);

	private CBoidNavigator navigator = null;
	private CBoidEye eye = null;
	private CBoidPilot pilot = null;

	public CBoidObject(CBoidScene scene) {
		initializeBoid(scene);
	}

	public CBoidObject(CBoidScene scene, Point3D startPos) {
		position = startPos;
		initializeBoid(scene);

		velocity = new Vector3D(mSpeed, mSpeed, mSpeed);
		velocity.multiply(heading);
	}

	private void initializeBoid(CBoidScene scene) {
		eye = new CBoidEye(this, scene);
		navigator = new CBoidNavigator(eye);
		pilot = new CBoidPilot();

		mLastTime = System.currentTimeMillis();
	}

	public void calculate() {
		// Let navigation module calculate desired acceleration in scene
		requestedAccel = navigator.calculate(this).scalarMultiply(maxAcceleration);
		// pilot.evaluate(this);

	}

	public void update(double time) {
		// Let pilot module calculate new positions based on time delta
		Point3D newPos = pilot.evaluate(this, (time - mLastTime) * 0.001);
		setPositionInScene(newPos);

		System.out.print("newPos = (" + newPos.getX() + "," + newPos.getY()
				+ "," + newPos.getZ() + ")\n");

		mLastTime = time;
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
	public double getSpeed() {
		return mSpeed;
	}

	public void setSpeed(double speed) {
		mSpeed = speed;
	}

	public double getPitch() {
		return 0;
	}

	public double getRoll() {

		if (mRoll == 15) {
			mRoll = -15;
		} else {
			mRoll = 15;
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

	public Vector3D getRequestedAccel() {
		return requestedAccel;
	}
}
